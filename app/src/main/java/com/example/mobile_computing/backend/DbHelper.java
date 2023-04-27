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

public class DbHelper extends SQLiteOpenHelper {
// If you change the database schema, you must increment the database

    private static final String DATABASE_NAME = "favourites.db";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE favouritesTable ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "flightData TEXT,"
                + "hotelData TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement the code to upgrade the database here
    }
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

    public void removeFavorite(String flightData, String hotelData) {
        Log.i("removed from DB", flightData);
        Log.i("removed from DB", hotelData);
        SQLiteDatabase db = getWritableDatabase();

        String selection = "flightData = ? AND hotelData = ?";
        String[] selectionArgs = {flightData, hotelData};

        db.delete("favouritesTable", selection, selectionArgs);
        db.close();
    }
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

    public void clearFavorites() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM favouritesTable");

        db.close();
    }


}
