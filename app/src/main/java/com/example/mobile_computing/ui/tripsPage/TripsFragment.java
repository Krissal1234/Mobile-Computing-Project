package com.example.mobile_computing.ui.tripsPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightModel;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TripsFragment extends Fragment {
    private String originLocation;
    private String departureDate;
    private String returnDate;
    private String price;
    private String destination;
    private String imageUrl;
    private TripsViewModel tripsViewModel;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");


        View view = inflater.inflate(R.layout.fragment_flights, container, false);
            tripsViewModel = new TripsViewModel();
        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
            price = getArguments().getString("price");
            destination = getArguments().getString("destination");
            imageUrl = getArguments().getString("imageUrl");
        }

        tripsViewModel.getFlightDetails(originLocation, departureDate, returnDate,destination).thenAccept(flightDetails -> {


        });


        return view;
    }





}
