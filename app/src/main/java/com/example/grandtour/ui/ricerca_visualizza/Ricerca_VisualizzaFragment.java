package com.example.grandtour.ui.ricerca_visualizza;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;

import com.example.grandtour.Viaggio;
import com.example.grandtour.databinding.FragmentRicercaVisualizzaBinding;
import com.example.grandtour.databinding.FragmentVisualizzaRecensioneBinding;
import com.example.grandtour.ui.visualizza_recensioni.Visualizza_Recensioni_Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Ricerca_VisualizzaFragment extends Fragment {

    private FragmentRicercaVisualizzaBinding binding;
    private Ricerca_VisualizzaViewModel visViewModel;
    private Button cerca;
    private static String strRegione;
    private  static String strViaggio;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

    //prova login
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d("Log in", "sono dentro");
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            Log.d("Nome ", name);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        else
        {
            Log.d("Log in", "nologgin");

        }


        visViewModel =
                new ViewModelProvider(this).get(Ricerca_VisualizzaViewModel.class);

        binding = FragmentRicercaVisualizzaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //riempiamo lo spinner
        View v = inflater.inflate(R.layout.fragment_ricerca_visualizza, container, false);


        //prendo i due parametri spinner

         Spinner regione = (Spinner) v.findViewById(R.id.spinner_search_regione1);

        Spinner viaggio = (Spinner) v.findViewById(R.id.spinner_search_viaggio1); //viene settato dopo








       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.regione, android.R.layout.simple_spinner_item);

        b.setAdapter(adapter);
        */
        Log.d("Prova regione inizo", regione.getSelectedItem().toString());
        Log.d("Prova viaggio inizio", viaggio.getSelectedItem().toString());

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


        //riferimento al bottone cerca
        cerca = (Button) v.findViewById(R.id.buttonCerca);
        cerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sRegione = regione.getSelectedItem().toString();
                String sViaggio = viaggio.getSelectedItem().toString();

                //setto le variabili globali
                setRegione(sRegione);
                setViaggio(sViaggio);

                //verifico funzionamento
                Log.d("Prova ciao", getRegione());
                Log.d("Prova bene", getViaggio());



                Fragment fragment = null;
                fragment =new Visualizza_Recensioni_Fragment();
                replaceFragment(fragment);
            }
        });



        return v;
    }

    public void setRegione(String regione)
    {
        strRegione=regione;
    }
    public void setViaggio(String viaggio)
    {
        strViaggio=viaggio;
    }
    public static String getViaggio()
    {
        return strViaggio;
    }
    public static String getRegione()
    {
        return strRegione;
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
