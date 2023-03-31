package com.example.mobile_computing.model;

public class LegsModel {

    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String duration;
    private String flightNumber;

    public LegsModel(String departureAirport, String arrivalAirport, String departureTime, String arrivalTime, String duration, String flightNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.flightNumber = flightNumber;
    }
}