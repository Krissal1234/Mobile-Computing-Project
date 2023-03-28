package com.example.mobile_computing.model;

public class FlightDescriptionModel {
    private String country;
    private double price;
    private String imageUrl;

    public FlightDescriptionModel(String country, double price, String imageUrl) {
        this.country = country;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
