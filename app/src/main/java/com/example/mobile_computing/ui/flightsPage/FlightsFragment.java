package com.example.mobile_computing.ui.flightsPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightDescriptionModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightsFragment extends Fragment implements FlightsSelectListener {
    private FlightsViewModel flightViewModel;
    private String originLocation;
    private String departureDate;
    private String returnDate;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Flights");


        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.flightsRecyclerView);
        flightViewModel = new ViewModelProvider(this).get(FlightsViewModel.class);

        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
        }
            if(checkCache(originLocation,departureDate,returnDate)){


            }else {

                flightViewModel.getFlightData(originLocation, departureDate, returnDate).thenAccept(flightList -> {
                    FlightsRecyclerViewAdapter adapter = new FlightsRecyclerViewAdapter(getContext(), flightList, this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                });
            }
        navController = NavHostFragment.findNavController(this);
        return view;
    }

    private boolean checkCache(String origin, String departureDate, String returnDate) {
        String cacheKey = origin + departureDate + returnDate;

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("api_cache", Context.MODE_PRIVATE);
        String cache = sharedPreferences.getString(cacheKey, null);

        if(cache == null){
            return false;
        }else{
          return true;
        }

    }

    //OnClick method when a Card is clicked
    @Override
    public void onItemClicked(FlightDescriptionModel model) {
        //Direct user to flight_details page
        Bundle bundle = new Bundle();
        bundle.putString("originLocation", originLocation);
        bundle.putString("departureDate", departureDate);
        bundle.putString("returnDate", returnDate);
        bundle.putString("destination", model.getCountry());
        bundle.putString("imageUrl", model.getImageUrl());
        bundle.putString("price", model.getPrice());
        Log.d("navigation","Navigating to trips");
        navController.navigate(R.id.action_nav_flights_to_nav_trips,bundle);

    }




}
