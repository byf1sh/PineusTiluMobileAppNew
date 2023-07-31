package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookingDeckTutorialActivity extends AppCompatActivity {

    Button btndek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_deck_tutorial);

        btndek = findViewById(R.id.btntutordek);

        btndek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingDeckTutorialActivity.this, HomeMainActivity.class);
                startActivity(intent);
            }
        });
    }
}