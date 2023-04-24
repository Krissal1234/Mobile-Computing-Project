package com.example.mobile_computing.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class HotelModel implements Parcelable {


    private String country;
    private String city;
    private String hotel_name;
    private int price_per_night;
    private String address;
    private List<AvailableDate> available_dates;

    public HotelModel(String country, String city, String hotel_name, int price_per_night, String address, List<AvailableDate> available_dates) {
        this.country = country;
        this.city = city;
        this.hotel_name = hotel_name;
        this.price_per_night = price_per_night;
        this.address = address;
        this.available_dates = available_dates;
    }

    protected HotelModel(Parcel in) {
        country = in.readString();
        city = in.readString();
        hotel_name = in.readString();
        price_per_night = in.readInt();
        address = in.readString();
    }

    public static final Creator<HotelModel> CREATOR = new Creator<HotelModel>() {
        @Override
        public HotelModel createFromParcel(Parcel in) {
            return new HotelModel(in);
        }

        @Override
        public HotelModel[] newArray(int size) {
            return new HotelModel[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getHotelName() {
        return hotel_name;
    }

    public String getPricePerNight() {
        return String.valueOf(price_per_night);
    }

    public String getAddress() {
        return address;
    }

    public List<AvailableDate> getAvailableDates() {
        return available_dates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(hotel_name);
        dest.writeInt(price_per_night);
        dest.writeString(address);
    }
}
