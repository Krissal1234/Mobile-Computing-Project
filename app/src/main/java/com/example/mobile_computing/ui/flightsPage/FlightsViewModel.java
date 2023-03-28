package com.example.mobile_computing.ui.flightsPage;
import androidx.lifecycle.ViewModel;

import com.example.mobile_computing.model.FlightDescriptionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import services.FirebaseService;

class FlightViewModel extends ViewModel {
    private List<FlightDescriptionModel> flightData = new ArrayList<>();

    public List<FlightDescriptionModel> getFlightData(String originLocation, String departureDate, String  returnDate) {
        fetchFlightsAsync(originLocation, departureDate, returnDate);
        return flightData;
    }

    private void fetchFlightsAsync(String originLocation, String departureDate, String  returnDate) {
        FirebaseService service = new FirebaseService();
        //Calls the method asynchronously
        CompletableFuture.supplyAsync(() -> {
            List<FlightDescriptionModel> flightList;
            flightList = service.getFlightsEverywhere(originLocation, departureDate, returnDate);
            return flightList;
        }).thenAccept(flightList -> {
            flightData.clear();
            flightData.addAll(flightList);
        });
    }
}
