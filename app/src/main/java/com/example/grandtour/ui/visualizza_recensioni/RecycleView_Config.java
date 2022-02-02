package com.example.grandtour.ui.visualizza_recensioni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
        private TextView mRating;
        private TextView mRegione;
        private TextView mViaggio;
        private TextView mNome_Utente;

        private String key;

        public RecensioniItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.recensioni_lista_item, parent, false));

            mTitle =(TextView) itemView.findViewById(R.id.titolo_txtView);
            mRating = (TextView) itemView.findViewById(R.id.rating_txtView);
            mRegione = (TextView) itemView.findViewById(R.id.regione_txtView);
            mViaggio = (TextView) itemView.findViewById(R.id.viaggio_txtView);
            mNome_Utente = (TextView) itemView.findViewById(R.id.nome_Utente_textView);

        }

        public void bind (Recensione recensione, String key)
        {
            mTitle.setText(recensione.getTitoloRecensione());
            mRating.setText(recensione.getRating());
            mRegione.setText(recensione.getRegione());
            mViaggio.setText(recensione.getIdViaggio());
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
