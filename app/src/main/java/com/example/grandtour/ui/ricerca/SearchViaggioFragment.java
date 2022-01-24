package com.example.grandtour.ui.ricerca;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.example.grandtour.MyAppGlideModule;
import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.databinding.FragmentViaggioBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class SearchViaggioFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private FragmentViaggioBinding binding;

    private Viaggio vResult = SearchResult.getViaggio();

    //private TextView destinazione;
    private TextView mezzo;
    private TextView regione;
    //private TextView dataPartenza;
    //private TextView dataRitorno;

    private TextView durata;
    private TextView nomeViaggio;
    private TextView tappa1;
    private TextView tappa2;
    private TextView tappa3;
    private TextView tappa4;

    private ImageView image;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference spaceRef;

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


        String path = vResult.getRegione() + "/" + vResult.getRegione() + ".jpg";
        spaceRef = storageRef.child(path);
        image = root.findViewById(R.id.image_viaggio);
        Log.e(TAG_SV, "link: " + path);
        Glide.with(getContext())
                .load(spaceRef)
                .into(image);
        Log.e(TAG_SV, "link: " + path);


        image1 = root.findViewById(R.id.image_tappa1);
        tappa1 = root.findViewById(R.id.nome1_result);
        tappa1.setText(vResult.getTappa1());
        path = vResult.getRegione() + "/" + vResult.getTappa1() + ".jpg";
        spaceRef = storageRef.child(path);
        Glide.with(getContext())
                .load(spaceRef)
                .into(image1);


        image2 = root.findViewById(R.id.image_tappa2);
        tappa2 = root.findViewById(R.id.nome2_result);
        tappa2.setText(vResult.getTappa2());
        path = vResult.getRegione() + "/" + vResult.getTappa2() + ".jpg";
        spaceRef = storageRef.child(path);
        Glide.with(getContext())
                .load(spaceRef)
                .into(image2);


        image3 = root.findViewById(R.id.image_tappa3);
        tappa3 = root.findViewById(R.id.nome3_result);
        tappa3.setText(vResult.getTappa3());
        path = vResult.getRegione() + "/" + vResult.getTappa3() + ".jpg";
        spaceRef = storageRef.child(path);
        Glide.with(getContext())
                .load(spaceRef)
                .into(image3);


        image4 = root.findViewById(R.id.image_tappa4);
        tappa4 = root.findViewById(R.id.nome4_result);
        if(vResult.getTappa4().equalsIgnoreCase("")) {
            image4.setVisibility(View.INVISIBLE);
            tappa4.setVisibility(View.INVISIBLE);
        } else {
            tappa4.setText(vResult.getTappa4());
            path = vResult.getRegione() + "/" + vResult.getTappa4() + ".jpg";
            spaceRef = storageRef.child(path);
            Glide.with(getContext())
                    .load(spaceRef)
                    .into(image4);
        }

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
