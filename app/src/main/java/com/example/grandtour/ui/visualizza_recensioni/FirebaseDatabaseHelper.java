package com.example.grandtour.ui.visualizza_recensioni;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.grandtour.ui.ricerca_visualizza.Ricerca_VisualizzaFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    //colleghamo il database
    //  FirebaseDatabase database= FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;

    private List<Recensione> recensioni = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Recensione> recensioni, List<String> keys);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    //creo i costuttori
    //primo
    public FirebaseDatabaseHelper() {
        //colleghamo il database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference().child("Recensioni");
    }
    private FirebaseAuth mAuth;
    FirebaseUser user;


    public void readRecensioni(final DataStatus dataStatus) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                recensioni.clear(); //pulisco le recensioni da qualsiasi dato
                List<String> keys = new ArrayList<>();//serve per conservare tutte le chiavi delle recensioni
                for (DataSnapshot keyNode : snapshot.getChildren()) //conterrà chiave e valore di uno specifico nodo
                {

                    Recensione recensione = keyNode.getValue(Recensione.class); //creo un oggetto recensine con i campi presi dal database

                    if (recensione.getIdViaggio().equals(Ricerca_VisualizzaFragment.getViaggio()) &&
                            recensione.getRegione().equals(Ricerca_VisualizzaFragment.getRegione())) {
                        keys.add(keyNode.getKey());//prendo la chiave del nodo e la salvo nella lista di tutte le chiavi
                        recensioni.add(recensione);
                    }

                }
                dataStatus.DataIsLoaded(recensioni, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


    }

    public void readRecensioniUtenete(final DataStatus dataStatus) {

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mAuth = FirebaseAuth.getInstance();
                user =  mAuth.getCurrentUser();
                recensioni.clear(); //pulisco le recensioni da qualsiasi dato
                List<String> keys = new ArrayList<>();//serve per conservare tutte le chiavi delle recensioni
                for (DataSnapshot keyNode : snapshot.getChildren()) //conterrà chiave e valore di uno specifico nodo
                {

                    Recensione recensione = keyNode.getValue(Recensione.class); //creo un oggetto recensine con i campi presi dal database

                    if (user.getUid().equals(recensione.getId_Utente())) {
                        keys.add(keyNode.getKey());//prendo la chiave del nodo e la salvo nella lista di tutte le chiavi
                        recensioni.add(recensione);
                    }

                }
                dataStatus.DataIsLoaded(recensioni, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


    }
}