package com.example.mobile_computing.model;

import java.util.Date;
//Segments are the layovers in a flight
public class SegmentModel {

        private String departureCity;
        private String arrivalCity;
        private Date departureTime;
        private Date arrivalTime;
        private String layoverCity;
        private String layoverDuration;


    public SegmentModel(String departureCity, String arrivalCity, Date departureTime, Date arrivalTime, String layoverCity, String layoverDuration) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.layoverCity = layoverCity;
        this.layoverDuration = layoverDuration;
    }
}
