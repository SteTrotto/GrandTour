package com.example.grandtour.ui.Recensioni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReviewsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReviewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Scrivi una recensione");
    }

    public LiveData<String> getText() {
        return mText;
    }
}