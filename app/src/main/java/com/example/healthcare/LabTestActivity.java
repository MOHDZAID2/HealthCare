package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {
    private  String[][] Packages =
            {
                    {"Package 1 : Full Body Checkup","","","","999"},
                    {"Package 2 : Blood Glucose Fasting","","","","299"},
                    {"Package 3  : COVID-19 AntiBody - IgG","","","","899"},
                    {"Package 4 : Thyroid Check","","","","499"},
                    {"Package 5 : Immunity Check","","","","699"}

            };
    private  String[] Package_details = {
              "Blood Glucose Fasting\n" +
              "Complete Hemogram\n" +
                      "HbAIc\n" +
                      " Iron Studies\n" +
                      "Kidney Function Test\n" +
                      "LDH Lactate Dehydrogenase, Serum\n" +
                      "Lipid Profile\n" +
                      "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroid profile-Total (T3, T4 & TSH Ultra-sensittive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hyprpoxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sp;
    Button btnGoTOCart,btnBack;
    ListView listView;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        btnGoTOCart = findViewById(R.id.buttonLTGoToCart);
        btnBack = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<Packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", Packages[i][0]);
            item.put("line2", Packages[i][1]);
            item.put("line3", Packages[i][2]);
            item.put("line4", Packages[i][3]);
            item.put("line5", "Total Cost:"+Packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
        new int[]{R.id.line_a, R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",Packages[i][0]);
                it.putExtra("text2",Package_details[i]);
                it.putExtra("text3",Packages[i][4]);
                startActivity(it);
            }
        });
        btnGoTOCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));

            }
        });
    }
}