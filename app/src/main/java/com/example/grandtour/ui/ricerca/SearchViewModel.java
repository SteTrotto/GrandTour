package com.example.grandtour.ui.ricerca;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Cerca una Destinazione");
    }

    public LiveData<String> getText() {
        return mText;
    }
}