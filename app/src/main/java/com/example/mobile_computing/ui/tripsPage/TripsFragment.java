package com.example.mobile_computing.ui.tripsPage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mobile_computing.R;
import com.example.mobile_computing.backend.DbHelper;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import java.util.List;

/**
 * The `TripsFragment` class represents a fragment for displaying trip information in the trips page
 * of the application.
 * It includes methods for handling user interactions, fetching hotels from an API,
 * and managing favorites.
 */
public class TripsFragment extends Fragment {

    private FlightModel flight;
    private TextView arrivalLocationDeparture;
    private TextView originLocationDeparture;
    private TextView totalDurationDeparture;
    private TextView departureTimeDeparture;
    private TextView returnTimeDeparture;
    private TextView departureDateText;
    private TextView departureFlightNumber;
    private TextView hotelName;
    private TextView hotelPrice;
    private TextView hotelAddress;
    private boolean isFavoriteSelected = false;
    private ImageView favouriteButton;
    private CardView hotelCard;
    private String hotelJson;
    private String flightJson;
    private TextView arrivalLocationReturn;
    private TextView originLocationReturn;
    private TextView totalDurationReturn;
    private TextView departureTimeReturn;
    private TextView arrivalTimeReturn;
    private TextView returnDateText;
    private TextView returnFlightNumber;
    private DbHelper dbHelper;
    private LottieAnimationView animationView;
    private TextView hotelText;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");


        View view = inflater.inflate(R.layout.fragment_trips, container, false);



        Gson gson = new Gson();
        // Get the flight model from bundle
        if (getArguments() != null) {
            flight = getArguments().getParcelable("flightModel");
        }


        // Initialize the TextViews for flight and hotel information
        instantiateTextViews(view, flight);

        // Create a new instance of the database helper
        dbHelper = new DbHelper(getContext());

        // Convert the flight model to JSON string
        flightJson = gson.toJson(flight);

        // Fetch hotels from API
        fetchHotelsFromApi(gson, view);

