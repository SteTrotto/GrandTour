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

import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.databinding.FragmentViaggioBinding;

public class SearchViaggioFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentViaggioBinding binding;

    private Viaggio vResult = SearchResult.getViaggio();

    private TextView destinazione;
    private TextView mezzo;
    private TextView regione;
    private TextView dataPartenza;
    private TextView dataRitorno;

    private TextView durata;
    private TextView nomeViaggio;
    private TextView tappa1;
    private TextView tappa2;
    private TextView tappa3;
    private TextView tappa4;

    final private String TAG_SV = "SEARCH VIAGGIO";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentViaggioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(TAG_SV,vResult.getRegione());

        mezzo = root.findViewById(R.id.mezzo_result);
        mezzo.setText(vResult.getMezzo());

        nomeViaggio = root.findViewById(R.id.nomeV_result);
        nomeViaggio.setText(vResult.getNomeViaggio());

        //destinazione = root.findViewById(R.id.destinazione_result);
        //destinazione.setText(vResult.getDestinazione());

        //dataPartenza = root.findViewById(R.id.partenza_result);
        //dataPartenza.setText("Data Partenza: " + vResult.getDataPartenza());

        //dataRitorno = root.findViewById(R.id.ritorno_result);
        //dataRitorno.setText("Data Ritorno: " + vResult.getDataRitorno());
        //aggiungere gli altri valori delle View seguendo il layout

        return root;
    }

}
