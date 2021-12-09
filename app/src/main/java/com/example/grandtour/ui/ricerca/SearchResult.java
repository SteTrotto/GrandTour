package com.example.grandtour.ui.ricerca;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentSearchResultBinding;

import java.util.Date;
import java.util.List;

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

    final private String TAG_SR = "SEARCH_RESULT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        readViewModel();

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


        listViewResult = root.findViewById(R.id.search_list_result);


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

}
