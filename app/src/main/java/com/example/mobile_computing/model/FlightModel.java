package com.example.mobile_computing.model;

import java.util.Date;
import java.util.List;


public class FlightModel {
    //TODO Plan below:
    /*
    First user GET Search flights everywhere to get the available countries, should already ordered by price
    Then use GetFlightDetails with origin, dates destination from the country they picked

     */
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private String airline;
    private String flightNumber;
    private double price;
    private String bookingUrl;
    private List<SegmentModel> segments;

    public FlightModel(String origin, String destination, Date departureDate, Date arrivalDate, String airline, String flightNumber, double price, String bookingUrl, List<SegmentModel> segments) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.price = price;
        this.bookingUrl = bookingUrl;
        this.segments = segments;
    }
}
