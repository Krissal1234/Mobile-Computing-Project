package com.example.mobile_computing.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mobile_computing.utils.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * The FlightModel class represents a flight with various attributes.
 * It implements the Parcelable interface to enable passing instances between components.
 */
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

    /**
     * Constructs a FlightModel object with the specified attributes.
     *
     * @param origin         the origin of the flight
     * @param destination    the destination of the flight
     * @param departureDate  the departure date of the flight
     * @param departureTime  the departure time of the flight
     * @param arrivalDate    the arrival date of the flight
     * @param arrivalTime    the arrival time of the flight
     * @param flightDuration the duration of the departure flight
     * @param airline        the airline of the flight
     * @param flightNumber   the flight number
     * @param price          the price of the flight
     * @param imageUrl       the URL of the flight's image
     * @param returnFlight   the return flight (optional)
     */
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
    /**
     * Constructs a FlightModel object from a Parcel object (used for Parcelable implementation).
     *
     * @param in the Parcel object containing the FlightModel data
     */
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
    /**
     * Creator constant for Parcelable implementation.
     */
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
        return DateUtils.formatDate(departureDate);
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
    /**

     Writes the object's data to a Parcel.
     @param dest The Parcel where the object's data should be written to.
     @param flags Additional flags about how the object should be written. May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
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



