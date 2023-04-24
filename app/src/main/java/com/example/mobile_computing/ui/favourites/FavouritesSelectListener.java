package com.example.mobile_computing.ui.favourites;

import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

public interface FavouritesSelectListener {
    void onItemClicked(FlightModel model, HotelModel hotelModel);
}
