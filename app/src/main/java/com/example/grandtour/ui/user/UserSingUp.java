package com.example.grandtour.ui.user;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.InternetConnection;
import com.example.grandtour.R;
import com.example.grandtour.Utente;
import com.example.grandtour.databinding.FragmentUserSingupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class UserSingUp extends Fragment {

    private FirebaseAuth mAuth;
    private UserViewModel UserViewModel;
    private FragmentUserSingupBinding binding;
    private EditText mail;
    private EditText nome_e_cognome;
    private EditText Data;
    private EditText pwd;
    private Button iscriviti;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    final Calendar myCalendar = Calendar.getInstance();
    private boolean andata;

    final private String myFormat = "dd/MM/yy";
    final private SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

    final private String TAG_S = "USER";
    private final String TAG = "Registrati";


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

        mAuth = FirebaseAuth.getInstance();

        //bottone sing_up
        iscriviti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!InternetConnection.haveInternetConnection(getContext())) {
                    Log.d(TAG_S, "ERRORE DI CONNESSIONE");
                    Toast.makeText(getContext(), "Errore di connessione, verifica la tua rete a internet",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(TAG_S, String.valueOf(mail.getText()));
                Log.d(TAG_S, String.valueOf(pwd.getText()));
                //controllo registrazione
                createAccount(String.valueOf(mail.getText()), String.valueOf(pwd.getText()));
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

    private boolean validateForm()      //controllo form
    {
        String email = mail.getText().toString();
        String password = pwd.getText().toString();
        String nUtente = nome_e_cognome.getText().toString();
        String data_nascita = Data.getText().toString();

        //aggiungere controllo nomi utenti del DB

        boolean valid = true;
        if (TextUtils.isEmpty(email)) {
            valid = false;
        } else if(!email.contains("@")){
            valid = false;
            Toast.makeText(getContext(), "L' email non è inserita correttamente",Toast.LENGTH_SHORT).show();
            //Toast.makeText(UserSingUp.this, "L' email non è inserita correttamente",Toast.LENGTH_SHORT))
        }
        if (TextUtils.isEmpty(password)) {
            valid = false;
        } else {
            if (password.length() < 6) {
                valid = false;
                Toast.makeText(getContext(), "La password deve avere almeno 6 caratteri",Toast.LENGTH_SHORT).show();
            }
        }
        if(TextUtils.isEmpty(nUtente)){
            valid = false;
        }
        else {
            if (nUtente.contains(".")){
                valid = false;
                Toast.makeText(getContext(),"Errore, un carattere non è valido", Toast.LENGTH_SHORT).show();
            }
            if (nUtente.length() < 4){
                valid = false;
                Toast.makeText(getContext(),"Il nome utente deve avere almeno 4 caratteri",Toast.LENGTH_SHORT).show();
            }
        }

        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d("crea account", "createAccount:" + email);


        if (!validateForm()) {
            return;
        }


        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("creazione utente", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            setNome(user);

                            Fragment fragment = null;
                            fragment = new UserFragment();
                            replaceFragment(fragment);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("errore creazione", "createUserWithEmail:failure", task.getException());
                        }

                    }
                });
        // [END create_user_with_email]
    }

    public void replaceFragment(Fragment someFragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setNome(FirebaseUser user) {
        String email = mail.getText().toString();
        int pos = email.indexOf("@");   //posizione@
        email = email.substring(0, pos); //prende la parte di mail prima di @


        String nome = String.valueOf(nome_e_cognome.getText());

        myRef = database.getReference().child("Utenti").child(nome);
        Utente p = new Utente(nome, mail.getText().toString(), pwd.getText().toString(), Data.getText().toString() );  //nomeUtente, monete, email
        myRef.setValue(p);


        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });

    }

}