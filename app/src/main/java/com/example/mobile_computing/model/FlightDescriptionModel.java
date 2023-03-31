package com.example.mobile_computing.model;

public class FlightDescriptionModel {
    private String country;
    private String price;
    private String imageUrl;

    public String getCountry() {
        return country;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FlightDescriptionModel(String country, String price, String imageUrl) {
        this.country = country;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
