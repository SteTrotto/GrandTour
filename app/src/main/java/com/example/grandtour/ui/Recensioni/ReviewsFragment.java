package com.example.grandtour.ui.Recensioni;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.InternetConnection;
import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentReviewsBinding;
import com.example.grandtour.ui.ricerca_recensione.Ricerca_RecensioneFragment;
import com.example.grandtour.ui.ricerca_visualizza.Ricerca_VisualizzaFragment;


public class ReviewsFragment extends Fragment {

    private ReviewsViewModel reviewsViewModel;
    private FragmentReviewsBinding binding;

    private Button bottone_scrivi;
    private Button bottone_visualizza;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reviewsViewModel =
                new ViewModelProvider(this).get(ReviewsViewModel.class);

        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //questo bottone è quello per andare a SCRIVERE le recensioni
        bottone_scrivi = root.findViewById(R.id.button);
        bottone_scrivi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!InternetConnection.haveInternetConnection(getContext())) {
                    Toast.makeText(getContext(), "Errore di connessione, verifica la tua rete a internet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Fragment fragment = null;
                fragment =new Ricerca_RecensioneFragment();
                replaceFragment(fragment);

            }
        });
        //Questo bottone è quello per andare a LEGGERE le recensioni
        bottone_visualizza = root.findViewById(R.id.button2);
        bottone_visualizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!InternetConnection.haveInternetConnection(getContext())) {
                    Toast.makeText(getContext(), "Errore di connessione, verifica la tua rete a internet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Fragment fragment = null;
                fragment =new Ricerca_VisualizzaFragment();
                replaceFragment(fragment);

            }
        });

        return root;
    }
    public void replaceFragment(Fragment someFragment) {
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


}