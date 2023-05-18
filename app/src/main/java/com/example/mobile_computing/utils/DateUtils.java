package com.example.mobile_computing.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * Formats a date string from the format "yyyy-MM-dd" to "dd/MM/yyyy".
     *Used when returning dates from objects,since the api returns this format.
     * @param dateString The date string to format.
     * @return The formatted date string in the format "dd/MM/yyyy",
     * or an empty string if the input is invalid.
     */
    public static String formatDate(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = inputFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            return outputFormat.format(date);
        } else {
            return "";
        }
    }
}
