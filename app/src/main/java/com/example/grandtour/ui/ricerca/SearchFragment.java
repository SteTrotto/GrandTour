package com.example.grandtour.ui.ricerca;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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
        editTrasporto = root.findViewById(R.id.search_trasporto);

        //test bottone
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(editText.getText()));
                Log.d(TAG_S, String.valueOf(editDate.getText()));
                Log.d(TAG_S, String.valueOf(editTrasporto.getText()));
            }
        }
        );





        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}