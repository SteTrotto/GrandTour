package com.example.grandtour.ui.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentUserBinding;
import com.example.grandtour.databinding.FragmentUserLogBinding;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogUtente extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserLogBinding binding;

    private TextView profilo;
    private TextView preferiti;
    private TextView contatti;
    private TextView recensione_app;
    private Button button_profilo;
    private Button button_preferiti;
    private Button button_contatti;
    private Button button_recensione_app;

    private FirebaseAuth mAuth;
    private final String TAG = "HOME";

    final private String TAG_S = "USER";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserLogBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUser;
        UserViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        profilo = root.findViewById(R.id.casella1);
        preferiti = root.findViewById(R.id.casella2);
        contatti = root.findViewById(R.id.casella3);
        recensione_app = root.findViewById(R.id.casella4);

        button_profilo = root.findViewById(R.id.casella1);
        button_preferiti = root.findViewById(R.id.casella2);
        button_contatti = root.findViewById(R.id.casella3);
        button_recensione_app = root.findViewById(R.id.casella4);
        //mAuth = FirebaseAuth.getInstance();


        button_profilo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (button_profilo.getVisibility() == View.GONE) {
                    profilo.setVisibility(View.VISIBLE);
                }
                else
                    profilo.setVisibility(View.GONE);
            }
        });













        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}