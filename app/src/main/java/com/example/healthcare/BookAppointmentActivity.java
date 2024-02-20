package com.example.healthcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button datebutton,timeButton,btnBook,btnBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointement);
    tv  = findViewById(R.id.textViewAppTitle);
    ed1 = findViewById(R.id.editTextAppFullName);
    ed2 = findViewById(R.id.editTextAppAddress);
    ed3 = findViewById(R.id.editTextAppContactNumber);
    ed4 = findViewById(R.id.editTextAppFee);
    datebutton = findViewById(R.id.buttonAppDate);
    timeButton = findViewById(R.id.buttonAppTime);
    btnBook = findViewById(R.id.buttonBookAppointment);
    btnBack = findViewById(R.id.buttonAppBack);
    ed1.setKeyListener(null);
    ed2.setKeyListener(null);
    ed3.setKeyListener(null);
    ed4.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("coin Fees:"+fees+"/-");
        // Inside your activity or fragment

            // Initialize DatePickerDialog
            datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        // Handle date selection
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );

            // Initialize TimePickerDialog
            timePickerDialog = new TimePickerDialog(
                    this,
                    (view, hourOfDay, minute) -> {
                        // Handle time selection
                    },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    false
            );

            // Set onClickListener for date and time pickers
            findViewById(R.id.buttonAppDate).setOnClickListener(v -> datePickerDialog.show());
            findViewById(R.id.buttonAppTime).setOnClickListener(v -> timePickerDialog.show());

      btnBack.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));

          }
      });


      btnBook.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

          }
      });

        }


    }



 