package com.example.mobile_computing.model;
/**

 Represents a trip model that contains the corresponding flight data and hotel data.
 */
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
