package com.example.grandtour.ui.ricerca;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.databinding.FragmentHomeBinding;
import com.example.grandtour.databinding.FragmentSearchResultBinding;
import com.example.grandtour.ui.home.HomeViewModel;

import java.util.Date;

public class SearchResult extends Fragment {

    private SearchViewModel searchViewModel = SearchFragment.getSearchViewModel();
    private FragmentSearchResultBinding binding;

    private String regione = "test";
    private Date andata;
    private Date ritorno;
    private String mezzo;

    final private String TAG_SR = "SEARCH_RESULT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        readViewModel();

        //searchViewModel.check();

        //Log.d(TAG_SR, String.valueOf(searchViewModel.getRegione()));

        /*
        Log.d(TAG_SR, regione);
        Log.d(TAG_SR, mezzo);
        Log.d(TAG_SR, String.valueOf(andata));
        Log.d(TAG_SR, String.valueOf(ritorno));
        */

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
