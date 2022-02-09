package com.example.grandtour.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.adapter.ViaggiRecyclerViewAdapter;
import com.example.grandtour.databinding.FragmentHomeBinding;
import com.example.grandtour.ui.ricerca.SearchViaggioFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private static Viaggio viaggio;
    private List<Viaggio> mViaggioList;

    private RecyclerView mRecyclerViewViaggi;
    private ViaggiRecyclerViewAdapter adapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://grandtour-42d4d-default-rtdb.europe-west1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference();

    final private String TAG_H = "HOME";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mViaggioList = new ArrayList<>();

        readDB();
        Log.e(TAG_H, "------------------HOME FRAGMENT------------------");

        mRecyclerViewViaggi = root.findViewById(R.id.home_recyclerview);
        mRecyclerViewViaggi.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerViewViaggi.addOnItemTouchListener(
                new RecyclerItemClickListenerHome(getContext(), mRecyclerViewViaggi ,new RecyclerItemClickListenerHome.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        viaggio = adapter.getViaggioList().get(position);
                        Log.e(TAG_H, position + "");
                        openViaggio();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openViaggio() {
        Fragment fragment = null;
        fragment = new HomeViaggioFragment();
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static Viaggio getViaggio() {
        return viaggio;
    }

    void readDB() {

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
                        Log.d(TAG_H, "----- " + idViaggio + " ----- ");

                        Viaggio v = postSnapshot.getValue(Viaggio.class);

                        //test v result from DB
                        Log.d(TAG_H, v.getMezzo());
                        Log.d(TAG_H, v.getRegione());
                        Log.d(TAG_H, v.getDurata());
                        Log.d(TAG_H, v.getNomeViaggio());
                        Log.d(TAG_H, v.getTappa1());
                        Log.d(TAG_H, v.getTappa2());
                        Log.d(TAG_H, v.getTappa3());
                        Log.d(TAG_H, v.getTappa4());

                        mViaggioList.add(v);

                        Log.d(TAG_H, "----- " + idViaggio + " ----- ");
                    }

                    List<Viaggio> random = new ArrayList<>();
                    Random casuale = new Random();
                    for (int i = 0; i < mViaggioList.size() && random.size() < 3; i++) {
                        int j = casuale.nextInt(mViaggioList.size());
                        if(random.contains(mViaggioList.get(j)))
                            i--;
                        else
                            random.add(mViaggioList.get(j));
                    }

                    adapter = new ViaggiRecyclerViewAdapter(random,
                            new ViaggiRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Viaggio viaggio) {
                                    Log.d(TAG_H, "onItemClick: " + viaggio);
                                }
                            });
                    mRecyclerViewViaggi.setAdapter(adapter);
                }
            }
        });

    }

}

class RecyclerItemClickListenerHome implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onLongItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListenerHome(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
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