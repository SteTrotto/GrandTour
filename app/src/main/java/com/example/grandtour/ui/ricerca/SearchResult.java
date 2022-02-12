package com.example.grandtour.ui.ricerca;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends Fragment {

    private static Viaggio viaggio;
    private SearchViewModel searchViewModel = SearchFragment.getSearchViewModel();
    private FragmentSearchResultBinding binding;

    private String regione = "test";
    private String mezzo;

    private String durata;

    private boolean search_regione = true;
    private boolean search_mezzo = true;
    private boolean search_durata = true;

    private List<Viaggio> mViaggioList = new ArrayList<>();

    private RecyclerView mRecyclerViewViaggi;
    private ViaggiRecyclerViewAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    final private String TAG_SR = "SEARCH_RESULT";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        readViewModel();
        readDB();

        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mRecyclerViewViaggi = root.findViewById(R.id.search_recyclerview);
        mRecyclerViewViaggi.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerViewViaggi.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), mRecyclerViewViaggi ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        viaggio = adapter.getViaggioList().get(position);
                        openViaggio();
                    }

                    @Override public void onLongItemClick(View view, int position) {

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
        searchViewModel.getRegione().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                regione = s;
                if(regione.equalsIgnoreCase("Regione")) search_regione = false;
            }
        });

        searchViewModel.getMezzo().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mezzo = s;
                if(mezzo.equalsIgnoreCase("mezzo")) search_mezzo = false;
            }
        });

        searchViewModel.getDurata().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                durata = s;
                if(durata.equalsIgnoreCase("Durata:")) search_durata = false;
            }
        });
    }

    public void readDB() {

        myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    for (DataSnapshot postSnapshot: task.getResult().child("Viaggi").getChildren()) {
                        Viaggio v = postSnapshot.getValue(Viaggio.class);

                        if(!search_regione && !search_mezzo && !search_durata)
                                mViaggioList.add(v);

                        if(search_regione && !search_mezzo && !search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione))
                                mViaggioList.add(v);

                        if(!search_regione && search_mezzo && !search_durata)
                            if(v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        if(!search_regione && !search_mezzo && search_durata)
                            if(v.getDurata().equalsIgnoreCase(durata))
                                mViaggioList.add(v);

                        if(search_regione && search_mezzo && !search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione) && v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        if(!search_regione && search_mezzo && search_durata)
                            if(v.getDurata().equalsIgnoreCase(durata) && v.getMezzo().equalsIgnoreCase(mezzo))
                                mViaggioList.add(v);

                        if(search_regione && !search_mezzo && search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione) && v.getDurata().equalsIgnoreCase(durata))
                                mViaggioList.add(v);

                        if(search_regione && search_mezzo && search_durata)
                            if(v.getRegione().equalsIgnoreCase(regione) && v.getMezzo().equalsIgnoreCase(mezzo) && v.getDurata().equalsIgnoreCase(durata))
                                mViaggioList.add(v);
                    }
                    adapter = new ViaggiRecyclerViewAdapter(mViaggioList,
                            new ViaggiRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Viaggio viaggio) {
                                    Log.d(TAG_SR, "onItemClick: " + viaggio);
                                }
                            });
                    mRecyclerViewViaggi.setAdapter(adapter);
                    if(mViaggioList.size() == 0) {
                        Toast.makeText(getContext(), "Non sono presenti viaggi",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static Viaggio getViaggio() {
        return viaggio;
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