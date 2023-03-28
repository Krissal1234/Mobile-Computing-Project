package com.example.mobile_computing.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.SegmentModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import services.FirebaseService;

public class TripsFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");
        // Inflate the layout for this fragment#
        FirebaseService service = new FirebaseService();
//        service.getCheapestTrips();
        return inflater.inflate(R.layout.fragment_trips, container, false);
    }
    //TODO searchAirport request can be used to find the IATA code however maybe its better to hard code that
    //TODO get flights and hotel data
    //TODO algorithm to find the cheapest combination will be in services


    /*
    Steps: to get the cheapest hotels
    -   first use /search place (param: location), to get hotels in this location. the entity id of each
        hotel will be returned.

    -   Then /search hotel (param: entityId, checkIn, checkOut) to get the hotel data for that particular hotel
        this will probably be done on every hotel returned from the first step

    -   Like this we will receive a list of hotels in the area of choice during the duration requested.

    -   This list can then be sorted by price.

    -   Finally we will have a list of the cheapest hotels available in the location requested during the duration inputted


    Steps to get the cheapest flights

    -   First use /search flights everywhere to get a list of countries sorted by price that is
        available given the dates and origin location inputted

    -   Second we use /search flight to get the flight details to get to the country inputted in the given time frame (departure and return dates)

    -


        - I think the best approach would be to search flights everywhere first,
        then if the user clicks on the country, it will show the cheapest hotels in that area only. This data can then be cached
     */


}
