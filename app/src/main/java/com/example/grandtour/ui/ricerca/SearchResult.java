package com.example.grandtour.ui.ricerca;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.adapter.ViaggiRecyclerViewAdapter;
import com.example.grandtour.databinding.FragmentSearchResultBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class SearchResult extends Fragment {

    private SearchViewModel searchViewModel = SearchFragment.getSearchViewModel();
    private FragmentSearchResultBinding binding;

    private String regione = "test";
    private Date andata;
    private Date ritorno;
    private String mezzo;

    private ImageButton filtri;
    private LinearLayout visualizzaFiltri;

    private ListView listViewResult;


    private List<Viaggio> mViaggioList = new ArrayList<>();

    RecyclerView mRecyclerViewViaggi;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    final private String TAG_SR = "SEARCH_RESULT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        readViewModel();    //function for the read of user form value
        readDB();           //function for the read of the Database

        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //searchViewModel.check();

        //decidere se rifare la ricerca con il risultato
        filtri = root.findViewById(R.id.filter);
        visualizzaFiltri = root.findViewById(R.id.filter_visibility);

        filtri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visualizzaFiltri.getVisibility() == View.GONE) {
                    visualizzaFiltri.setVisibility(View.VISIBLE);
                }
                else
                    visualizzaFiltri.setVisibility(View.GONE);
            }
        });

        //listViewResult = root.findViewById(R.id.search_list_result); //inutile se uso recyclerView

        mRecyclerViewViaggi = root.findViewById(R.id.search_recyclerview);
        mRecyclerViewViaggi.setLayoutManager(new LinearLayoutManager(getContext()));


/*
        //ritardo della lettura del DB -> ritardo inserimento dati nel RecyclerView
        //ideale leggere dati su MainActivity
        ViaggiRecyclerViewAdapter adapter = new ViaggiRecyclerViewAdapter(mViaggioList,
                new ViaggiRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Viaggio viaggio) {
                        Log.d(TAG_SR, "onItemClick: " + viaggio);
                    }
                });
        mRecyclerViewViaggi.setAdapter(adapter);
*/

        //dopo l'inserimento dei valori nella lista faccio un confronto con i valori dati dalla ricerca




        return root;
    }

    public void readViewModel() {
        super.onStart();
        searchViewModel.getRegione().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //Log.d(TAG_SR, s);
                regione = s;
                //Log.d(TAG_SR, regione);
            }
        });

        searchViewModel.getAndata().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                andata = date;
                //Log.d(TAG_SR, String.valueOf(date));
            }
        });

        searchViewModel.getRitorno().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                ritorno = date;
                //Log.d(TAG_SR, String.valueOf(date));
            }
        });

        searchViewModel.getMezzo().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mezzo = s;
            }
        });
    }

    public void readDB() {
/*
        Log.d(TAG_SR, "-----  ----- ");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot: dataSnapshot.child("Viaggi").getChildren()) {

                    String idViaggio = postSnapshot.getKey();
                    Log.d(TAG_SR, "----- " + idViaggio + " ----- ");

                    Viaggio v = postSnapshot.getValue(Viaggio.class);

                    //add v in a list/vector/listView/...

                    mViaggioList.add(v);

                    ///test v result from DB
                    Log.d(TAG_SR, v.getDataPartenza());
                    Log.d(TAG_SR, v.getDataRitorno());
                    Log.d(TAG_SR, v.getDestinazione());
                    Log.d(TAG_SR, v.getIdItinerario());
                    Log.d(TAG_SR, v.getMezzo());
                    Log.d(TAG_SR, v.getRegione());

                    Log.d(TAG_SR, "----- " + idViaggio + " ----- ");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nome", "Failed to read value.", error.toException());
            }
        });
*/

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    for (DataSnapshot postSnapshot: task.getResult().child("Viaggi").getChildren()) {

                        String idViaggio = postSnapshot.getKey();
                        Log.d(TAG_SR, "----- " + idViaggio + " ----- ");

                        Viaggio v = postSnapshot.getValue(Viaggio.class);

                        //add v in a list/vector/listView/...
                        //comparison user search form with DB data

                        Log.e(TAG_SR, regione);

                        //miss comparison of the Date


                        if(v.getRegione().equalsIgnoreCase(regione) && v.getMezzo().equalsIgnoreCase(mezzo))
                        mViaggioList.add(v);

                        //test v result from DB
                        Log.d(TAG_SR, v.getDataPartenza());
                        Log.d(TAG_SR, v.getDataRitorno());
                        Log.d(TAG_SR, v.getDestinazione());
                        Log.d(TAG_SR, v.getIdItinerario());
                        Log.d(TAG_SR, v.getMezzo());
                        Log.d(TAG_SR, v.getRegione());

                        Log.d(TAG_SR, "----- " + idViaggio + " ----- ");
                    }
                    ViaggiRecyclerViewAdapter adapter = new ViaggiRecyclerViewAdapter(mViaggioList,
                            new ViaggiRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Viaggio viaggio) {
                                    Log.d(TAG_SR, "onItemClick: " + viaggio);
                                }
                            });
                    mRecyclerViewViaggi.setAdapter(adapter);
                }
            }
        });



    }

}
