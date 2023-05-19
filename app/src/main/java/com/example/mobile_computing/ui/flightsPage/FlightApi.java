package com.example.mobile_computing.ui.flightsPage;

import com.example.mobile_computing.model.FlightModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Interface representing the Flight API.
 */
public interface FlightApi {
    /**
     * The base URL of the Flight API.
     * TODO: Replace "<host's IP here>" with the actual IP address of the host where the API is hosted.
     */
    String BASE_URL = "http://<host's IP here>:8080/";

    /**
     * Retrieves a list of flights from the API based on the specified parameters.
     *
     * @param origin         The origin of the flights.
     * @param departureDate  The departure date of the flights.
     * @param returnDate     The return date of the flights.
     * @return
     */
    @GET("flights")
    Call<List<FlightModel>> getFlights(@Query("origin") String origin, @Query("departureDate") String departureDate,@Query("returnDate") String returnDate);
}

