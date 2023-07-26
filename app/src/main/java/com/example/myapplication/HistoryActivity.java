package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_history);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(HistoryActivity.this,HomeMainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.bottom_history:
                    return true;
                case R.id.bottom_notif:
                    Intent intent2 = new Intent(HistoryActivity.this,NotifActivity.class);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    Intent intent3 = new Intent(HistoryActivity.this,ProfileActivity.class);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
    }
}