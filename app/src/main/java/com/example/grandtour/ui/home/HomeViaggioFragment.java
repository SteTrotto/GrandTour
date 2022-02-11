package com.example.grandtour.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.databinding.FragmentViaggioBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class HomeViaggioFragment extends Fragment {

    private FragmentViaggioBinding binding;

    private Viaggio vResult = HomeFragment.getViaggio();

    private TextView mezzo;
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

    private LinearLayout linearLayout;
    private boolean visibile;
    private ImageButton hide;

    private CardView cardView;
    private CardView cardViewViaggio;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference spaceRef;

    final private String TAG_HV = "HOME VIAGGIO";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentViaggioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Log.d(TAG_HV,vResult.getRegione());

        mezzo = root.findViewById(R.id.mezzo_result);
        mezzo.setText(vResult.getMezzo());

        nomeViaggio = root.findViewById(R.id.nomeV_result);
        nomeViaggio.setText(vResult.getNomeViaggio());

        cardViewViaggio = root.findViewById(R.id.card_view_viaggio);

        String path = vResult.getRegione() + "/" + vResult.getRegione() + ".jpg";
        spaceRef = storageRef.child(path);
        image = root.findViewById(R.id.image_viaggio);
        Glide.with(getContext())
                .load(spaceRef)
                .into(image);


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


        cardView = root.findViewById(R.id.card_view);
        image4 = root.findViewById(R.id.image_tappa4);
        tappa4 = root.findViewById(R.id.nome4_result);
        if(vResult.getTappa4().equalsIgnoreCase("")) {
            tappa4.setVisibility(View.INVISIBLE);
            cardView.setVisibility(View.INVISIBLE);
        } else {
            tappa4.setText(vResult.getTappa4());
            path = vResult.getRegione() + "/" + vResult.getTappa4() + ".jpg";
            spaceRef = storageRef.child(path);
            Glide.with(getContext())
                    .load(spaceRef)
                    .into(image4);
        }


        linearLayout = root.findViewById(R.id.linearlayout);
        LinearLayout.MarginLayoutParams params = (LinearLayout.MarginLayoutParams) linearLayout.getLayoutParams();
        visibile = true;
        hide = root.findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(visibile) {
                    params.setMargins(0, 690, 0, 0);
                    linearLayout.setLayoutParams(params);
                    cardViewViaggio.setVisibility(View.GONE);
                    visibile = false;
                    hide.setBackground(getActivity().getDrawable(R.drawable.baseline_expand_more_24));
                }
                else {
                    params.setMargins(0, 990, 0, 0);//270dp = 810px
                    linearLayout.setLayoutParams(params);
                    cardViewViaggio.setVisibility(View.VISIBLE);
                    visibile = true;
                    hide.setBackground(getActivity().getDrawable(R.drawable.baseline_expand_less_24));
                }
            }
        });

        return root;
    }
}
