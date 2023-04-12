package com.example.mobile_computing.ui.tripsPage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobile_computing.R;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TripsFragment extends Fragment {
    private String originLocation;
    private String departureDate;
    private String returnDate;
    private String price;
    private String destination;
    private String imageUrl;
    private TripsViewModel tripsViewModel;


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
            tripsViewModel = new TripsViewModel();
            instantiateTextViews(view);

        if (getArguments() != null) {
            originLocation = getArguments().getString("originLocation");
            departureDate = getArguments().getString("departureDate");
            returnDate = getArguments().getString("returnDate");
            price = getArguments().getString("price");
            destination = getArguments().getString("destination");
            imageUrl = getArguments().getString("imageUrl");
        }

        tripsViewModel.getFlightDetails(originLocation, departureDate, returnDate,destination).thenAccept(flightDetails -> {
            //Departure Initialisations
            originLocationDeparture.setText(flightDetails.getOrigin());
            arrivalLocationDeparture.setText(flightDetails.getDestination());
            totalDurationDeparture.setText(flightDetails.getTotalDuration());
            departureDateText.setText(flightDetails.getDepartureDate());
            departureTimeDeparture.setText(flightDetails.getLegs().get(0).getDepartureTime());
            returnTimeDeparture.setText(flightDetails.getLegs().get(0).getArrivalTime());
            departureFlightNumber.setText(flightDetails.getLegs().get(0).getFlightNumber());


            //Return Initialisations
            originLocationReturn.setText(flightDetails.getDestination());
            arrivalLocationReturn.setText(flightDetails.getOrigin());
            returnFlightNumber.setText(flightDetails.getLegs().get(1).getFlightNumber());
//            arrivalTimeReturn.setText(flightDetails.getLegs().get(1).getArrivalTime());
            totalDurationReturn.setText(flightDetails.getLegs().get(1).getDuration());


            //get from legs
//            returnDateText.setText(flightDetails.);




        });


        return view;
    }

    private void instantiateTextViews(View view) {
        arrivalLocationDeparture = view.findViewById(R.id.arrivalLocation_departure);
        originLocationDeparture = view.findViewById(R.id.originLocation_departure);
        totalDurationDeparture = view.findViewById(R.id.totalDuration_departure);
        departureIataCodeDeparture = view.findViewById(R.id.departureIataCode_departure);
        returnIataCodeDeparture = view.findViewById(R.id.returnIata_departure);
        departureTimeDeparture = view.findViewById(R.id.departureTime_departure);
        returnTimeDeparture = view.findViewById(R.id.arrivalTime_departures);
        departureDateText = view.findViewById(R.id.departure_date_text);
        departureFlightNumber = view.findViewById(R.id.departure_flight_number);

        arrivalLocationReturn = view.findViewById(R.id.arrivalLocation_return);
        originLocationReturn = view.findViewById(R.id.originLocation_return);
        totalDurationReturn = view.findViewById(R.id.totalDuration_return);
        departureIataCodeReturn = view.findViewById(R.id.departure_Iata_return);
        returnIataCodeReturn = view.findViewById(R.id.returnIata_return);
        departureTimeReturn = view.findViewById(R.id.departureTime_return);
        arrivalTimeReturn = view.findViewById(R.id.arrivalTime_return);
        returnDateText = view.findViewById(R.id.return_date_text);
        returnFlightNumber = view.findViewById(R.id.return_flight_number);

    }


}
