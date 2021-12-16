package com.example.grandtour.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.databinding.FragmentUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorResolver;

import java.util.concurrent.Executor;

public class UserFragment extends Fragment {

    private UserViewModel UserViewModel;
    private FragmentUserBinding binding;
    private EditText Edit_Mail_User;
    private EditText Edit_Pwd_User;
    private Button Button_User;
    private Button Button_Sing_up;
    private FirebaseAuth mAuth;
    private final String TAG = "Accedi";

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
        mAuth = FirebaseAuth.getInstance();

        //bottone sing in
        Button_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String.valueOf(editText.getText()) prende il valore in stringa del editText
                Log.d(TAG_S, String.valueOf(Edit_Mail_User.getText()));
                Log.d(TAG_S, String.valueOf(Edit_Pwd_User.getText()));
                //controllo log-in
                signIn(Edit_Mail_User.getText().toString(), Edit_Pwd_User.getText().toString());
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
        });


        return root;
    }

    private boolean validateForm()      //controllo form
    {
        String mail = Edit_Mail_User.getText().toString();
        String password = Edit_Pwd_User.getText().toString();

        boolean valid = true;
        if (TextUtils.isEmpty(mail)) {
            valid = false;
        } else {
        }
        if (TextUtils.isEmpty(password)) {
            valid = false;
        } else {
        }
        return valid;
    }

     public void replaceFragment(Fragment someFragment)
     {
         FragmentTransaction transaction = getFragmentManager().beginTransaction();
         transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
         transaction.addToBackStack(null);
         transaction.commit();
     }

    private void signIn(String email, String password)
    {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            Toast.makeText(getContext(), "Email e/o password Errata.", Toast.LENGTH_SHORT).show();
            // Toast.makeText(Accedi.this, "Email e/o password Errata.", Toast.LENGTH_SHORT).show();
            return;
        }

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Fragment fragment1 = null;
                            fragment1 = new UserLogUtente();
                            replaceFragment(fragment1);

                            if(user.isEmailVerified()) //se mail verificata //da modificare le posizione: cosi accede lo stesso
                            {
                               // Intent in = new Intent(Accedi.this, MainActivity.class);startActivity(in);
                            }
                            else {
                                Log.w(TAG, "mailVerificata:failure");
                                Toast.makeText(getContext(), "Email non ancora verificata. \nControlla il tuo indirizzo di posta.",
                                        Toast.LENGTH_SHORT).show();
                                //Toast.makeText(Accedi.this, "Email non ancora verificata. \nControlla il tuo indirizzo di posta.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getContext(),"Email e/o password Errata.", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(Accedi.this, "Email e/o password Errata.", Toast.LENGTH_SHORT).show();
                            // [START_EXCLUDE]
                            //checkForMultiFactorFailure(task.getException());
                            // [END_EXCLUDE]
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void checkForMultiFactorFailure(Exception e) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        if (e instanceof FirebaseAuthMultiFactorException) {
            Log.w(TAG, "multiFactorFailure", e);
            //Intent intent = new Intent();
            MultiFactorResolver resolver = ((FirebaseAuthMultiFactorException) e).getResolver();
            //intent.putExtra("EXTRA_MFA_RESOLVER", resolver);
            //setResult(MultiFactorActivity.RESULT_NEEDS_MFA_SIGN_IN, intent);
            //finish();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}