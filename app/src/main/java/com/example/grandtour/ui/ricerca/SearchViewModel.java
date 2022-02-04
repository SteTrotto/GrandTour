package com.example.grandtour.ui.ricerca;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mRegione;
    private MutableLiveData<String> mMezzo;
    private MutableLiveData<String> mDurata;

    final private String TAG_SVM = "SEARCH_VIEW_MODEL";

    public SearchViewModel() {
        mRegione = new MutableLiveData<>();
        mMezzo = new MutableLiveData<>();
        mDurata = new MutableLiveData<>();
    }

    public void setmRegione(String regione) {
        this.mRegione.setValue(regione);
    }

    public void setmMezzo(String mezzo) {
        this.mMezzo.setValue(mezzo);
    }

    public void setmDurata(String durata) {this.mDurata.setValue(durata);}

    public LiveData<String> getRegione() {
        return mRegione;
    }

    public LiveData<String> getMezzo() {
        return mMezzo;
    }

    public LiveData<String> getDurata() {return mDurata;}

}