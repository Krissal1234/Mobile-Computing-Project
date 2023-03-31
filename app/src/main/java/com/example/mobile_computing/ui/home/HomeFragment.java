package com.example.mobile_computing.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobile_computing.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


import com.example.mobile_computing.databinding.FragmentHomeBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import services.FirebaseService;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private EditText departureDate;
    private EditText returnDate;
    private boolean isDepartureDate = false;
    private Spinner countrySpinner;
    private NavController navController;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*
       Initialisations of the EditText view in the inflated layout using their ID, and sets the input type to TYPE_NULL,
       which disables the keyboard from appearing when the user taps on the view.
         */
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        departureDate = view.findViewById(R.id.departure_date);
        departureDate.setInputType(InputType.TYPE_NULL);
        returnDate = view.findViewById(R.id.return_date);
        returnDate.setInputType(InputType.TYPE_NULL);
        countrySpinner = (Spinner) view.findViewById(R.id.origin_location);


        setUpDepartureDate();
        setUpReturnDate();
        setUpOriginLocation(view);
        setUpSearchButton(view);

        return view;
    }


    /**
     Sets up the departure date EditText view with a click listener to open a DatePickerDialog,
     which allows the user to select a date. The selected date is displayed in the EditText view.
     Also sets the minimum date selectable as today's date.
     */
    public void setUpDepartureDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        departureDate.setFocusable(false);
        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        departureDate.setText(date);
                        isDepartureDate = true;
                    }
                }, year, month, dayOfMonth);
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });
    }

    /**
     Sets up the return date picker dialog. The return date is selected by the user and displayed
     in the corresponding EditText view. The minimum date that can be selected is set to one day
     after the departure date that has been previously selected by the user. The return date is not
     editable by the user before the departure date has been set.
     before the
     */
    public void setUpReturnDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        returnDate.setFocusable(false);
        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar minimumDate = Calendar.getInstance();
                if (isDepartureDate) {
                    DatePickerDialog datePicker = new DatePickerDialog(
                            getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            month = month + 1;
                            String date = dayOfMonth + "/" + month + "/" + year;
                            returnDate.setText(date);
                        }
                    }, year, month, dayOfMonth);

                    // set minimum date for DatePicker
                    String[] departureDateSplit = departureDate.getText().toString().split("/");
                    minimumDate.set(Integer.parseInt(departureDateSplit[2]),
                    Integer.parseInt(departureDateSplit[1]) - 1,
                    Integer.parseInt(departureDateSplit[0]) + 1);
                    datePicker.getDatePicker().setMinDate(minimumDate.getTimeInMillis());
                    datePicker.show();
                } else {
                    Toast.makeText(getActivity(),
                    "Please enter your departure date first.",
                    Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     Sets up the origin location spinner with a list of countries obtained from the device's locales.
     The countries are sorted alphabetically and added to the spinner using an ArrayAdapter.
     @param view The View object representing the layout in which the spinner is located.
     */
        public void setUpOriginLocation(View view){
            List<String> countryList;
            countryList = new ArrayList<>();
            String[] locales = Locale.getISOCountries();

                for (String countryCode : locales) {
                    Locale obj = new Locale("", countryCode);
                    countryList.add(obj.getDisplayCountry());
                }

            Collections.sort(countryList, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, countryList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countrySpinner.setAdapter(adapter);

        }
    /**
     Sets up the search flights button and defines its onClickListener. When the button is clicked,
     it retrieves the data inputted
     If any of the fields are empty, it displays a message to the user prompting the user to fill
     in all fields.
     Otherwise, it formats the departure and return dates to work with the api.
     Finally, it navigates to the FlightsFragment and passes the data provided as arguments.
     @param view The view of the HomeFragment.
     */
        public void setUpSearchButton(View view){
            Button searchFlightsButton = view.findViewById(R.id.search_button);

            searchFlightsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String originLocation = countrySpinner.getSelectedItem().toString();
                    String departureDateString = departureDate.getText().toString();
                    String returnDateString = returnDate.getText().toString();

                    // Checks if all fields are filled in.
                    if (TextUtils.isEmpty(departureDateString) || TextUtils.isEmpty(returnDateString) || TextUtils.isEmpty(originLocation)) {
                        Toast.makeText(getActivity(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    departureDateString = formatDates(departureDateString);
                    returnDateString = formatDates(returnDateString);

                    Log.d("info", "From " +originLocation + " Departure: "+ departureDateString + " Return: "+ returnDateString);

                    navController = Navigation.findNavController(view);

                    // Create a new instance of the action
                    Bundle bundle = new Bundle();
                    bundle.putString("originLocation", originLocation);
                    bundle.putString("departureDate", departureDateString);
                    bundle.putString("return_date", returnDateString);
                    navController.navigate(R.id.nav_flights, bundle);
                }
            }
            );

        }
    /**
     This method converts the date string from the "dd/MM/yyyy" format to the "yyyy-MM-dd" format.
     In order to match the parameters required by the API.
     @param date the date string in "dd/MM/yyyy" format
     @return the formatted date string in "yyyy-MM-dd" format
     */
    private String formatDates(String date) {
       String newDate = " ";
        DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try{
            Date oldDate = inputFormat.parse(date);
            
            newDate = outputFormat.format(oldDate);

        }catch (Exception e){
            Log.d("error", e.toString());
        }
        return newDate;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}