package com.example.mobile_computing.backend;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mobile_computing.model.TripModel;

import java.util.ArrayList;
import java.util.List;
/**
 * A helper class for managing the database used for storing favorite trips.
 */
public class DbHelper extends SQLiteOpenHelper {
// If you change the database schema, you must increment the database

    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * Constructs a new DbHelper object.
     *
     * @param context The context.
     */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * Called when the database is created.
     *
     * @param db The SQLiteDatabase object.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favouritesTable ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "flightData TEXT,"
                + "hotelData TEXT)");
    }
/**
 * Called when the database needs to be upgraded.
 */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement the code to upgrade the database here
    }
    /**
     * Checks if a trip is a favorite.
     *
     * @param type The flight data of the trip.
     * @param data The hotel data of the trip.
     * @return true if the trip is a favorite, false otherwise.
     */
    public boolean isFavorite(String type, String data) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {"id"};
        String selection = "flightData = ? AND hotelData = ?";
        String[] selectionArgs = {type, data};

        Cursor cursor = db.query("favouritesTable", projection, selection, selectionArgs,
                null, null, null);

        boolean isFavorite = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isFavorite;
    }
    /**
     * Adds a trip to the favorites.
     *
     * @param flightData The flight data of the trip.
     * @param hotelData  The hotel data of the trip.
     */
    public void addFavorite(String flightData, String hotelData) {
        Log.i("added to DB", flightData);
        Log.i("added to DB", hotelData);
        if (!isFavorite(flightData, hotelData)) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("flightData", flightData);
            values.put("hotelData", hotelData);

            db.insert("favouritesTable", null, values);
            db.close();
        }
    }
    /**
     * Removes a trip from the favorites.
     *
     * @param flightData The flight data of the trip.
     * @param hotelData  The hotel data of the trip.
     */
    public void removeFavorite(String flightData, String hotelData) {
        Log.i("removed from DB", flightData);
        Log.i("removed from DB", hotelData);
        SQLiteDatabase db = getWritableDatabase();

        String selection = "flightData = ? AND hotelData = ?";
        String[] selectionArgs = {flightData, hotelData};

        db.delete("favouritesTable", selection, selectionArgs);
        db.close();
    }
    /**
     * Retrieves all favourite trips.
     *
     * @return A list of TripModel objects representing the favourite trips.
     */
    public List<TripModel> getAllData() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {"flightData", "hotelData"};

        Cursor cursor = db.query("favouritesTable", projection, null, null,
                null, null, null);

        List<TripModel> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String flightData = cursor.getString(cursor.getColumnIndex("flightData"));
            @SuppressLint("Range") String hotelData = cursor.getString(cursor.getColumnIndex("hotelData"));
            TripModel item = new TripModel(flightData, hotelData);
            data.add(item);
        }

        cursor.close();
        db.close();

        return data;
    }
    /**
     * Clears the favourites table.
     */
    public void clearFavorites() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM favouritesTable");

        db.close();
    }


}
