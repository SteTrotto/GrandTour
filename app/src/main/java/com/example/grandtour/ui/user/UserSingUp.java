package com.example.grandtour.ui.user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.grandtour.ui.ricerca.SearchFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserSingUp extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserSingupBinding binding;
    private EditText mail;
    private EditText nome_e_cognome;
    private EditText Data;
    private EditText pwd;
    private Button iscriviti;

    final Calendar myCalendar = Calendar.getInstance();
    private boolean andata;

    final private String myFormat = "dd/MM/yy"; //In which you need put here
    final private SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

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
        pwd=root.findViewById(R.id.pwd);
        Data=root.findViewById(R.id.DataNascita);
        iscriviti=root.findViewById(R.id.SingUp);

        //bottone sing_up
        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(mail.getText()));
                Log.d(TAG_S, String.valueOf(pwd.getText()));
                //controllo registrazione




            }
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                andata = true;
                // TODO Auto-generated method stub
                new DatePickerDialog(UserSingUp.this.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        return root;
    }



    private void updateLabel() {
        if(andata)  Data.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}