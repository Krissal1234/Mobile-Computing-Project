package com.example.mobile_computing.ui.favourites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mobile_computing.R;
import com.example.mobile_computing.backend.DbHelper;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.example.mobile_computing.model.TripModel;
import com.example.mobile_computing.ui.tripsPage.TripsFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FavouritesFragment extends Fragment implements FavouritesSelectListener {

    private List<TripModel> trips;
    private List<FlightModel> flightData;
    private List<HotelModel> hotelData;
    private boolean isFavoriteSelected = true;
    private String flightJson;
    private String hotelJson;
    private NavController navController;
    private LottieAnimationView animationView;
    private TextView no_favourites_text;
    private RecyclerView recyclerView;
    private DbHelper dbHelper;
    private Gson gson;

    /**
     * Initializes the view and retrieves necessary data from the database.
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        gson = new Gson();
        recyclerView = view.findViewById(R.id.favourites_recyclerView);
        dbHelper = new DbHelper(getContext());
        navController = NavHostFragment.findNavController(this);

        initializeDataAndView(view);
        loadTripData();
        setupRecyclerView();

        return view;
    }

    /**
     * Initializes the animation view and text view based on the population of the favourite trips
     * in the database.
     *
     * @param view The root view of the fragment.
     */
    private void initializeDataAndView(View view) {
        trips = dbHelper.getAllData();
        animationView = view.findViewById(R.id.favourites_no_found);
        no_favourites_text = view.findViewById(R.id.no_favourites_text);

        if (trips.isEmpty()) {
            animationView.setVisibility(View.VISIBLE);
            no_favourites_text.setVisibility(View.VISIBLE);
        } else {
            animationView.setVisibility(View.GONE);
            no_favourites_text.setVisibility(View.GONE);
        }
    }

    /**
     * Retrieves flight and hotel data from the trip models and populates the corresponding lists
     * to be used by the recycler view.
     */
    private void loadTripData() {
        flightData = new ArrayList<>();
        hotelData = new ArrayList<>();

        for (TripModel trip : trips) {
            flightJson = trip.getFlightData();
            hotelJson = trip.getHotelData();
            Log.i("hotelJson", hotelJson);
            flightData.add(gson.fromJson(flightJson, FlightModel.class));
            hotelData.add(gson.fromJson(hotelJson, HotelModel.class));
        }
    }

    /**
     * Sets up the RecyclerView with the adapter and layout manager.
     */
    private void setupRecyclerView() {
        FavouritesRecyclerViewAdapter adapter = new FavouritesRecyclerViewAdapter(getContext(),
                (ArrayList<FlightModel>) flightData, (ArrayList<HotelModel>) hotelData,
                FavouritesFragment.this);
        adapter.setOnFavouriteClickListener(new FavouritesRecyclerViewAdapter
                .OnFavouriteClickListener() {
            @Override
            public void onFavouriteClick(int position) {
                Log.i("favourite_button", "pressed");
                dbHelper.removeFavorite(gson.toJson(flightData.get(position)),
                        gson.toJson(hotelData.get(position)));
                flightData.remove(position);
                hotelData.remove(position);
                adapter.notifyItemRemoved(position);

                if (adapter.getItemCount() == 0) {
                    animationView.setVisibility(View.VISIBLE);
                    no_favourites_text.setVisibility(View.VISIBLE);
                }
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onItemClicked(FlightModel flightModel, HotelModel hotelModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("flightModel", flightModel);
        bundle.putParcelable("hotelModel", hotelModel);
        Log.d("navigation", "Navigating to trips");

        navController.navigate(R.id.action_nav_favourites_to_nav_favourite_trip, bundle);
    }
}
