package com.example.grandtour.ui.ricerca;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentSearchBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    private ImageButton imageButton;
    private EditText editText;
    private EditText editDate;
    private EditText editRitorno;
    private Spinner spinnerMezzo;
    private int spinnerSel;

    final Calendar myCalendar = Calendar.getInstance();
    private boolean andata;

    final private String myFormat = "dd/MM/yy"; //In which you need put here
    final private SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);


    final private String TAG_S = "SEARCH";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSearch;
        searchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        imageButton = root.findViewById(R.id.search_button);
        editText = root.findViewById(R.id.search_regione);
        editDate = root.findViewById(R.id.search_data_partenza);
        editRitorno = root.findViewById(R.id.search_data_ritorno);
        spinnerMezzo = root.findViewById(R.id.spinner_mezzo);

        //selezione oggetto spinner
        spinnerMezzo.setOnItemSelectedListener(new SpinnerActivity(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //super.onItemSelected(parent, view, pos, id);
                //System.out.println("pos " + pos);
                //System.out.println("id " + id);
                spinnerSel = pos;                                      //elemento selenzionato della lista
                //String m = (String) spinnerMezzo.getItemAtPosition(spinnerSel);    //elemento singolo della lista
                //System.out.println("modulo " + m);
            }
        });

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

        editDate.setOnClickListener(new View.OnClickListener() {
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





        //test bottone
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                String regione = String.valueOf(editText.getText());
                String dataAndata = String.valueOf(editDate.getText());
                String dataRitorno = String.valueOf(editRitorno.getText());
                String mezzo = (String) spinnerMezzo.getItemAtPosition(spinnerSel);

                //if(regione.equalsIgnoreCase("Regione")) return;
                Log.d(TAG_S, regione);

                //if(dataAndata.equalsIgnoreCase("Data partenza")) return;
                Log.d(TAG_S, dataAndata);

                //if(dataRitorno.equalsIgnoreCase("Data ritorno")) return;
                Log.d(TAG_S, dataRitorno);

                //if(mezzo.equalsIgnoreCase("mezzo")) return;
                Log.d(TAG_S, mezzo);

                String today = sdf.format(new Date());
                Log.d(TAG_S, today);


                Date t = new Date();
                try {
                    t = sdf.parse(today);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                /*
                if(t.after(a)) {
                    Log.e(TAG_S, "oggi dopo di andata");
                }
                if(t.after(r)) {
                    Log.e(TAG_S, "oggi dopo di ritorno");
                }*/
                if(a.after(r)) {
                    Log.e(TAG_S, "andata dopo ritorno"); //return;  //messagio per utente
                }
            }
        }
        );



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

    private void updateLabel() {
        //String myFormat = "dd/MM/yy"; //In which you need put here
        //SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

        if(andata)  editDate.setText(sdf.format(myCalendar.getTime()));
        else        editRitorno.setText(sdf.format(myCalendar.getTime()));
    }

}