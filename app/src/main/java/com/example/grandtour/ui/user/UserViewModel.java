package com.example.grandtour.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}