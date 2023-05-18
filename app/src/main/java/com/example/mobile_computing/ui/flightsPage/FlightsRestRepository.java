package com.example.mobile_computing.ui.flightsPage;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobile_computing.model.FlightModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * The repository class responsible for fetching flights data from the API.
 */
public class FlightsRestRepository {
    private static FlightsRestRepository instance = null;
    private FlightApi api;

    private FlightsRestRepository() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FlightApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(FlightApi.class);
    }

    public static synchronized FlightsRestRepository getInstance() {
        if (instance == null) {
            instance = new com.example.mobile_computing.ui.flightsPage.FlightsRestRepository();
        }
        return instance;
    }
    /**
     * Fetches flights data from the API based on the specified origin, departure date, and return date.
     * Returns a LiveData object containing the list of FlightModel objects.
     *
     * @param origin         The origin location for the flights.
     * @param departureDate  The departure date for the flights.
     * @param returnDate     The return date for the flights.
     * @return A LiveData object containing the list of FlightModel objects.
     */
    public LiveData<List<FlightModel>> fetchFlights(String origin, String departureDate, String returnDate) {
        final MutableLiveData<List<FlightModel>> flights = new MutableLiveData<>();
        api.getFlights(origin,departureDate,returnDate).enqueue(new Callback<List<FlightModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<FlightModel>> call, @NonNull
                    Response<List<FlightModel>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<FlightModel> flightList = flights.getValue();
                flights.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<List<FlightModel>> call, @NonNull
                    Throwable t) {
                Log.i("fetchFlights", call.request().toString());
                Log.e("fetchFlights", t.getMessage());
                flights.setValue(null);
            }
        });
        return flights;
    }
}
