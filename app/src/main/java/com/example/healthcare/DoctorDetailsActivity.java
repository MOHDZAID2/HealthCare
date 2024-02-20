package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
ArrayList list;
SimpleAdapter sa;
HashMap<String,String> item;
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
        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","coin fees:"+doctor_details[i][4]+"/-");
            list.add(item);

        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,new String[]
        {"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
                );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

     lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
             it.putExtra("text1",tittle);
             it.putExtra("text2",doctor_details1[i][0]);
             it.putExtra("text3",doctor_details1[i][1]);
             it.putExtra("text4",doctor_details1[i][3]);
             it.putExtra("text5",doctor_details1[i][4]);
             startActivity(it);


         }
     });


    }
}