package com.example.grandtour.ui.ricerca;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.adapter.ViaggiRecyclerViewAdapter;
import com.example.grandtour.databinding.FragmentSearchResultBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class SearchResult extends Fragment {

    private static Viaggio viaggio;
    private SearchViewModel searchViewModel = SearchFragment.getSearchViewModel();
    private FragmentSearchResultBinding binding;

    private String regione = "test";
    //private Date andata;
    //private Date ritorno;
    private String mezzo;

    private String durata;

    private boolean search_regione = true;
    private boolean search_mezzo = true;
    private boolean search_durata = true;


    private ListView listViewResult;

    private List<Viaggio> mViaggioList = new ArrayList<>();

    RecyclerView mRecyclerViewViaggi;
    ViaggiRecyclerViewAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    //final private String myFormat = "dd/MM/yy"; //In which you need put here
    //final private SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALY);

    final private String TAG_SR = "SEARCH_RESULT";


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference imagesRef = storageRef.child("lombardia");
    StorageReference spaceRef = storageRef.child("lombardia/Lombardia.jpg");
    StorageReference pathReference = storageRef.child("lombardia/Lombardia.jpg");
    static private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getContext();

        readViewModel();    //function for the read of user form value
        readDB();           //function for the read of the Database

        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //searchViewModel.check();

        //listViewResult = root.findViewById(R.id.search_list_result); //inutile se uso recyclerView

        mRecyclerViewViaggi = root.findViewById(R.id.search_recyclerview);
        mRecyclerViewViaggi.setLayoutManager(new LinearLayoutManager(getContext()));


        mRecyclerViewViaggi.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerViewViaggi ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        List<Viaggio> lv = adapter.getViaggioList();
                        viaggio = lv.get(position);
                        openViaggio();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return root;
    }

    public void openViaggio() {
        Fragment fragment = null;
        fragment = new SearchViaggioFragment();
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void readViewModel() {
        //super.onStart();
        searchViewModel.getRegione().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                regione = s;
                if(regione.equalsIgnoreCase("regione")) search_regione = false;
            }
        });

        searchViewModel.getMezzo().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mezzo = s;
                if(mezzo.equalsIgnoreCase("mezzo")) search_mezzo = false;
            }
        });

        searchViewModel.getRegione().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                durata = s;
                if(durata.equalsIgnoreCase("Durata:")) search_durata = false;
            }
        });

        /*
        searchViewModel.getAndata().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                andata = date;
            }
        });

        searchViewModel.getRitorno().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                ritorno = date;
            }
        });
*/
    }

    public void readDB() {
/*
        Log.d(TAG_SR, "-----  ----- ");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot postSnapshot: dataSnapshot.child("Viaggi").getChildren()) {

                    String idViaggio = postSnapshot.getKey();
                    Log.d(TAG_SR, "----- " + idViaggio + " ----- ");

                    Viaggio v = postSnapshot.getValue(Viaggio.class);

                    //add v in a list/vector/listView/...

                    mViaggioList.add(v);

                    ///test v result from DB
                    Log.d(TAG_SR, v.getDataPartenza());
                    Log.d(TAG_SR, v.getDataRitorno());
                    Log.d(TAG_SR, v.getDestinazione());
                    Log.d(TAG_SR, v.getIdItinerario());
                    Log.d(TAG_SR, v.getMezzo());
                    Log.d(TAG_SR, v.getRegione());

                    Log.d(TAG_SR, "----- " + idViaggio + " ----- ");
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("nome", "Failed to read value.", error.toException());
            }
        });
*/

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    for (DataSnapshot postSnapshot: task.getResult().child("Viaggi").getChildren()) {

                        String idViaggio = postSnapshot.getKey();
                        Log.d(TAG_SR, "----- " + idViaggio + " ----- ");

                        Viaggio v = postSnapshot.getValue(Viaggio.class);

                        //add v in a list/vector/listView/...
                        //comparison user search form with DB data

                        Log.d(TAG_SR, regione);
/*
                        //miss comparison of the Date
                        Date a = new Date();
                        try {
                            a = sdf.parse(v.getDataPartenza());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date r = new Date();
                        try {
                            r = sdf.parse(v.getDataRitorno());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        //controlli per la ricerca senza filtri
                        //solo su regione e mezzo


                        boolean compareDate = true;
                        if(a.before(andata) && !a.equals(andata)) {  //se A vigggio prima A ricerca
                            compareDate = false;
                            Log.d(TAG_SR, "ANDATA: viaggio prima ricerca");
                            Log.e(TAG_SR, a.toString());
                            Log.e(TAG_SR, andata.toString());
                        }
                        if(r.after(ritorno) && !r.equals(ritorno)) { //se R viaggio dopo R ricerca
                            compareDate = false;
                            Log.d(TAG_SR, "RITORNO: viaggio dopo ricerca");
                            Log.e(TAG_SR, r.toString());
                            Log.e(TAG_SR, ritorno.toString());
                        }

                        if(!search_regione && !search_mezzo)
                            if(compareDate)
                                mViaggioList.add(v);

                        if(!search_regione)
                            if(v.getMezzo().equalsIgnoreCase(mezzo) && compareDate)
                                mViaggioList.add(v);

                        if(!search_mezzo)
                            if(v.getRegione().equalsIgnoreCase(regione) && compareDate)
                                mViaggioList.add(v);

                        if(search_mezzo && search_regione)
                            if(v.getRegione().equalsIgnoreCase(regione) && v.getMezzo().equalsIgnoreCase(mezzo) && compareDate)
                                mViaggioList.add(v);
*/
                        //Log.d(TAG_SR, v.getDataPartenza());
                        //Log.d(TAG_SR, v.getDataRitorno());
                        //Log.d(TAG_SR, v.getDestinazione());
                        //Log.d(TAG_SR, v.getIdItinerario());
                        //test v result from DB
                        Log.d(TAG_SR, v.getMezzo());
                        Log.d(TAG_SR, v.getRegione());
                        Log.d(TAG_SR, v.getDurata());
                        Log.d(TAG_SR, v.getNomeViaggio());
                        Log.d(TAG_SR, v.getTappa1());
                        Log.d(TAG_SR, v.getTappa2());
                        Log.d(TAG_SR, v.getTappa3());
                        Log.d(TAG_SR, v.getTappa4());

                        if(!search_mezzo && !search_regione && !search_durata)
                            mViaggioList.add(v);

                        if(!search_regione)
                            if(v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        if(!search_mezzo)
                            if(v.getRegione().equalsIgnoreCase(regione))
                                mViaggioList.add(v);
/*
                        if(!search_regione && !search_durata)
                            if(v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        if(!search_mezzo && !search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione))
                                mViaggioList.add(v);

                        if(!search_mezzo && !search_regione)
                            if(v.getDurata().equalsIgnoreCase(durata))
                                mViaggioList.add(v);
*/
                        if(search_mezzo && search_regione && search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione) && v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        Log.d(TAG_SR, "----- " + idViaggio + " ----- ");
                    }
                    adapter = new ViaggiRecyclerViewAdapter(mViaggioList,
                            new ViaggiRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Viaggio viaggio) {
                                    Log.d(TAG_SR, "onItemClick: " + viaggio);
                                }
                            });
                    mRecyclerViewViaggi.setAdapter(adapter);
                }
            }
        });



    }

    public static Viaggio getViaggio() {
        return viaggio;
    }

    public static Context getContext2() {
        return context;
    }
}

class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }


    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}


}