package com.example.mobile_computing.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> departureDate = new MutableLiveData<>();
    private MutableLiveData<String> returnDate = new MutableLiveData<>();
    private MutableLiveData<String> originLocation = new MutableLiveData<>();



}