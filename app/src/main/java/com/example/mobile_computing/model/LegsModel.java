package com.example.mobile_computing.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTime() {
        return getTime(departureTime);
    }

    public String getArrivalTime() {
        return getTime(arrivalTime);
    }

    public String getDuration() {
        return duration;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    private String getTime(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        String timeString = dateTime.toLocalTime().toString();
        return timeString;
    }
}