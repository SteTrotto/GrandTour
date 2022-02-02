package com.example.grandtour.ui.ricerca;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.InternetConnection;
import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentSearchBinding;
import com.example.grandtour.ui.user.UserSingUp;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SearchFragment extends Fragment {

    private static SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    private ImageButton imageButton;
    //private EditText editAndata;
    //private EditText editRitorno;
    private Spinner spinnerMezzo;
    private int spinnerSel;
    private Spinner spinnerRegione;
    private int spinnerReg;

    private Spinner spinnerDurata;
    private int spinnerDur;

    //final Calendar myCalendar = Calendar.getInstance();
    //private boolean andata;

    //final private String myFormat = "dd/MM/yy"; //In which you need put here
    //final private SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

    final private String TAG_S = "SEARCH";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;

        textView.setText("Cerca una Destinazione");

        imageButton = root.findViewById(R.id.search_button);
        //editAndata = root.findViewById(R.id.search_data_partenza);
        //editRitorno = root.findViewById(R.id.search_data_ritorno);
        spinnerRegione = root.findViewById(R.id.spinner_regione);
        spinnerRegione.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerReg = pos;                                      //elemento selenzionato della listaS
            }
        });

        //selezione oggetto spinner
        spinnerMezzo = root.findViewById(R.id.spinner_mezzo);
        spinnerMezzo.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                spinnerSel = pos;                                      //elemento selenzionato della lista
                //super.onItemSelected(parent, view, pos, id);
                //System.out.println("pos " + pos);
                //System.out.println("id " + id);
                //String m = (String) spinnerMezzo.getItemAtPosition(spinnerSel);    //elemento singolo della lista
                //System.out.println("modulo " + m);
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
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regione;
                if(spinnerReg == 0) {
                    //Toast.makeText(getContext(), "Regione non inserita", Toast.LENGTH_SHORT).show();
                    regione = "Regione";
                    //return;
                } else
                    regione = (String) spinnerRegione.getItemAtPosition(spinnerReg);

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                //String regione = (String) spinnerRegione.getItemAtPosition(spinnerReg);
                //String dataAndata = String.valueOf(editAndata.getText());
                //String dataRitorno = String.valueOf(editRitorno.getText());
                String mezzo = (String) spinnerMezzo.getItemAtPosition(spinnerSel);

                String durata = (String) spinnerDurata.getItemAtPosition(spinnerDur);

                //if(regione.equalsIgnoreCase("Regione")) return;
                Log.d(TAG_S, regione);
                Log.d(TAG_S, mezzo);
                Log.d(TAG_S, durata);

                /*if(durata.equalsIgnoreCase("Durata:")) {
                    Toast.makeText(getContext(), "Durata non inserita",
                            Toast.LENGTH_SHORT).show();
                    return;
                }*/
                //if(mezzo.equalsIgnoreCase("mezzo")) return;

/*
                if(dataAndata.equalsIgnoreCase("Data partenza")) {
                    Toast.makeText(getContext(), "Data non inserita",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG_S, dataAndata);

                if(dataRitorno.equalsIgnoreCase("Data ritorno")) {
                    Toast.makeText(getContext(), "Data non inserita",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG_S, dataRitorno);


                Date t = new Date();
                Date a = new Date();
                try {
                    a = sdf.parse(dataAndata);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date r = new Date();
                try {
                    r = sdf.parse(dataRitorno);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //lista check delle date
                if(t.after(a)) {
                    Log.e(TAG_S, "oggi dopo di andata");
                    Toast.makeText(getContext(), "Errore data di partenza",
                            Toast.LENGTH_SHORT).show();
                    return;  //messaggio per utente
                }
                if(t.after(r)) {
                    Log.e(TAG_S, "oggi dopo di ritorno");
                    Toast.makeText(getContext(), "Errore data di ritorno",
                            Toast.LENGTH_SHORT).show();
                    return;  //messaggio per utente
                }
                if(a.after(r)) {
                    Log.e(TAG_S, "andata dopo ritorno");
                    Toast.makeText(getContext(), "Errorei date di partenza e/o ritorno",
                            Toast.LENGTH_SHORT).show();
                    return;  //messagio per utente
                }
*/
                //searchViewModel.setmDateA(a);
                //searchViewModel.setmDateR(r);
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
        }
        );

/*
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        editAndata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andata = true;
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        });

        editRitorno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andata = false;
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchFragment.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
*/
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

    /*
    private void updateLabel() {
        if(andata)  editAndata.setText(sdf.format(myCalendar.getTime()));
        else        editRitorno.setText(sdf.format(myCalendar.getTime()));
    }
    */

}
