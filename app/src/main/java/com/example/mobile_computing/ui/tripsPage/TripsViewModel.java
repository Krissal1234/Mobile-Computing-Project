package com.example.mobile_computing.ui.tripsPage;



import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import services.FirebaseService;
/**
 The TripsViewModel class provides methods for asynchronously retrieving flight and hotel
 details for a specified trip.
 */
public class TripsViewModel {
    private FlightModel flightDetails;
    private ArrayList<HotelModel> hotels= new ArrayList<>();

    /**
     Asynchronously fetches flight details for the specified origin location, departure date, return date, and destination.
     @param originLocation The origin location of the flight.
     @param departureDate The departure date of the flight.
     @param returnDate The return date of the flight.
     @param destination The destination of the flight.
     @return A CompletableFuture object that will eventually hold the flight details.
     */
    public CompletableFuture<FlightModel> getFlightDetails(String originLocation, String departureDate, String  returnDate, String destination) {
        CompletableFuture<FlightModel> future = new CompletableFuture<>();
        fetchFlightDetailsAsync(originLocation, departureDate, returnDate,destination).thenRun(() -> future.complete(flightDetails));
        return future;
    }

    /**
     Retrieves flight details asynchronously from the services package.
     @param originLocation the origin location of the flight
     @param departureDate the departure date of the flight
     @param returnDate the return date of the flight
     @param destination the destination of the flight
     @return a CompletableFuture representing the flight details
     */
    private CompletableFuture<Void> fetchFlightDetailsAsync(String originLocation, String departureDate, String  returnDate, String destination) {
        FirebaseService service = new FirebaseService();
        return CompletableFuture.supplyAsync(() -> service.getTestFlights(originLocation, departureDate, returnDate, destination))
                .thenAccept(flight -> {
                    flightDetails = flight;
                });
    }

/** Retrieves a list of hotels from an api call in a firebase server source using an asynchronous process.
    @param location the location for the hotel search
    @param checkIn the check-in date for the hotel stay
    @param checkOut the check-out date for the hotel stay
    @return a CompletableFuture that resolves to an ArrayList of HotelModel objects
*/
    public CompletableFuture<ArrayList<HotelModel>> getHotels(String location, String checkIn, String  checkOut) {
        CompletableFuture<ArrayList<HotelModel>> future = new CompletableFuture<>();
        fetchHotelsAsync(location, checkIn, checkOut).thenRun(() -> future.complete(hotels));
        return future;
    }

/** Retrieves a list of hotels from an api call in a firebase server source using an asynchronous process.
 @param location the location to search for hotels in
 @param checkIn the date to check-in to the hotel
 @param checkOut the date to check-out of the hotel
 @return a CompletableFuture that completes when the hotel list has been fetched and stored in the class variable 'hotels'
 */
    private CompletableFuture<Void> fetchHotelsAsync(String location, String checkIn, String  checkOut) {
        FirebaseService service = new FirebaseService();
        return CompletableFuture.supplyAsync(() -> service.getTestHotels(location, checkIn, checkOut))
                .thenAccept(hotelList -> {
                    hotels.clear();
                    hotels.addAll(hotelList);
                });
    }
}
