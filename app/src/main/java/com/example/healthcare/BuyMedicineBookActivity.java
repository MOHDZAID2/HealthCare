package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname,edaddres,edcontact,edpincode;
    Button btnbooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edname = findViewById(R.id.editTextBMBFullName);
        edaddres = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContactNumber);
        edpincode = findViewById(R.id.editTextBMBPinCode);
        btnbooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");
        /* String time = intent.getStringExtra("time"); */

        btnbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                /* CartLabActivity.Database db = new CartLabActivity.Database(getApplicationContext(),"healthcare",null,1); */
                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                db.addOrder(username, edname.getText().toString(), edaddres.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), "", Float.parseFloat(price[1].toString()), "medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(BuyMedicineBookActivity.this, "Your booking is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });

    }
}