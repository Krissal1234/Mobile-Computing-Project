package com.example.mobile_computing.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mobile_computing.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TripsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trips, container, false);
    }
    //TODO searchAirport request can be used to find the IATA code however maybe its better to hard code that
    //
}
