package com.example.grandtour.ui.ricerca_visualizza;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentRicercaRecensioneBinding;

import com.example.grandtour.databinding.FragmentVisualizzaRecensioneBinding;
import com.example.grandtour.ui.Recensioni.ReviewsViewModel;
import com.example.grandtour.ui.ricerca_recensione.Ricerca_RecensioneViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ricerca_VisualizzaFragment extends Fragment {

    private FragmentVisualizzaRecensioneBinding binding;
    private Ricerca_VisualizzaViewModel visViewModel;
    private Button cerca;
    private TextView mNameView;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        visViewModel =
                new ViewModelProvider(this).get(Ricerca_VisualizzaViewModel.class);

        binding = FragmentVisualizzaRecensioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //riempiamo lo spinner
        View v = inflater.inflate(R.layout.fragement_ricerca_visualizza, container, false);

        //prendo i due parametri spinner
        Spinner regione = (Spinner) v.findViewById(R.id.spinner_search_regione1);

        Spinner viaggio = (Spinner) v.findViewById(R.id.spinner_search_viaggio1); //viene settato dopo





       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.regione, android.R.layout.simple_spinner_item);

        b.setAdapter(adapter);
        */

        regione.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                ArrayAdapter<CharSequence> adapter;
                switch (pos) {

                    case 1:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiAbruzzo,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 2:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiBasilicata,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 3:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiCalabria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 4:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiCampania,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 5:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiEmilia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 6:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiFriuli,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 7:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLazio,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 8:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLiguria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 9:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLombardia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 10:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiMarche,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 11:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiMolise,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 12:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiPiemonte,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 13:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiPuglia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 14:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiSardegna,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 15:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiSicilia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 16:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiToscana,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 17:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiTrentino,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 18:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiUmbria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 19:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiAosta,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                    case 20:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiVeneto,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;


                    default:
                        //contiene anche il case 0
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.Iniziale,
                                        android.R.layout.simple_spinner_dropdown_item);
                        viaggio.setAdapter(adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //da qui in giù va in un altra pagina, è solo una prova

        //riferimento al database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference().child("Name");
        mNameView = (TextView) v.findViewById(R.id.textView2);
        //riferimento al bottone cerca
        cerca = (Button) v.findViewById(R.id.buttonCerca);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myTag", "Sono dentro il metodo");

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

            }
        });

        return v;
    }



}
