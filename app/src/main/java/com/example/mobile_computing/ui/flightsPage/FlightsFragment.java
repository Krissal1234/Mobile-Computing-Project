package com.example.mobile_computing.ui.flightsPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobile_computing.R;
//import com.example.mobile_computing.model.FlightDescriptionModel;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.ui.tripsPage.TripsFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FlightsFragment extends Fragment implements FlightsSelectListener {
//    private FlightsViewModel flightViewModel;
    private String originLocation;
    private String departureDate;
    private String returnDate;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Flights");


        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.flightsRecyclerView);
//        flightViewModel = new ViewModelProvider(this).get(FlightsViewModel.class);

        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
        }
            if(checkCache(originLocation,departureDate,returnDate)){


            }else {
                FlightsRestRepository repository = FlightsRestRepository.getInstance();
                repository.fetchFlights(originLocation,departureDate).observe(getActivity(), new Observer<List<FlightModel>>() {
                    @Override
                    public void onChanged(List<FlightModel> flightModels) {
                        Log.i("flights",flightModels.toString());
                        FlightsRecyclerViewAdapter adapter = new FlightsRecyclerViewAdapter(getContext(), (ArrayList<FlightModel>) flightModels, FlightsFragment.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                });

//
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
    public void onItemClicked(FlightModel model) {
        //Direct user to flight_details page
        Bundle bundle = new Bundle();

        bundle.putParcelable("flightModel", model);
        Log.d("navigation","Navigating to trips");

        navController.navigate(R.id.action_nav_flights_to_nav_trips,bundle);

    }



}
