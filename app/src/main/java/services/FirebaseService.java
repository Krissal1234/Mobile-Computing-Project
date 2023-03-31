package services;

import com.example.mobile_computing.model.FlightDescriptionModel;
import com.example.mobile_computing.model.FlightModel;
//import com.google.firebase.functions.FirebaseFunctions;
//import com.google.firebase.functions.HttpsCallableResult;

import java.util.ArrayList;
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


    public List<FlightModel> getFlights(){
    List<FlightModel> flights = new ArrayList<>();

//    flights.add(new FlightModel("England", "France", "2023-04-10", "2023-04-15", "Air France", "AF123", 250.00, "https://example.com/book-flight",
//            Arrays.asList(new SegmentModel("London", "Paris", "2023-04-10 10:00", "2023-04-10 12:00", "None", ""), new SegmentModel("Paris", "London", "2023-04-15 14:00", "2023-04-15 16:00", "None", ""))));
//
//    flights.add(new FlightModel("England", "Italy", "2023-04-20", "2023-04-26", "Alitalia", "AZ456", 280.50, "https://example.com/book-flight",
//            Arrays.asList(new SegmentModel("London", "Rome", "2023-04-20 08:00", "2023-04-20 11:30", "Milan", "02:00"), new SegmentModel("Rome", "London", "2023-04-26 17:00", "2023-04-26 20:30", "None", ""))));
//    flights.add(new FlightModel("England", "Spain", "2023-05-05", "2023-05-09", "Iberia", "IB789", 180.00, "https://example.com/book-flight",
//                Arrays.asList(new SegmentModel("London", "Barcelona", "2023-05-05 11:00", "2023-05-05 13:30", "Madrid", "01:30"), new SegmentModel("Barcelona", "London", "2023-05-09 16:00", "2023-05-09 18:30", "None", ""))));
//
//    flights.add(new FlightModel("England", "Japan", "2023-06-15", "2023-06-25", "Japan Airlines", "JL567", 1200.00, "https://example.com/book-flight",
//            Arrays.asList(new SegmentModel("London", "Tokyo", "2023-06-15 09:00", "2023-06-16 15:00", "Seoul", "05:00"), new SegmentModel("Tokyo", "London", "2023-06-25 12:00", "2023-06-25 20:00", "None", ""))));
        return  flights;
    }

    public List<FlightDescriptionModel> getFlightsEverywhere(String originLocation, String departureDate, String returnDate){
        List<FlightDescriptionModel> cheapestFlights = new ArrayList<>();
        cheapestFlights.add(new FlightDescriptionModel("France", "12", "https://content.skyscnr.com/c88da2b091534f7baf8536b3959ce83a/GettyImages-495057957.jpg?crop=400px:400px&quality=75"));
        cheapestFlights.add(new FlightDescriptionModel("Italy", "14", "https://content.skyscnr.com/f348d79cfdf70286dc759d24618a23c3/GettyImages-182281845.jpg?crop=400px:400px&quality=75"));
        cheapestFlights.add(new FlightDescriptionModel("Spain", "15", "https://content.skyscnr.com/e0241c97c2b33b71e9c278bc89bb17ed/GettyImages-178640523.jpg?crop=400px:400px&quality=75"));
        cheapestFlights.add(new FlightDescriptionModel("Japan", "17", "https://example.com/japan.jpg"));

            return cheapestFlights;


    }

}
