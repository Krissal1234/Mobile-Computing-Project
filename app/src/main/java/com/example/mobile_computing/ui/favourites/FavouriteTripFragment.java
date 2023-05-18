package com.example.mobile_computing.ui.favourites;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.mobile_computing.R;
import com.example.mobile_computing.backend.DbHelper;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FavouriteTripFragment extends Fragment {

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
    private boolean isFavoriteSelected = true;
    private ImageView favouriteButton;
    HotelModel hotel;
    private CardView hotelCard;
    private String flightJson;
    private String hotelJson;
    private TextView arrivalLocationReturn;
    private TextView originLocationReturn;
    private TextView totalDurationReturn;
    private TextView departureTimeReturn;
    private TextView arrivalTimeReturn;
    private TextView returnDateText;
    private TextView returnFlightNumber;
    private DbHelper dbHelper;
    private boolean isNavigatingFromFavorites;
    private LottieAnimationView animationView;
    private TextView hotelText;

    /**
     * Initialises the appropriate elements required.
     * Extracts the flight and hotel data from the bundle.
     * Initialises the favourite button, based on isFavourite check.
     * Adds an on click listener that does the appropriate operations based on the click.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");


        View view = inflater.inflate(R.layout.fragment_trips, container, false);
        dbHelper = new DbHelper(getContext());


        Gson gson = new Gson();
        if (getArguments() != null) {
            flight = getArguments().getParcelable("flightModel");
            hotel = getArguments().getParcelable("hotelModel");

            }
        flightJson = gson.toJson(flight);
        hotelJson = gson.toJson(hotel);



        instantiateTextViews(view, flight, hotel);
        Log.i("flightJSONFav",flightJson);
        Log.i("hotelJSONFav",hotelJson);
        if(dbHelper.isFavorite(flightJson,hotelJson)){
            isFavoriteSelected = true;
            favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
        }
        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavoriteSelected) {
                    // Remove trip  from favorites
                    removeFromFavorites();
                } else {
                    // Add trip to favorites
                    addToFavorites();
                }
                animateFavouriteButton();
            }
        });
        return view;
    }

    /**
     * Removes the trip from the favorites and updates the UI accordingly.
     */
    private void removeFromFavorites() {
        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        isFavoriteSelected = false;
        dbHelper.removeFavorite(flightJson, hotelJson);
        Toast.makeText(getActivity(),
                "Removed Trip from favorites.",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Adds the trip to the favorites and updates the UI accordingly.
     */
    private void addToFavorites() {
        favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
        isFavoriteSelected = true;
        dbHelper.addFavorite(flightJson, hotelJson);
        Toast.makeText(getActivity(),
                "Added Trip to favorites.",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Animates the favourite button when clicked.
     */
    private void animateFavouriteButton() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.favourite_button_anim);
        favouriteButton.startAnimation(animation);
    }

    /**
     * Instantiates the views required for corresponding layout page.
     */
    private void instantiateTextViews(View view, FlightModel flight, HotelModel hotel) {
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

        if(hotel != null) {
            hotelName.setText(hotel.getHotelName());
            hotelPrice.setText(hotel.getPricePerNight());
            hotelAddress.setText(hotel.getAddress());
        }else{
            hotelCard.setVisibility(View.GONE);
            animationView.setVisibility(View.VISIBLE);
            hotelText.setVisibility(View.VISIBLE);
        }
    }
}
