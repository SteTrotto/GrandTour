package com.example.grandtour.ui.ricerca_visualizza;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Ricerca_VisualizzaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Ricerca_VisualizzaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Scrivi una recensione");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
