package com.example.grandtour.ui.ricerca;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentSearchBinding binding;

    private ImageButton imageButton;
    private EditText editText;
    private EditText editDate;
    private EditText editTrasporto;
    private Spinner spinnerMezzo;
    private int spinnerSel;


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
        editDate = root.findViewById(R.id.search_data);
        //editTrasporto = root.findViewById(R.id.search_trasporto);
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


        //test bottone
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                String string = (String) spinnerMezzo.getItemAtPosition(spinnerSel);

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(editText.getText()));
                Log.d(TAG_S, String.valueOf(editDate.getText()));
                //Log.d(TAG_S, String.valueOf(editTrasporto.getText()));
                Log.d(TAG_S, string);
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
}