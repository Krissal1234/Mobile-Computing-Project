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


import com.example.mobile_computing.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import services.FirebaseService;

public class HomeFragment extends Fragment {

    private FirebaseService firebaseService;

    private FragmentHomeBinding binding;
    private EditText departureDate;
    private EditText returnDate;
    private boolean isDepartureDate = false;
    private Spinner countrySpinner;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //initiates the firebase service class.
        this.firebaseService = new FirebaseService();

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

    //Sets up Departure Date DatePicker.
    //Calender blocks out all days prior to current date (no past flights)
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

    //Sets up the return date DatePicker.
    //User must input departure date before return date
    //Calender blocks out all days prior to the departure date inputted.
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
                    minimumDate.set(Integer.parseInt(departureDateSplit[2]), Integer.parseInt(departureDateSplit[1]) - 1, Integer.parseInt(departureDateSplit[0]) + 1);
                    datePicker.getDatePicker().setMinDate(minimumDate.getTimeInMillis());
                    datePicker.show();
                } else {
                    Toast.makeText(getActivity(), "Please enter your departure date first.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
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
        //Sets up "Find my Trip" button.
        //Checks if all fields are filled in first.
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

                            //TODO Navigate to new ui where the api will be called onCreateView
                }
            }
            );

        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}