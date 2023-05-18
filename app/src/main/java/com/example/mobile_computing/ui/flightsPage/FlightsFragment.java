package com.example.mobile_computing.ui.flightsPage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mobile_computing.R;
//import com.example.mobile_computing.model.FlightDescriptionModel;
import com.example.mobile_computing.model.FlightModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 * Fragment for displaying flights.
 */
public class FlightsFragment extends Fragment implements FlightsSelectListener {
//    private FlightsViewModel flightViewModel;
    private String originLocation;
    private String departureDate;
    private String returnDate;
    private NavController navController;
    private FragmentManager fragmentManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Flights");

        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        ProgressBar loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        TextView noFlightsText = view.findViewById(R.id.no_flights_text);
        Button backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_home);
            }
        });

        loadingProgressBar.setVisibility(View.VISIBLE);
        animationView.setVisibility(View.GONE);
        noFlightsText.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);
        navController = NavHostFragment.findNavController(this);
        RecyclerView recyclerView = view.findViewById(R.id.flightsRecyclerView);

        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
        }
            fetchFlightsFromApi(view);



        return view;
    }
    /**
     * Fetches flights from the API and updates the view accordingly.
     *
     * @param view The view associated with the fragment required by
     *  handleApiError() and handleApiSuccess().
     */
    private void fetchFlightsFromApi(View view) {
        FlightsRestRepository repository = FlightsRestRepository.getInstance();
        repository.fetchFlights(originLocation, departureDate, returnDate).observe(getActivity(), new Observer<List<FlightModel>>() {
            @Override
            public void onChanged(List<FlightModel> flightModels) {
                if (flightModels == null) {
                    handleApiError(view);
                } else {
                    handleApiSuccess(flightModels,view);
                }
            }
        });
    }

    /**
     * Handles the API error case.
     * Displays animation along with a message indicating a problem with the connection to the api.
     * Also provides the user with a back button.
     * @param view The view of the fragment.
     */
    private void handleApiError(View view) {
        ProgressBar loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        TextView noFlightsText = view.findViewById(R.id.no_flights_text);
        Button backButton = view.findViewById(R.id.backButton);

        loadingProgressBar.setVisibility(View.GONE);
        animationView.setVisibility(View.VISIBLE);
        noFlightsText.setText("There has been an error in the request, please check your internet connection.");
        noFlightsText.setTextColor(Color.RED);
        noFlightsText.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
    }

    /**
     * Handles the API success case.
     * removes progress bar, back button and text.
     * Displays flight data or message that indicates that no flight data is found.
     * @param flightModels The list of flight models.
     * @param view The view of the fragment.
     */
    private void handleApiSuccess(List<FlightModel> flightModels, View view) {
        ProgressBar loadingProgressBar = view.findViewById(R.id.loadingProgressBar);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        TextView noFlightsText = view.findViewById(R.id.no_flights_text);
        Button backButton = view.findViewById(R.id.backButton);
        RecyclerView recyclerView = view.findViewById(R.id.flightsRecyclerView);

        if (!flightModels.isEmpty()) {
            FlightsRecyclerViewAdapter adapter = new FlightsRecyclerViewAdapter(getContext(), (ArrayList<FlightModel>) flightModels, FlightsFragment.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            loadingProgressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            loadingProgressBar.setVisibility(View.GONE);
            animationView.setVisibility(View.VISIBLE);
            noFlightsText.setVisibility(View.VISIBLE);
            backButton.setVisibility(View.VISIBLE);
        }
    }


    /**
     * On click method when a flight is clicked.
     * Adds data into a bundle and passes it to the trips fragment with the navController.
     */
    @Override
    public void onItemClicked(FlightModel model) {
        //Direct user to flight_details page
        Bundle bundle = new Bundle();

        bundle.putParcelable("flightModel", model);
        Log.d("navigation","Navigating to trips");

        navController.navigate(R.id.action_nav_flights_to_nav_trips,bundle);

    }



}
