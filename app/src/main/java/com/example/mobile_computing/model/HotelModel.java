package com.example.mobile_computing.model;

import java.util.List;

public class HotelModel {

    private String location;
    private String price;
    private String entityId;
    private String rating;
    private String longitudeLoc;
    private String latitudeLoc;
    private String hotelName;
    private String checkIn; // including times
    private String checkOut;
    private List<String> images;

    public String getLocation() {
        return location;
    }

    public String getPrice() {
        return price;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getRating() {
        return rating;
    }

    public String getLongitudeLoc() {
        return longitudeLoc;
    }

    public String getLatitudeLoc() {
        return latitudeLoc;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public List<String> getImages() {
        return images;
    }

    public HotelModel(String location, String price, String entityId, String rating, String longitudeLoc, String latitudeLoc, String hotelName, String checkIn, String checkOut, List<String> images) {
        this.location = location;
        this.price = price;
        this.entityId = entityId;
        this.rating = rating;
        this.longitudeLoc = longitudeLoc;
        this.latitudeLoc = latitudeLoc;
        this.hotelName = hotelName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.images = images;
    }
}
