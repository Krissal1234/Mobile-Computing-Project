package com.example.mobile_computing.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class FlightModel implements Parcelable {

    private String origin;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String flightDuration;
    private String airline;
    private String flightNumber;
    private String price;
    private String imageUrl;
    private ReturnFlight returnFlight;


    public FlightModel(String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightDuration, String airline, String flightNumber, String price, String imageUrl, ReturnFlight returnFlight) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.flightDuration = flightDuration;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.price = price;
        this.imageUrl = imageUrl;
        this.returnFlight = returnFlight;
    }

    protected FlightModel(Parcel in) {
        origin = in.readString();
        destination = in.readString();
        departureDate = in.readString();
        departureTime = in.readString();
        arrivalDate = in.readString();
        arrivalTime = in.readString();
        flightDuration = in.readString();
        airline = in.readString();
        flightNumber = in.readString();
        price = in.readString();
        imageUrl = in.readString();
        returnFlight = in.readParcelable(ReturnFlight.class.getClassLoader());
    }

    public static final Creator<FlightModel> CREATOR = new Creator<FlightModel>() {
        @Override
        public FlightModel createFromParcel(Parcel in) {
            return new FlightModel(in);
        }

        @Override
        public FlightModel[] newArray(int size) {
            return new FlightModel[size];
        }
    };

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightDuration() {
        return flightDuration;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ReturnFlight getReturnFlight() {
        return returnFlight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(origin);
        dest.writeString(destination);
        dest.writeString(departureDate);
        dest.writeString(departureTime);
        dest.writeString(arrivalDate);
        dest.writeString(arrivalTime);
        dest.writeString(flightDuration);
        dest.writeString(airline);
        dest.writeString(flightNumber);
        dest.writeString(price);
        dest.writeString(imageUrl);
        dest.writeParcelable(returnFlight, flags);
    }
}



