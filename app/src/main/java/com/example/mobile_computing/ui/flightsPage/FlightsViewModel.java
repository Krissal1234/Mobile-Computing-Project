package com.example.mobile_computing.ui.flightsPage;

import androidx.lifecycle.ViewModel;

import com.example.mobile_computing.model.FlightDescriptionModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import services.FirebaseService;

public class FlightsViewModel extends ViewModel {
    private ArrayList<FlightDescriptionModel> flightData = new ArrayList<>();


    public CompletableFuture<ArrayList<FlightDescriptionModel>> getFlightData(String originLocation, String departureDate, String  returnDate) {
        CompletableFuture<ArrayList<FlightDescriptionModel>> future = new CompletableFuture<>();
        fetchFlightsAsync(originLocation, departureDate, returnDate).thenRun(() -> future.complete(flightData));
        return future;
    }

    private CompletableFuture<Void> fetchFlightsAsync(String originLocation, String departureDate, String  returnDate) {
        FirebaseService service = new FirebaseService();
        return CompletableFuture.supplyAsync(() -> service.getTestFlightsEverywhere(originLocation, departureDate, returnDate))
                .thenAccept(flightList -> {
                    flightData.clear();
                    flightData.addAll(flightList);
                });
    }
}