        // Set click listener for the favorite button
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavoriteSelected) {
                    unfavouriteTrip();
                } else {
                    favouriteTrip();
                }
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.favourite_button_anim);
                favouriteButton.startAnimation(animation);
            }
        });

        return view;
    }
    /**
     * Unfavorite the trip by removing it from favorites using the dbHelper method.
     */
    private void unfavouriteTrip() {
        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        isFavoriteSelected = false;
        dbHelper.removeFavorite(flightJson, hotelJson);
        Toast.makeText(getActivity(), "Removed Trip from favourites.", Toast.LENGTH_SHORT).show();
    }
    /**
     * Favourite the trip by adding it to favorites using the dbHelper method.
     */
    private void favouriteTrip() {
        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
        isFavoriteSelected = true;
        dbHelper.addFavorite(flightJson, hotelJson);
        Toast.makeText(getActivity(), "Added Trip to favourites.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Fetch hotels from API using the flights REST repository.
     *If hotels are present from the api's response, the handleHotelsSuccess() is called.
     * @param gson The Gson object for JSON serialization.
     * @param view The fragment's view.
     */
    private void fetchHotelsFromApi(Gson gson, View view){

        HotelsRestRepository repository = HotelsRestRepository.getInstance();
        repository.fetchHotels(flight.getArrivalDate(), flight.getReturnFlight().getDepartureDate(),flight.getDestination()).observe(getActivity(), new Observer<List<HotelModel>>() {
            @Override
            public void onChanged(List<HotelModel> hotelModels) {

                if(!hotelModels.isEmpty()){
                    //Hotels are found.
                    handleHotelsSuccess(hotelModels,gson,view);

                }else{
                        //No hotels are found
                    hotelCard.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);
                    hotelText.setVisibility(View.VISIBLE);
                    hotelJson = "null";

                    if(dbHelper.isFavorite(flightJson,hotelJson)){
                        isFavoriteSelected = true;
                        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
                    }

                }

            }
        });


    }
    /**
     * Handle the success response when hotels are fetched from the API.
     * By setting the appropriate variables.
     * Checking if the curent trip is already in favourites for the heart icon configurations.
     * Sets up the google maps link for the hotel.
     * @param hotelModels The list of hotel models.
     * @param gson        The Gson object for JSON serialization.
     * @param view        The fragment's view.
     */
private void handleHotelsSuccess(List<HotelModel> hotelModels,Gson gson,View view){
    hotelName.setText(hotelModels.get(0).getHotelName());
    hotelPrice.setText(hotelModels.get(0).getPricePerNight());
    hotelAddress.setText(hotelModels.get(0).getAddress());
    hotelJson = gson.toJson(hotelModels.get(0));


    if(dbHelper.isFavorite(flightJson,hotelJson)){
        isFavoriteSelected = true;
        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
    }

    String latitude = hotelModels.get(0).getLatitude();
    String longitude = hotelModels.get(0).getLongitude();
    Button mapButton = view.findViewById(R.id.map_button);
    mapButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String url = "https://www.google.com/maps/search/?api=1&query="+latitude+","+ longitude;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    });
}
    /**
     * Initialize the TextViews for flight and hotel information.
     *
     * @param view   The fragment's view.
     * @param flight The flight model.
     */
    private void instantiateTextViews(View view, FlightModel flight) {
        arrivalLocationDeparture = view.findViewById(R.id.arrivalLocation_departure);
        originLocationDeparture = view.findViewById(R.id.originLocation_departure);
        totalDurationDeparture = view.findViewById(R.id.totalDuration_departure);
        departureTimeDeparture = view.findViewById(R.id.departureTime_departure);
        returnTimeDeparture = view.findViewById(R.id.arrivalTime_departures);
        departureDateText = view.findViewById(R.id.departure_date_text);
        departureFlightNumber = view.findViewById(R.id.departure_flight_number);

        arrivalLocationReturn = view.findViewById(R.id.arrivalLocation_return);
        originLocationReturn = view.findViewById(R.id.originLocation_return);
        totalDurationReturn = view.findViewById(R.id.totalDuration_return);
        departureTimeReturn = view.findViewById(R.id.departureTime_return);
        arrivalTimeReturn = view.findViewById(R.id.arrivalTime_return);
        returnDateText = view.findViewById(R.id.return_date_text);
        returnFlightNumber = view.findViewById(R.id.return_flight_number);


        hotelPrice = view.findViewById(R.id.hotel_price);
        hotelName = view.findViewById(R.id.hotel_name);
        hotelAddress= view.findViewById(R.id.address_text);
        favouriteButton = view.findViewById(R.id.favorite_button);
        hotelCard = view.findViewById(R.id.hotelRow_main_container);
        animationView = view.findViewById(R.id.home_anim);
        hotelText = view.findViewById(R.id.no_hotels);
        //Departure Initialisations
        originLocationDeparture.setText(flight.getOrigin());
        arrivalLocationDeparture.setText(flight.getDestination());
        totalDurationDeparture.setText(flight.getFlightDuration());
        departureDateText.setText(flight.getDepartureDate());
        departureTimeDeparture.setText(flight.getDepartureTime());
        returnTimeDeparture.setText(flight.getArrivalTime());
        departureFlightNumber.setText(flight.getFlightNumber());
        //Return Initialisations
        originLocationReturn.setText(flight.getReturnFlight().getOrigin());
        arrivalLocationReturn.setText(flight.getReturnFlight().getDestination());
        returnFlightNumber.setText(flight.getReturnFlight().getFlightNumber());
        arrivalTimeReturn.setText(flight.getReturnFlight().getArrivalTime());
        departureTimeReturn.setText(flight.getReturnFlight().getDepartureTime());
        returnDateText.setText(flight.getReturnFlight().getArrivalDate());
        totalDurationReturn.setText(flight.getReturnFlight().getFlightDuration());
        hotelText.setVisibility(View.GONE);
        animationView.setVisibility(View.GONE);
    }


}
