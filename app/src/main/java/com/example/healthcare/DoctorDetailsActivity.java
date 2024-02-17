package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"DoctorName : Ajit Saste", "Hospital Address : Delhi", "Exp : 5yrs", "Mobile No : 9898989898","600"},
                    {"DoctorName : Prasad Pawar", "Hospital Address : Meerut", "Exp : 8yrs", "Mobile No : 9989899999","700"},
                    {"DoctorName : Zeeshan Khan", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No : 6395000011","900"},
                    {"DoctorName : Deepak Deshmukh", "Hospital Address : Gorakhpur", "Exp : 10yrs", "Mobile No : 9720200001","800"},
                    {"DoctorName : Ashok Kumar", "Hospital Address : Chennai", "Exp : 12yrs", "Mobile No : 8912111132","900"},

            };

    private String[][] doctor_details2 =
            {
                    {"DoctorName : Ajit Saste", "Hospital Address : Delhi", "Exp : 5yrs", "Mobile No : 9898989898","600"},
                    {"DoctorName : Prasad Pawar", "Hospital Address : Meerut", "Exp : 8yrs", "Mobile No : 9989899999","700"},
                    {"DoctorName : Zeeshan Khan", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No : 6395000011","900"},
                    {"DoctorName : Deepak Deshmukh", "Hospital Address : Gorakhpur", "Exp : 10yrs", "Mobile No : 9720200001","800"},
                    {"DoctorName : Ashok Kumar", "Hospital Address : Chennai", "Exp : 12yrs", "Mobile No : 8912111132","900"},

            };

    private String[][] Doctor_details3 =
            {
                    {"DoctorName : Ajit Saste", "Hospital Address : Delhi", "Exp : 5yrs", "Mobile No : 9898989898","600"},
                    {"DoctorName : Prasad Pawar", "Hospital Address : Meerut", "Exp : 8yrs", "Mobile No : 9989899999","700"},
                    {"DoctorName : Zeeshan Khan", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No : 6395000011","900"},
                    {"DoctorName : Deepak Deshmukh", "Hospital Address : Gorakhpur", "Exp : 10yrs", "Mobile No : 9720200001","800"},
                    {"DoctorName : Ashok Kumar", "Hospital Address : Chennai", "Exp : 12yrs", "Mobile No : 8912111132","900"},

            };

    private String[][] doctor_details4 =
            {
                    {"DoctorName : Ajit Saste", "Hospital Address : Delhi", "Exp : 5yrs", "Mobile No : 9898989898","600"},
                    {"DoctorName : Prasad Pawar", "Hospital Address : Meerut", "Exp : 8yrs", "Mobile No : 9989899999","700"},
                    {"DoctorName : Zeeshan Khan", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No : 6395000011","900"},
                    {"DoctorName : Deepak Deshmukh", "Hospital Address : Gorakhpur", "Exp : 10yrs", "Mobile No : 9720200001","800"},
                    {"DoctorName : Ashok Kumar", "Hospital Address : Chennai", "Exp : 12yrs", "Mobile No : 8912111132","900"},

            };

    private String[][] doctor_details5 =
            {
                    {"DoctorName : Ajit Saste", "Hospital Address : Delhi", "Exp : 5yrs", "Mobile No : 9898989898","600"},
                    {"DoctorName : Prasad Pawar", "Hospital Address : Meerut", "Exp : 8yrs", "Mobile No : 9989899999","700"},
                    {"DoctorName : Zeeshan Khan", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No : 6395000011","900"},
                    {"DoctorName : Deepak Deshmukh", "Hospital Address : Gorakhpur", "Exp : 10yrs", "Mobile No : 9720200001","800"},
                    {"DoctorName : Ashok Kumar", "Hospital Address : Chennai", "Exp : 12yrs", "Mobile No : 8912111132","900"},

            };
TextView tv;
Button btn;
String[][] doctor_details = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String tittle = it.getStringExtra("tittle");
        tv.setText(tittle);

        if(tittle.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else
        if(tittle.compareTo("Dietician") == 0)
            doctor_details = doctor_details2;
        else
        if(tittle.compareTo("Dentist") == 0)
            doctor_details = Doctor_details3;
        else
        if(tittle.compareTo("Surgen") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });


    }
}