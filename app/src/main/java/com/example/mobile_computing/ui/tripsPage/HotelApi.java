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
     * TODO: Replace "<your IP address here>" with the actual IP address of the device running the application.
     */
    String BASE_URL = "http://<your IP address here>:8080/";
    @GET("hotels")
    Call<List<HotelModel>> getHotels(@Query("arrivalDate") String checkIn, @Query("departureDate") String checkOut, @Query("country") String destination);
}
