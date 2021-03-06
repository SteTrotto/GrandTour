package com.example.grandtour.ui.user;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentUserLogBinding;
import com.example.grandtour.ui.visualizza_recensioni.FirebaseDatabaseHelper;
import com.example.grandtour.ui.visualizza_recensioni.Recensione;
import com.example.grandtour.ui.visualizza_recensioni.RecycleView_Config;
import com.example.grandtour.ui.visualizza_recensioni.SpacingitemDecorator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.ArrayList;
import java.util.List;

public class UserLogUtente extends Fragment {

    private DatabaseReference myRef;
    private List<Recensione> recensioni=new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Recensione> recensioni, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }



    public UserLogUtente() {
        //colleghamo il database
        FirebaseDatabase database= FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef=database.getReference().child("Recensioni");
    }


    private UserViewModel UserViewModel;
    private FragmentUserLogBinding binding;

    private RecycleView_Config.RecensioniAdapter adapter;
    private TextView profilo;
    private TextView preferiti;
    private TextView contatti;
    private ImageButton button_profilo;
    private ImageButton button_preferiti;
    private ImageButton button_contatti;
    private ImageButton button_log_out;

    private RecyclerView mRecyclerView;


    private FirebaseAuth mAuth;
            FirebaseUser user;
    private final String TAG = "HOME";

    final private String TAG_S = "USER";

    private TextView email;
    private TextView nome_cognome;

    int conta = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserLogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUser;
        UserViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        profilo = root.findViewById(R.id.casella1);
        preferiti = root.findViewById(R.id.casella2);
        contatti = root.findViewById(R.id.casella3);


        button_profilo = root.findViewById(R.id.button1);
        button_preferiti = root.findViewById(R.id.button2);
        button_contatti = root.findViewById(R.id.button3);
        button_log_out = root.findViewById(R.id.log_out);


        email = root.findViewById(R.id.email);
        nome_cognome = root.findViewById(R.id.nome_cognome);

        mAuth = FirebaseAuth.getInstance();


        conta++;
        user =  mAuth.getCurrentUser();
        Log.e(TAG_S, "----------USER LOG UTENTE ----------");
        if(conta == 2) {
            Fragment delete = this;
            delete.onDestroy();
        } else {
            if(user == null) {
                Log.e(TAG_S, "----------------USER LOG UTENTE to USER FRAGMENT--------------------");
                Fragment fragment1 = null;
                fragment1 = new UserFragment();
                replaceFragment(fragment1);
            }
            else {
                email.setText(user.getEmail());
                nome_cognome.setText(user.getDisplayName());

                profilo.setText("Email: " + email.getText() + "\n"
                        + "Nome e Cognome: " + nome_cognome.getText() + "\n");
                contatti.setText("Email: GrandTour@gmail.com" + "\n"
                        + "Numero di Telefono: 0002 98 54");

                //lista di recensioni
                mRecyclerView = (RecyclerView) root.findViewById(R.id.lista);
                //gioco di spazi tra le recensioni
                SpacingitemDecorator itemDecorator = new SpacingitemDecorator(10);
                mRecyclerView.addItemDecoration(itemDecorator);
                readRecensioni();

                button_log_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.getInstance()
                                .signOut();

                        Fragment fragment = null;
                        fragment = new UserFragment();
                        replaceFragment(fragment);
                    }
                });

                button_profilo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (profilo.getVisibility() == View.GONE) {
                            profilo.setVisibility(View.VISIBLE);
                        } else
                            profilo.setVisibility(View.GONE);
                    }
                });


                button_preferiti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferiti.getVisibility() == View.GONE) {
                            preferiti.setVisibility(View.VISIBLE);
                            mRecyclerView.setVisibility(View.VISIBLE);

                        } else {
                            preferiti.setVisibility(View.GONE);
                            mRecyclerView.setVisibility(View.GONE);
                        }
                    }
                });

                button_contatti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (contatti.getVisibility() == View.GONE) {
                            contatti.setVisibility(View.VISIBLE);
                        } else
                            contatti.setVisibility(View.GONE);
                    }
                });
            }
        }

        return root;
    }

    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void readRecensioni(){
        new FirebaseDatabaseHelper().readRecensioniUtenete(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Recensione> recensioni, List<String> keys) {
                new RecycleView_Config().setConfig(mRecyclerView, getActivity(), recensioni, keys );
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

}

}