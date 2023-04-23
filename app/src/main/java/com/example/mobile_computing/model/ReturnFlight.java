package com.example.mobile_computing.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ReturnFlight implements Parcelable  {
    private String origin;
    private String destination;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private String flightDuration;
    private String airline;
    private String flightNumber;
    private int price;
    private String imageUrl;

    public ReturnFlight(String origin, String destination, String departureDate, String departureTime, String arrivalDate, String arrivalTime, String flightDuration, String airline, String flightNumber, int price, String imageUrl) {
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
    }
    public static final Parcelable.Creator<ReturnFlight> CREATOR = new Parcelable.Creator<ReturnFlight>() {
        @Override
        public ReturnFlight createFromParcel(Parcel in) {
            return new ReturnFlight(in);
        }

        @Override
        public ReturnFlight[] newArray(int size) {
            return new ReturnFlight[size];
        }
    };

    protected ReturnFlight(Parcel in) {
        origin = in.readString();
        destination = in.readString();
        departureDate = in.readString();
        departureTime = in.readString();
        arrivalDate = in.readString();
        arrivalTime = in.readString();
        flightDuration = in.readString();
        airline = in.readString();
        flightNumber = in.readString();
        price = in.readInt();
        imageUrl = in.readString();
    }


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

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
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
        dest.writeInt(price);
        dest.writeString(imageUrl);
    }

}