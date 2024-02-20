package com.example.healthcare;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookAppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointement);

        // Get date strings from your intent or wherever you are getting them
        String fromDateStr = getIntent().getStringExtra("fromDate");
        String toDateStr = getIntent().getStringExtra("toDate");

        if (fromDateStr != null && toDateStr != null) {
            // Format the date strings correctly
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

            try {
                Date fromDate = inputFormat.parse(fromDateStr);
                Date toDate = inputFormat.parse(toDateStr);

                // Format the dates to the desired format for comparison
                fromDateStr = outputFormat.format(fromDate);
                toDateStr = outputFormat.format(toDate);

                // Check if the date range is valid
                if (isDateRangeValid(fromDateStr, toDateStr)) {
                    // Your logic if the date range is valid
                    Toast.makeText(this, "Date range is valid", Toast.LENGTH_SHORT).show();
                } else {
                    // Your logic if the date range is not valid
                    Toast.makeText(this, "Invalid date range", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            // Handle the case where fromDateStr or toDateStr is null
            Toast.makeText(this, "Invalid date strings", Toast.LENGTH_SHORT).show();
        }
    }

    // Your existing isDateRangeValid method remains the same
    private boolean isDateRangeValid(String fromDateStr, String toDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        try {
            Date fromDate = dateFormat.parse(fromDateStr);
            Date toDate = dateFormat.parse(toDateStr);

            // Check if fromDate is before toDate
            return fromDate.before(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception
            return false;
        }
    }
}
