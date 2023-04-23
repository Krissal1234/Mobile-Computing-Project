package com.example.mobile_computing.ui.tripsPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.ReturnFlight;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TripsFragment extends Fragment {
    private String originLocation;
    private String departureDate;
    private String departureTime;
    private String returnDate;
    private String price;
    private String destination;
    private String imageUrl;
    private String airline;
    private String flightNumber;
    private String totalDuration;
    private ReturnFlight returnFlight;

    private FlightModel flight;
//    private TripsViewModel tripsViewModel;


    private TextView arrivalLocationDeparture;
    private TextView originLocationDeparture;
    private TextView totalDurationDeparture;
    private TextView departureIataCodeDeparture;
    private TextView returnIataCodeDeparture;
    private TextView departureTimeDeparture;
    private TextView returnTimeDeparture;
    private TextView departureDateText;
    private TextView departureFlightNumber;


    private TextView arrivalLocationReturn;
    private TextView originLocationReturn;
    private TextView totalDurationReturn;
    private TextView departureIataCodeReturn;
    private TextView returnIataCodeReturn;
    private TextView departureTimeReturn;
    private TextView arrivalTimeReturn;
    private TextView returnDateText;
    private TextView returnFlightNumber;


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
//            tripsViewModel = new TripsViewModel();
            instantiateTextViews(view);

        if (getArguments() != null) {

            flight = getArguments().getParcelable("flightModel");

//            originLocation = getArguments().getString("originLocation");
//            departureDate = getArguments().getString("departureDate");
//            returnDate = getArguments().getString("returnDate");
//            price = getArguments().getString("price");
//            totalDuration = getArguments().getString("totalDuration");
//            flightNumber = getArguments().getString("flightNumber");
//            destination = getArguments().getString("destination");
//            imageUrl = getArguments().getString("imageUrl");
        }

//        tripsViewModel.getFlightDetails(originLocation, departureDate, returnDate,destination).thenAccept(flightDetails -> {
//            //Departure Initialisations
            originLocationDeparture.setText(flight.getOrigin());
            arrivalLocationDeparture.setText(flight.getDestination());
            totalDurationDeparture.setText(flight.getFlightDuration());
            departureDateText.setText(flight.getDepartureDate());
            departureTimeDeparture.setText(flight.getDepartureTime());
            returnTimeDeparture.setText(flight.getArrivalTime());
            departureFlightNumber.setText(flight.getFlightNumber());
            returnFlight = flight.getReturnFlight();
            //Return Initialisations
            originLocationReturn.setText(flight.getReturnFlight().getOrigin());
            arrivalLocationReturn.setText(flight.getReturnFlight().getDestination());
            returnFlightNumber.setText(flight.getReturnFlight().getFlightNumber());
            arrivalTimeReturn.setText(flight.getReturnFlight().getArrivalTime());
            departureTimeReturn.setText(flight.getReturnFlight().getDepartureTime());
            returnDateText.setText(flight.getReturnFlight().getArrivalDate());

            totalDurationReturn.setText(flight.getReturnFlight().getFlightDuration());




        return view;
    }

    private void instantiateTextViews(View view) {
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

    }


}
