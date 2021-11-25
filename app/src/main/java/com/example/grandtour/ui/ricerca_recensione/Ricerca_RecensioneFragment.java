package com.example.grandtour.ui.ricerca_recensione;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentRicercaRecensioneBinding;

import java.util.ArrayList;



  public class Ricerca_RecensioneFragment extends Fragment/*AppCompatActivity*/ {




    private Ricerca_RecensioneViewModel ricerca_recViewModel;
    private FragmentRicercaRecensioneBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ricerca_recViewModel = new ViewModelProvider(this).get(Ricerca_RecensioneViewModel.class);

        binding = FragmentRicercaRecensioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    //-------------------------------------------------------------------


}

