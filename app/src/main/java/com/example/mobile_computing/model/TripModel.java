package com.example.mobile_computing.model;

public class TripModel {

    private String flightData;
    private String hotelData;

    public TripModel(String flightData, String hotelData) {
        this.flightData = flightData;
        this.hotelData = hotelData;
    }

    public String getFlightData() {
        return flightData;
    }

    public String getHotelData() {
        return hotelData;
    }
}
