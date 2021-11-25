package com.example.grandtour.ui.ricerca_recensione;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Ricerca_RecensioneViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public Ricerca_RecensioneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cerca la destinazione");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
