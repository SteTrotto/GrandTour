package com.example.grandtour.ui.ricerca_recensione;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.grandtour.R;
import com.example.grandtour.Recensione;
import com.example.grandtour.databinding.FragmentRicercaRecensioneBinding;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;


import java.util.ArrayList;
import java.util.HashMap;


  public class Ricerca_RecensioneFragment extends Fragment /*AppCompatActivity*/ {

    private Ricerca_RecensioneViewModel ricerca_recViewModel;
    private FragmentRicercaRecensioneBinding binding;





      public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ricerca_recViewModel = new ViewModelProvider(this).get(Ricerca_RecensioneViewModel.class);

        binding = FragmentRicercaRecensioneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //riempiamo lo spinner
        View v = inflater.inflate(R.layout.fragment_ricerca_recensione,container,false);

        //prendo i due parametri spinner
        Spinner a = (Spinner) v.findViewById(R.id.spinner_search_regione);

        Spinner b = (Spinner) v.findViewById(R.id.spinner_search_viaggio); //viene settato dopo

        RatingBar rate=(RatingBar) v.findViewById(R.id.ratingBar);

        EditText titolo=(EditText) v.findViewById(R.id.editTextTextPersonName);

        EditText corpo=(EditText) v.findViewById(R.id.editTextCommento);

        Button invia;




       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.regione, android.R.layout.simple_spinner_item);

        b.setAdapter(adapter);
        */

        a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                /*
                Log.d("myTag", "This is my message");
               if(pos == 1) { //se abruzzo è selezionato
                   Log.d("myTag", "Sei in abruzzo");
                }*/
                ArrayAdapter<CharSequence> adapter;
                switch(pos) {

                    case 1:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiAbruzzo,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 2:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiBasilicata,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 3:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiCalabria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 4:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiCampania,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 5:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiEmilia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 6:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiFriuli,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 7:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLazio,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 8:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLiguria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 9:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiLombardia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 10:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiMarche,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 11:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiMolise,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 12:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiPiemonte,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 13:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiPuglia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 14:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiSardegna,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 15:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiSicilia,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 16:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiToscana,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 17:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiTrentino,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 18:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiUmbria,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 19:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiAosta,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                    case 20:
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.ViaggiVeneto,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;


                    default:
                        //contiene anche il case 0
                        adapter = ArrayAdapter
                                .createFromResource(view.getContext(), R.array.Iniziale,
                                        android.R.layout.simple_spinner_dropdown_item);
                        b.setAdapter(adapter);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
            });

        //Riferimento al bottone invia
          invia=(Button) v.findViewById(R.id.button2);

          //colleghamo il database
          FirebaseDatabase database= FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app/");
          DatabaseReference myRef=database.getReference();

          invia.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           //login
                                           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                           if (user != null) {

                                               //info utente
                                               //nome
                                               String name = user.getDisplayName();
                                               //id
                                               String uid = user.getUid();

                                               //prendo il campo Regione
                                               String texta = a.getSelectedItem().toString();

                                               //prendo il campo Viaggio
                                               String textb = b.getSelectedItem().toString();

                                               //prendo il capo Rating
                                               float ratingValue =  rate.getRating();
                                               String SRatingValue=String.valueOf(ratingValue);
                                               Log.d("Prova rating", SRatingValue);

                                               //prendo il campo Titolo
                                               String STitolo=titolo.getText().toString().trim();
                                               Log.d("Prova titolo", STitolo);

                                               //prendo il campo Corpo
                                               String SCorpo=corpo.getText().toString().trim();
                                               Log.d("Prova corpo", SCorpo);

                                               HashMap<String, String> val=new HashMap<String, String>();
                                               val.put("id_Utente", uid);
                                               val.put("nome_Utente", name);
                                               val.put("regione",texta);
                                               val.put("idViaggio", textb);
                                               val.put("rating", SRatingValue);
                                               val.put("titoloRecensione", STitolo);
                                               val.put("corpoRecensione", SCorpo);


                                               //rec =new Recensione(texta,textb, ratingValue, STitolo, SCorpo);
                                               myRef.child("Recensioni").push().setValue(val);





                                           }
                                           else
                                           {
                                               Log.d("Log in", "nologgin");

                                           }



                                       }
                                   });

          //Firebase.setAndroidContext(getApplicationContext());

        return v; //or return root

    }

  }


                    /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,
                            getResources().getStringArray(R.array.viaggiAbruzzo));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    */


       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);








