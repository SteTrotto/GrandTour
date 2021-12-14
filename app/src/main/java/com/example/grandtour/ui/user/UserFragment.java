package com.example.grandtour.ui.user;

import android.os.Bundle;
import android.util.Log;
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
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserBinding binding;
    private EditText Edit_Mail_User;
    private EditText Edit_Pwd_User;
    private Button Button_User;
    private Button Button_Sing_up;

    final private String TAG_S = "USER";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUser;
        UserViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //associa i valori alle variabili
        Edit_Mail_User = root.findViewById(R.id.login_mail);
        Edit_Pwd_User = root.findViewById(R.id.login_password);
        Button_User = root.findViewById(R.id.login);
        Button_Sing_up = root.findViewById(R.id.SingUp);

        //bottone sing in
        Button_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(Edit_Mail_User.getText()));
                Log.d(TAG_S, String.valueOf(Edit_Pwd_User.getText()));
                //controllo log-in




            }
        });

        //bottone sing up
        Button_Sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment = null;
                fragment = new UserSingUp();
                replaceFragment(fragment);
            }
        }

        );

        return root;
    }

     public void replaceFragment(Fragment someFragment)
     {
         FragmentTransaction transaction = getFragmentManager().beginTransaction();
         transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
         transaction.addToBackStack(null);
         transaction.commit();
     }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}