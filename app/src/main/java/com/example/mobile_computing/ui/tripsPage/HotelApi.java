package com.example.mobile_computing.ui.tripsPage;

import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Interface representing the Flight API.
 */
public interface HotelApi {
    /**
     * The base URL of the Hotel API.
     * TODO: Replace "<host's IP here>" with the actual IP address of the host where the API is hosted.
     */
    String BASE_URL = "http://<host's IP here>:8080/";
    @GET("hotels")
    Call<List<HotelModel>> getHotels(@Query("arrivalDate") String checkIn, @Query("departureDate") String checkOut, @Query("country") String destination);
}
