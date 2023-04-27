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

import com.example.mobile_computing.R;
import com.example.mobile_computing.backend.DbHelper;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.example.mobile_computing.model.TripModel;
import com.example.mobile_computing.ui.tripsPage.TripsFragment;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;


public class FavouritesFragment extends Fragment implements FavouritesSelectListener{
    private DbHelper dbHelper;
    private List<TripModel> trips;
    private List<FlightModel> flightData;
    private List<HotelModel> hotelData;
    private boolean isFavoriteSelected = true;
    private ImageView favouriteButton;
    private String flightJson;
    private String hotelJson;
    private NavController navController;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        Gson gson = new Gson();
        RecyclerView recyclerView = view.findViewById(R.id.favourites_recyclerView);
        DbHelper dbHelper = new DbHelper(getContext());
        navController = NavHostFragment.findNavController(this);
        trips = dbHelper.getAllData();
        if(trips.isEmpty()){
            //TODO no favourites
        }

        flightData = new ArrayList<>();
        hotelData = new ArrayList<>();
        for (TripModel trip : trips) {

            flightJson = trip.getFlightData();
            hotelJson = trip.getHotelData();
            flightData.add(gson.fromJson(flightJson,FlightModel.class));
            hotelData.add(gson.fromJson(hotelJson,HotelModel.class));

        }


        FavouritesRecyclerViewAdapter adapter = new FavouritesRecyclerViewAdapter(getContext(), (ArrayList<FlightModel>) flightData, (ArrayList<HotelModel>)hotelData, FavouritesFragment.this);
        adapter.setOnFavouriteClickListener(new FavouritesRecyclerViewAdapter.OnFavouriteClickListener() {
            @Override
            public void onFavouriteClick(int position) {
                Log.i("favourite_button", "pressed");
                dbHelper.removeFavorite(gson.toJson(flightData.get(position)),gson.toJson(hotelData.get(position)));
                flightData.remove(position);
                hotelData.remove(position);
                adapter.notifyItemRemoved(position);
            }

        });
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }


    @Override
    public void onItemClicked(FlightModel flightModel, HotelModel hotelModel) {
        Bundle bundle = new Bundle();

        bundle.putParcelable("flightModel", flightModel);
        bundle.putParcelable("hotelModel", hotelModel);
        Log.d("navigation","Navigating to trips");

        navController.navigate(R.id.action_nav_favourites_to_nav_favourite_trip, bundle);

    }


}