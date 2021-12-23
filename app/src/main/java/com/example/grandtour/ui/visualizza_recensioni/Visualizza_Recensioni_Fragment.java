package com.example.grandtour.ui.visualizza_recensioni;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentRicercaVisualizzaBinding;
import com.example.grandtour.databinding.FragmentVisualizzaRecensioneBinding;
import com.example.grandtour.databinding.RecensioniListaItemBinding;
import com.example.grandtour.ui.ricerca_visualizza.Ricerca_VisualizzaFragment;
import com.example.grandtour.ui.ricerca_visualizza.Ricerca_VisualizzaViewModel;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class Visualizza_Recensioni_Fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FragmentVisualizzaRecensioneBinding binding;
    private Ricerca_VisualizzaViewModel visrecViewModel;

    private TextView mNameView;
    Ricerca_VisualizzaFragment c;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        visrecViewModel =
                new ViewModelProvider(this).get(Ricerca_VisualizzaViewModel.class);

        binding = FragmentVisualizzaRecensioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //riempiamo lo spinner
        View v = inflater.inflate(R.layout.fragment_visualizza_recensione, container, false);


        mRecyclerView= (RecyclerView) v.findViewById(R.id.recycleview_recensioni);
        new FirebaseDatabaseHelper().readRecensioni(new FirebaseDatabaseHelper.DataStatus() {
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

    return v;

    }



}
/*
       //colleghamo il database
        //riferimento al database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference().child("Name");

        mNameView = (TextView) v.findViewById(R.id.textView2);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.getValue().toString();
                mNameView.setText("Name: " + name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        */


/*
        mRecyclerView= (RecyclerView) v.findViewById(R.id.recycleview_recensioni);
        new FirebaseDatabaseHelper().readRecensioni(new FirebaseDatabaseHelper.DataStatus() {
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
*/