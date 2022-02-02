package com.example.grandtour.ui.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentUserLogBinding;
import com.example.grandtour.ui.visualizza_recensioni.FirebaseDatabaseHelper;
import com.example.grandtour.ui.visualizza_recensioni.Recensione;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



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

    public void readRecensioni(final FirebaseDatabaseHelper.DataStatus dataStatus){

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recensioni.clear(); //pulisco le recensioni da qualsiasi dato
                List<String> keys = new ArrayList<>();//serve per conservare tutte le chiavi delle recensioni
                for (DataSnapshot keyNode : snapshot.getChildren()) //conterrà chiave e valore di uno specifico nodo
                {
                    keys.add(keyNode.getKey());//prendo la chiave del nodo e la salvo nella lista di tutte le chiavi
                    Recensione recensione=keyNode.getValue(Recensione.class); //creo un oggetto recensine con i campi presi dal database
                    recensioni.add(recensione);
                }
                dataStatus.DataIsLoaded(recensioni, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });

    }


    private UserViewModel UserViewModel;
    private FragmentUserLogBinding binding;

    private TextView profilo;
    private TextView preferiti;
    private TextView contatti;
    private ImageButton button_profilo;
    private ImageButton button_preferiti;
    private ImageButton button_contatti;


    private FirebaseAuth mAuth;
    private final String TAG = "HOME";

    final private String TAG_S = "USER";

    private TextView email;
    private TextView nome_cognome;
    private TextView Data;

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

        //mAuth = FirebaseAuth.getInstance();

        email = root.findViewById(R.id.email);
        nome_cognome = root.findViewById(R.id.nome_cognome);
        Data = root.findViewById(R.id.DataNascita);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user =  mAuth.getCurrentUser();

        email.setText(user.getEmail());
        nome_cognome.setText(user.getDisplayName());

        profilo.setText("Email: "+email.getText()+"\n"
                +"Nome e Cognome: "+  nome_cognome.getText()+"\n");
                //+"Data di nascita: "+ Data.getText());
        contatti.setText("Email: GrandTour@gmail.com"+"\n"
                +"Numero di Telefono: 0002 98 54");

        button_profilo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (profilo.getVisibility() == View.GONE) {
                    profilo.setVisibility(View.VISIBLE);
                }
                else
                    profilo.setVisibility(View.GONE);
            }
        });


        button_preferiti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (preferiti.getVisibility() == View.GONE) {
                    preferiti.setVisibility(View.VISIBLE);
                }
                else
                    preferiti.setVisibility(View.GONE);
            }
        });

        button_contatti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (contatti.getVisibility() == View.GONE) {
                    contatti.setVisibility(View.VISIBLE);
                }
                else
                    contatti.setVisibility(View.GONE);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}