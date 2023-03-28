package services;

import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;
import com.example.mobile_computing.model.SegmentModel;
import com.example.mobile_computing.model.TripsModel;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.functions.FirebaseFunctions;
//import com.google.firebase.functions.HttpsCallableResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // Create a list of segments
        List<SegmentModel> segments = new ArrayList<>();
        segments.add(new SegmentModel("ABC", "DEF", new Date(1655347200000L), new Date(1655350800000L), "Airline1", "1234"));
        segments.add(new SegmentModel("DEF", "GHI", new Date(1655400000000L), new Date(1655403600000L), "Airline2", "5678"));


        List<SegmentModel> segments1 = new ArrayList<>();
        segments1.add(new SegmentModel("JFK", "LHR", new Date(1655347200000L), new Date(1655350800000L), "Layover City 1", "120"));
        segments1.add(new SegmentModel("LHR", "CDG", new Date(1655400000000L), new Date(1655403600000L), "Layover City 2", "90"));


        List<SegmentModel> segments2 = new ArrayList<>();
        segments2.add(new SegmentModel("LAX", "HKG", new Date(1655347200000L), new Date(1655350800000L), "Layover City 1", "240"));
        segments2.add(new SegmentModel("HKG", "BKK", new Date(1655400000000L), new Date(1655403600000L), "Layover City 2", "180"));


        List<FlightModel> flights = new ArrayList<>();

        flights.add(new FlightModel("XYZ", "GHI", new Date(1655270400000L), new Date(1655443200000L), "Airline3", "56789", 500.00, "https://bookingurl.com/flight", segments));
        flights.add(new FlightModel("JFK", "CDG", new Date(1655270400000L), new Date(1655443200000L), "Airline1", "12345", 800.00, "https://bookingurl1.com/flight", segments1));
        flights.add(new FlightModel("LAX", "BKK", new Date(1655270400000L), new Date(1655443200000L), "Airline2", "67890", 1000.00, "https://bookingurl2.com/flight", segments2));


        return  flights;
    }


}
