package com.example.mobile_computing.ui.flightsPage;

import com.example.mobile_computing.model.FlightModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "http://192.168.1.138:3000/";
    @GET("flights")
    Call<List<FlightModel>> getFlights(@Query("origin") String origin, @Query("departureDate") String departureDate);
}

