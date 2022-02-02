package com.example.grandtour.ui.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.google.firebase.auth.FirebaseUser;

public class UserLogUtente extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserLogBinding binding;

    private TextView profilo;
    private TextView preferiti;
    private TextView contatti;
    private TextView recensione_app;
    private ImageButton button_profilo;
    private ImageButton button_preferiti;
    private ImageButton button_contatti;
    private ImageButton button_recensione_app;

    private FirebaseAuth mAuth;
    private final String TAG = "HOME";

    final private String TAG_S = "USER";

    private TextView email;

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

        button_profilo = root.findViewById(R.id.button1);
        button_preferiti = root.findViewById(R.id.button2);
        button_contatti = root.findViewById(R.id.button3);
        button_recensione_app = root.findViewById(R.id.button4);
        //mAuth = FirebaseAuth.getInstance();


        button_profilo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (profilo.getVisibility() == View.GONE) {
                    profilo.setVisibility(View.VISIBLE);
                }
                else
                    profilo.setVisibility(View.GONE);
            }
        });


        email = root.findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user =  mAuth.getCurrentUser();
        email.setText(user.getEmail());











        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}