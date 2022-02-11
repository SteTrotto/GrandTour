package com.example.grandtour.ui.ricerca;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.InternetConnection;
import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentSearchBinding;



public class SearchFragment extends Fragment {

    private static SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    private Button imageButton;
    private Spinner spinnerMezzo;
    private int spinnerSel;
    private Spinner spinnerRegione;
    private int spinnerReg;
    private Spinner spinnerDurata;
    private int spinnerDur;

    final private String TAG_S = "SEARCH";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;
        textView.setText("Cerca una Destinazione");

        spinnerRegione = root.findViewById(R.id.spinner_regione);
        spinnerRegione.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerReg = pos;   //elemento selenzionato della lista
            }
        });

        //selezione oggetto spinner
        spinnerMezzo = root.findViewById(R.id.spinner_mezzo);
        spinnerMezzo.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerSel = pos;
            }
        });

        spinnerDurata = root.findViewById(R.id.spinner_durata);
        spinnerDurata.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerDur = pos;
            }
        });

        //bottone
        imageButton = root.findViewById(R.id.search_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regione;
                if(spinnerReg == 0)
                    regione = "Regione";
                 else
                    regione = (String) spinnerRegione.getItemAtPosition(spinnerReg);

                String mezzo = (String) spinnerMezzo.getItemAtPosition(spinnerSel);
                String durata = (String) spinnerDurata.getItemAtPosition(spinnerDur);

                Log.d(TAG_S, regione);
                Log.d(TAG_S, mezzo);
                Log.d(TAG_S, durata);

                searchViewModel.setmRegione(regione);
                searchViewModel.setmMezzo(mezzo);
                searchViewModel.setmDurata(durata);

                if(!InternetConnection.haveInternetConnection(getContext())) {
                    Log.d(TAG_S, "ERRORE DI CONNESSIONE");
                    Toast.makeText(getContext(), "Errore di connessione, verifica la tua rete a internet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Fragment fragment = null;
                fragment = new SearchResult();
                replaceFragment(fragment);
            }
        });

        return root;
    }

    public static class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static SearchViewModel getSearchViewModel() {
        return searchViewModel;
    }


}
