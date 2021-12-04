package com.example.grandtour.ui.user;

import android.os.Bundle;
import android.util.Log;
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
import com.example.grandtour.databinding.FragmentUserSingupBinding;

public class UserSingUp extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserSingupBinding binding;
    private EditText mail;
    private EditText nome_e_cognome;
    private EditText nome_utente;
    private EditText pwd;
    private Button iscriviti;


    final private String TAG_S = "USER";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserSingupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUser;
        UserViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        nome_e_cognome = root.findViewById(R.id.nome_e_cognome);
        mail=root.findViewById(R.id.mail);
        nome_utente=root.findViewById(R.id.nome_utente);
        pwd=root.findViewById(R.id.pwd);
        iscriviti=root.findViewById(R.id.SingUp);

        //bottone sing_up
        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(nome_utente.getText()));
                Log.d(TAG_S, String.valueOf(pwd.getText()));
                //controllo registrazione




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