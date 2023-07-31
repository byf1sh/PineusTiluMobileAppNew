package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingDateTutorialActivity extends AppCompatActivity {

    Button btnbooktanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_date_tutorial);

        btnbooktanggal = findViewById(R.id.btnbooktanggal);

        btnbooktanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingDateTutorialActivity.this, BookingDeckTutorialActivity.class);
                startActivity(intent);
            }
        });

    }
}