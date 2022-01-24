package com.example.grandtour.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.MainActivity;
import com.example.grandtour.R;
import com.example.grandtour.Viaggio;
import com.example.grandtour.ui.ricerca.SearchResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;
import com.bumptech.glide.Glide;

public class ViaggiRecyclerViewAdapter extends RecyclerView.Adapter<ViaggiRecyclerViewAdapter.ViaggioViewHolder>{

    // To detect a click on the RecyclerView items
    public interface OnItemClickListener {
        void onItemClick(Viaggio viaggio);
    }

    private final List<Viaggio> mViaggioList;
    private final OnItemClickListener mOnItemClickListener;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference pathReference;

    public ViaggiRecyclerViewAdapter(List<Viaggio> viaggioList, OnItemClickListener onItemClickListener) {
        this.mViaggioList = viaggioList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViaggioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.row_result, parent, false);

        return new ViaggioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViaggioViewHolder holder, int position) {
        holder.bind(mViaggioList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mViaggioList != null) {
            return mViaggioList.size();
        }

        return 0;
    }

    public List<Viaggio> getViaggioList() {
        return mViaggioList;
    }

    public class ViaggioViewHolder extends RecyclerView.ViewHolder {

        //private final TextView textViewNewsTitle;
        private final TextView textViewViaggioSource;
        private final ImageView imageViewViaggioImage;

        public ViaggioViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.textViewNewsTitle = itemView.findViewById(R.id.img);
            this.textViewViaggioSource = itemView.findViewById(R.id.info_text);
            this.imageViewViaggioImage = itemView.findViewById(R.id.info_image);
        }

        public void bind(Viaggio viaggio) {
            //this.textViewNewsTitle.setText(viaggio.getTitle());
            this.textViewViaggioSource.setText(viaggio.getNomeViaggio());

            String path = viaggio.getRegione() + "/" + viaggio.getRegione() + ".jpg";
            pathReference = storageRef.child(path);
            Context c = SearchResult.getContesto();
            Glide.with(c)
                    .load(pathReference)
                    .into(this.imageViewViaggioImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(viaggio);
                }
            });
        }
    }


}
