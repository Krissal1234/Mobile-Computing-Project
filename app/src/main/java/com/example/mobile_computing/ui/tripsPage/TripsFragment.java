package com.example.mobile_computing.ui.tripsPage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_computing.R;
import com.example.mobile_computing.backend.DbHelper;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.example.mobile_computing.model.ReturnFlight;
import com.example.mobile_computing.ui.flightsPage.FlightsFragment;
import com.example.mobile_computing.ui.flightsPage.FlightsRestRepository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    ImageView favouriteButton;
    HotelModel hotel;
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
    private boolean isNavigatingFromFavorites;
    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trips");


        View view = inflater.inflate(R.layout.fragment_trips, container, false);
        isNavigatingFromFavorites = getActivity().getIntent().getBooleanExtra("isNavigatingFromFavorites", false);

        Gson gson = new Gson();
        if (getArguments() != null) {
            flight = getArguments().getParcelable("flightModel");
            if(isNavigatingFromFavorites) {
                hotelJson = gson.toJson(getArguments().getParcelable("hotelModel"));

            }

        }

        instantiateTextViews(view,flight);
        dbHelper = new DbHelper(getContext());

        flightJson = gson.toJson(flight);

        if(!isNavigatingFromFavorites){
            HotelsRestRepository repository = HotelsRestRepository.getInstance();
            repository.fetchHotels(flight.getArrivalDate(), flight.getReturnFlight().getDepartureDate(),flight.getDestination()).observe(getActivity(), new Observer<List<HotelModel>>() {
                @Override
                public void onChanged(List<HotelModel> hotelModels) {

                    if(!hotelModels.isEmpty()){

                        hotelName.setText(hotelModels.get(0).getHotelName());
                        Log.i("hotelName", hotelModels.get(0).getHotelName().toString());
                        hotelPrice.setText(hotelModels.get(0).getPricePerNight());
                        hotelAddress.setText(hotelModels.get(0).getAddress());

                        hotelJson = gson.toJson(hotelModels.get(0));
                        Log.i("hotel", hotelJson);
                        if(dbHelper.isFavorite(flightJson,hotelJson)){
                            isFavoriteSelected = true;
                            favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
                        }
//                    mapView = view.findViewById(R.id.mapView);
//                    mapView.onCreate(savedInstanceState);
//
//                    mapView.getMapAsync(new OnMapReadyCallback() {
//                        @Override
//                        public void onMapReady(GoogleMap map) {
//                            googleMap = map;
//                            LatLng location = new LatLng(37.422, -122.084); // Replace with your desired location
//                            googleMap.addMarker(new MarkerOptions().position(location));
//                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//
//                            // Set an onClickListener for the map
//                            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                                @Override
//                                public void onMapClick(LatLng latLng) {
//                                    // Create a Uri from the location's latitude and longitude
//                                    Uri gmmIntentUri = Uri.parse("geo:" + latLng.latitude + "," + latLng.longitude + "?q=" + latLng.latitude + "," + latLng.longitude);
//
//                                    // Create an Intent with the action VIEW and the location's Uri
//                                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//
//                                    // Set the package to "com.google.android.apps.maps" to open the app if it's installed
//                                    mapIntent.setPackage("com.google.android.apps.maps");
//
//                                    // Check if the intent can be resolved to avoid crashes
//                                    if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                                        startActivity(mapIntent);
//                                    }
//                                }
//                            });
//                        }
//                    });
//                    HotelSelectListener adapter = new HotelRecyclerViewAdapter(getContext(), (ArrayList<HotelModel>) hotelModels, FlightsFragment.this);
//                    recyclerView.setAdapter(adapter);
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    loadingProgressBar.setVisibility(View.GONE);
////                            emptyTextView.setVisibility(View.GONE);
//                    recyclerView.setVisibility(View.VISIBLE);
                    }else{
                        Log.i("hotel","hotels not found");
//                    loadingProgressBar.setVisibility(View.GONE);
//                    animationView.setVisibility(View.VISIBLE);
//                    noFlightsText.setVisibility(View.VISIBLE);
//                    backButton.setVisibility(View.VISIBLE);

                    }

                }
            });


        }


        favouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                ImageView favoriteButton = (ImageView) view.findViewById(R.id.favorite_button);
                if (isFavoriteSelected) {
                    favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    isFavoriteSelected = false;
                    dbHelper.removeFavorite(flightJson,hotelJson);
                    Toast.makeText(getActivity(),
                            "Removed Trip from favourites.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    favouriteButton.setImageResource(R.drawable.ic_baseline_favorite_24_selected);
                    isFavoriteSelected = true;
                    Log.i("flightJson",flightJson );
                    Log.i("hotelJson",hotelJson);
                    dbHelper.addFavorite(flightJson,hotelJson);
                    Toast.makeText(getActivity(),
                            "Added Trip to favourites.",
                            Toast.LENGTH_SHORT).show();
                }
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.favourite_button_anim);
                favouriteButton.startAnimation(animation);

            }
        });

        return view;
    }



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

    }


}
