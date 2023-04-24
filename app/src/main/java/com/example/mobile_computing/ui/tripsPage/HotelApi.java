package com.example.mobile_computing.ui.tripsPage;

import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HotelApi {
    String BASE_URL = "http://192.168.1.138:3000/";
    @GET("hotels")
    Call<List<HotelModel>> getHotels(@Query("arrivalDate") String checkIn, @Query("departureDate") String checkOut, @Query("country") String destination);
}
