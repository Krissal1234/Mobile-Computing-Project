package com.example.mobile_computing.model;

import java.util.List;


public class FlightModel {
    //TODO Plan below:
    /*
    First user GET Search flights everywhere to get the available countries, should already ordered by price
    Then use GetFlightDetails with origin, dates destination from the country they picked

     */

    //Each return flight with no stops will have 2 legs. origin->destination, destination-> origin
    private String origin;
    private String destination;
    private String departureDate;
    private String arrivalDate;
    private String airline;
    private String price;
    private String bookingUrl;
    private String totalDuration;
    private List<LegsModel> legs;


    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getAirline() {
        return airline;
    }

    public String getPrice() {
        return price;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public List<LegsModel> getLegs() {
        return legs;
    }

    public FlightModel(String origin, String destination, String departureDate, String arrivalDate, String airline, String price, String bookingUrl, String totalDuration, List<LegsModel> legs) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airline = airline;
        this.price = price;
        this.bookingUrl = bookingUrl;
        this.totalDuration = totalDuration;
        this.legs = legs;
    }
}


