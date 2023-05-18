package com.example.mobile_computing.ui.tripsPage;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.mobile_computing.model.HotelModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class HotelsRestRepository {
    private static HotelsRestRepository instance = null;
    private HotelApi api;

    private HotelsRestRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HotelApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(HotelApi.class);
    }

    public static synchronized HotelsRestRepository getInstance() {
        if (instance == null) {
            instance = new HotelsRestRepository();
        }
        return instance;
    }
    /**
     * Fetches hotels data from the API based on the specified check-in date, check-out date
     * and destination.
     * Returns a LiveData object containing the list of HotelModel objects.
     *
     * @param checkIn      The check-in date for the hotels.
     * @param checkOut     The check-out date for the hotels.
     * @param destination  The location of the hotels.
     * @return A LiveData object containing the list of HotelModel objects.
     */
    public LiveData<List<HotelModel>> fetchHotels(String checkIn, String checkOut, String destination) {
        final MutableLiveData<List<HotelModel>> hotels = new MutableLiveData<>();
        api.getHotels(checkIn,checkOut,destination).enqueue(new Callback<List<HotelModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<HotelModel>> call, @NonNull
                    Response<List<HotelModel>> response) {
                if (!response.isSuccessful() ) {
                    return;
                }
                hotels.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<HotelModel>> call, @NonNull
                    Throwable t) {
                Log.i("fetchHotels", call.request().toString());
                Log.e("fetchHotels", t.getMessage());
                hotels.setValue(null);
            }
        });
        return hotels;
    }
}
