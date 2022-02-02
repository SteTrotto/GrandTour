package com.example.grandtour.ui.visualizza_recensioni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.grandtour.R;

import java.util.List;

public class RecycleView_Config {
    private Context mContext;
    private RecensioniAdapter mRecensioniAdapter;

    public void setConfig (RecyclerView recyclerView, Context context, List<Recensione> recensioni, List<String> keys ){
        mContext =context;
        mRecensioniAdapter =new RecensioniAdapter(recensioni, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mRecensioniAdapter);
    }

    class RecensioniItemView extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private RatingBar mRating;
        private TextView mNome_Utente;
        private TextView mCorpo_Recensione;

        private String key;

        public RecensioniItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.recensioni_lista_item, parent, false));

            mTitle =(TextView) itemView.findViewById(R.id.titolo_txtView);
            mRating = (RatingBar) itemView.findViewById(R.id.rating_ratingBar);
            mCorpo_Recensione = (TextView) itemView.findViewById(R.id.corpo_Recensione);
            mNome_Utente = (TextView) itemView.findViewById(R.id.nome_Utente_textView);

        }

        public void bind (Recensione recensione, String key)
        {
            mTitle.setText(recensione.getTitoloRecensione());
            mRating.setRating(Float.parseFloat(recensione.getRating()));
            mCorpo_Recensione.setText(recensione.getCorpoRecensione());
            mNome_Utente.setText(recensione.getNome_Utente());
            this.key=key;
        }

     }
    class RecensioniAdapter extends RecyclerView.Adapter<RecensioniItemView>{
        private List<Recensione> mRecensineList;
        private List<String> mKeys;

        public RecensioniAdapter(List<Recensione> mRecensineList, List<String> mKeys) {
            this.mRecensineList = mRecensineList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public RecensioniItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecensioniItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecensioniItemView holder, int position) {
            holder.bind(mRecensineList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mRecensineList.size();
        }
    }
}
