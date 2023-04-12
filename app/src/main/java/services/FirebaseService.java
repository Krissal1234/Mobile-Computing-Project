package services;

import android.util.Log;

import com.example.mobile_computing.model.FlightDescriptionModel;
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.example.mobile_computing.model.LegsModel;
//import com.google.firebase.functions.FirebaseFunctions;
//import com.google.firebase.functions.HttpsCallableResult;

import com.google.gson.Gson;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FirebaseService {
//    private FirebaseFunctions func;

//    public FirebaseService() {
//        func = FirebaseFunctions.getInstance();
//    }

//    public Task<HttpsCallableResult> getFlights() {
//       return func.getHttpsCallable("helloWorld").call();
//
//    }
//    public List<TripsModel> getCheapestTrips(){
//        List<FlightModel> flights = getFlights();
//
//
//    }


    public FlightModel getTestCheapestFlight(String originLocation, String departureDate, String returnDate, String destination){
        String arrivalDate = departureDate;
        String airline = originLocation +"Airlines";
        String price = "750.00";
        String bookingUrl = "https://www.example.com/bookings/12345";
        String totalDuration = "7h 30m";

        List<LegsModel> legs = new ArrayList<>();

        LegsModel leg1 = new LegsModel(originLocation, destination, "2023-05-01T10:00:00", "2023-05-01T17:30:00", "7h 30m", "BA1234");
        LegsModel leg2 = new LegsModel(destination, originLocation, "2023-05-10T15:00:00", returnDate +"T19:00:00", "4h 00m", "BA5678");

        legs.add(leg1);
        legs.add(leg2);

        FlightModel flight = new FlightModel(originLocation, destination, departureDate, arrivalDate, airline, price, bookingUrl, totalDuration, legs);

        return  flight;
    }


    public List<FlightDescriptionModel> getTestFlightsEverywhere(String originLocation, String departureDate, String returnDate){
        List<FlightDescriptionModel> cheapestFlights = new ArrayList<>();
        cheapestFlights.add(new FlightDescriptionModel("France", "12", "https://content.skyscnr.com/c88da2b091534f7baf8536b3959ce83a/GettyImages-495057957.jpg?crop=400px:400px&quality=75"));
        cheapestFlights.add(new FlightDescriptionModel("Italy", "14", "https://content.skyscnr.com/f348d79cfdf70286dc759d24618a23c3/GettyImages-182281845.jpg?crop=400px:400px&quality=75"));
        cheapestFlights.add(new FlightDescriptionModel("Spain", "15", "https://content.skyscnr.com/e0241c97c2b33b71e9c278bc89bb17ed/GettyImages-178640523.jpg?crop=400px:400px&quality=75"));


            return cheapestFlights;


    }
//    public List<FlightDescriptionModel> getTestFlightsEverywhere(String originLocation, String departureDate, String returnDate) {
//        List<FlightDescriptionModel> cheapestFlights = new ArrayList<>();
//        cheapestFlights.add(new FlightDescriptionModel("France", "12", "https://content.skyscnr.com/c88da2b091534f7baf8536b3959ce83a/GettyImages-495057957.jpg?crop=400px:400px&quality=75"));
//        cheapestFlights.add(new FlightDescriptionModel("Italy", "14", "https://content.skyscnr.com/f348d79cfdf70286dc759d24618a23c3/GettyImages-182281845.jpg?crop=400px:400px&quality=75"));
//        cheapestFlights.add(new FlightDescriptionModel("Spain", "15", "https://content.skyscnr.com/e0241c97c2b33b71e9c278bc89bb17ed/GettyImages-178640523.jpg?crop=400px:400px&quality=75"));
//        cheapestFlights.add(new FlightDescriptionModel("Japan", "17", "https://example.com/japan.jpg"));
//
//        List<JsonObject> jsonData = new ArrayList<>();
//        Gson gson = new Gson();
//
//        for (int i = 0; i < cheapestFlights.size(); i++) {
//            FlightDescriptionModel flight = cheapestFlights.get(i);
//            JsonObject jsonFlight = new JsonObject();
//            jsonFlight.addProperty("CountryName", flight.getCountry());
//            jsonFlight.addProperty("Price", flight.getPrice());
//            jsonFlight.addProperty("ImageUrl", flight.getImageUrl());
//            jsonData.add(jsonFlight);
//        }
//
//        Log.d("json:",jsonData.toString());
//        return cheapestFlights;
//    }
    public List<HotelModel> getTestHotels(String location, String checkIn, String checkOut ){
        List<HotelModel> hotels = null;
        String price = "175.50";
        String entityId = "123456";
        String rating = "4.5";
        String longitudeLoc = "2.3522";
        String latitudeLoc = "48.8566";
        String hotelName = "Hotel Eiffel";

        List<String> images = Arrays.asList("https://example.com/image1.jpg", "https://example.com/image2.jpg");

        hotels.add(new HotelModel("Paris, France", "price", entityId, rating, longitudeLoc, latitudeLoc, hotelName, "2023-04-01T15:00:00", "2023-04-05T11:00:00", images));
        hotels.add(new HotelModel("Paris, France", "135.50", "12345", "4.3", "2.3522", "48.8566", "Hotel de Paris", "2023-04-10T14:00:00", "2023-04-15T12:00:00", List.of("https://www.example.com/hotel_image1.jpg", "https://www.example.com/hotel_image2.jpg")));
        hotels.add(new HotelModel("Nice, France", "95.00", "67890", "3.9", "7.2674", "43.7102", "Hotel du Soleil", "2023-04-01T12:00:00", "2023-05-05T10:00:00", List.of("https://www.example.com/hotel_image1.jpg", "https://www.example.com/hotel_image2.jpg")));
        hotels.add(new HotelModel("Lyon, France", "125.00", "23456", "4.1", "4.8357", "45.7640", "Hotel du Parc", "2023-04-20T15:00:00", "2023-07-23T11:00:00", List.of("https://www.example.com/hotel_image1.jpg", "https://www.example.com/hotel_image2.jpg")));

        return hotels;
    }
}
