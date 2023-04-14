package com.example.mobile_computing.ui.flightsPage;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.mobile_computing.model.FlightDescriptionModel;
import com.google.firebase.functions.FirebaseFunctions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import services.FirebaseService;

public class FlightsViewModel extends ViewModel {
    private ArrayList<FlightDescriptionModel> flightData = new ArrayList<>();


    public CompletableFuture<ArrayList<FlightDescriptionModel>> getFlightData(String originLocation, String departureDate, String  returnDate) {
        CompletableFuture<ArrayList<FlightDescriptionModel>> future = new CompletableFuture<>();
        fetchFlightsAsync(originLocation, departureDate, returnDate).thenRun(() -> future.complete(flightData));
        return future;
    }

//    private CompletableFuture<Void> fetchFlightsAsync(String originLocation, String departureDate, String  returnDate) {
//        FirebaseService service = new FirebaseService();
//        return CompletableFuture.supplyAsync(() -> service.getTestFlightsEverywhere(originLocation, departureDate, returnDate))
//                .thenAccept(flightList -> {
//                    flightData.clear();
//
//
//                    flightData.addAll(flightList);
//                });
//    }

    private CompletableFuture<Void> fetchFlightsAsync(String originLocation, String departureDate, String  returnDate) {
        // Get a reference to the Firebase Cloud Function
        FirebaseService service = new FirebaseService();
        return CompletableFuture.supplyAsync(() -> getFlightsFirebaseCall())
                .thenAccept(flightList -> {
                    flightData.clear();
                    flightData.addAll(flightList);
                });
    }

    private List<FlightDescriptionModel> getFlightsFirebaseCall() {
        FirebaseFunctions functions = FirebaseFunctions.getInstance();

        // Call the Firebase Cloud Function with the provided parameters
        try {
            functions.getHttpsCallable("getFlights")
                    .call()
                    .continueWith(task -> {
                        // Handle the result of the Firebase Cloud Function call
                        List<FlightDescriptionModel> flightList;
                        if (!task.isSuccessful()) {
                            Log.e("Error", task.getException().toString());
                            throw new Exception(task.getException());
                        } else {
                            Log.i("function", task.getResult().getData().toString());
                            JSONArray jsonArray = new JSONArray(task.getResult().toString());

                            flightList = new ArrayList<>();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String country = jsonObject.getString("CountryName");
                                String price = jsonObject.getString("Price");
                                String imageUrl = jsonObject.getString("ImageUrl");

                                FlightDescriptionModel flight = new FlightDescriptionModel(country, price, imageUrl);
                                flightList.add(flight);
                            }
                        }

                        return flightList;
                    });
        }catch (Exception e) {
            Log.e("Error", e.toString());
            return new ArrayList<>();
        }

        return null;

    }


}
