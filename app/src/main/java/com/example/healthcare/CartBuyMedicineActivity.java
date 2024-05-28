package com.example.healthcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView tvTotal;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnCheckout, btnBack;
    private String[][] packages ={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        dateButton = findViewById(R.id.buttonBMCartDate);
        //timeButton = findViewById(R.id.buttonCartTime);
        btnCheckout = findViewById(R.id.buttonBMCartCheckOut);
        btnBack = findViewById(R.id.buttonBMCartBack);
        tvTotal = findViewById(R.id.textViewBMCartTotalCost);
        lst = findViewById(R.id.listViewBmCart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();

        // Update this line to use the correct Database constructor
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        float totalAmount = 0;
        ArrayList<String> dbData = db.getCartData(username, "lab");
       // Toast.makeText(getApplicationContext(), "" + dbData, Toast.LENGTH_SHORT).show();

        packages = new String[dbData.size()][5];
        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i);
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$")); // Assuming "$" as delimiter, adjust as per your actual delimiter
            packages[i][0] = strData[0];
            packages[i][1] = ""; // Set appropriate value
            packages[i][2] = ""; // Set appropriate value
            packages[i][3] = ""; // Set appropriate value
            packages[i][4] = "Cost : " + strData[1] + "/-";
            totalAmount += Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost: " + totalAmount);

        list = new ArrayList<>();

        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

        lst.setAdapter(sa);
        btnBack.setOnClickListener(v -> startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class)));

        btnCheckout.setOnClickListener(v -> {
            Intent it = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
            it.putExtra("price", tvTotal.getText());
            it.putExtra("date", dateButton.getText());
            startActivity(it);
            });

            // Initialize DatePickerDialog
            datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        dateButton.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

            dateButton.setOnClickListener(v -> datePickerDialog.show());


    }
}