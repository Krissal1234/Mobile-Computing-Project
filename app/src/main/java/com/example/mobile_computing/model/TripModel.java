package com.example.mobile_computing.model;

import java.util.Date;
import java.util.List;


public class TripModel {
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


}
