package com.example.mobile_computing.ui.flightsPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mobile_computing.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import services.FirebaseService;

public class FlightsFragment extends Fragment {
    private FlightViewModel flightViewModel;
    private String originLocation;
    private String departureDate;
    private String returnDate;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");
        // Inflate the layout for this fragment


        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
        }
        Log.d("location", originLocation);
        flightViewModel = new ViewModelProvider(this).get(FlightViewModel.class);
//        flightViewModel.getFlightData(originLocation, departureDate, returnDate)
//                .observe(getViewLifecycleOwner(), flightList -> {
//                    adapter.setFlights(flightList);
//                });
        //TODO find a way to make a recycler view using the test data and making them clickable to expand into a new page.


        return inflater.inflate(R.layout.fragment_trips, container, false);
    }

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
