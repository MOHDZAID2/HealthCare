package com.example.healthcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointement);

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFee);
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Cons. Fees: " + fees + "/-");

        // Initialize date and time pickers
        initDatePicker();
        initTimePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(v -> {
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");
            String date = dateButton.getText().toString();
            String time = timeButton.getText().toString();
            String appointmentDetails = title + " => " + fullname;

            if (db.checkAppointmentExists(username, appointmentDetails, address, contact, date, time) == 1) {
                Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_SHORT).show();
            } else {
                db.addOrder(username, appointmentDetails, address, contact, 0, date, time, Float.parseFloat(fees), "appointment");
                Toast.makeText(BookAppointmentActivity.this, "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, dayOfMonth) -> {
            month = month + 1;
            dateButton.setText(dayOfMonth + "/" + month + "/" + year);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Set the minimum date to tomorrow
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) ->
                timeButton.setText(String.format("%02d:%02d", hourOfDay, minute));

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(this, timeSetListener, hour, minute, true);
    }
}
