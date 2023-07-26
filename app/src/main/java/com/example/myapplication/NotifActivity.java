package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NotifActivity extends AppCompatActivity {

    TextView riwayat, upcoming, namanana;
    ConstraintLayout cl1, cl2, notification, notifpurchased, notifpurchased2, notification1;

    ImageView background;
    String getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_notif);
        int hijau = ContextCompat.getColor(getApplicationContext(), R.color.green);
        int abuabu = ContextCompat.getColor(getApplicationContext(), R.color.abu3);

        upcoming = findViewById(R.id.upcoming);
        riwayat = findViewById(R.id.riwayat);
        cl1 = findViewById(R.id.cl1);
        cl2 = findViewById(R.id.cl2);
        background = findViewById(R.id.background);
        notification = findViewById(R.id.notification);
        notifpurchased = findViewById(R.id.notifpuchased);
        notifpurchased2 = findViewById(R.id.notifpuchased2);
        notification1 = findViewById(R.id.notification1);
        namanana=findViewById(R.id.namanana);

        getData();

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upcoming.setTextColor(hijau);
                riwayat.setTextColor(abuabu);
                if (cl1.getVisibility() == View.GONE) {
                    cl1.setVisibility(View.VISIBLE);
                    cl2.setVisibility(View.GONE);
                    background.setVisibility(View.VISIBLE);

                } else {
                    cl1.setVisibility(View.VISIBLE);
                }
            }
        });

        notifpurchased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notification.getVisibility() == View.GONE){
                    notification.setVisibility(View.VISIBLE);
                }
                else {
                    notification.setVisibility(View.GONE);
                }
            }
        });

        notifpurchased2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notification1.getVisibility() == View.GONE){
                    notification1.setVisibility(View.VISIBLE);
                }
                else {
                    notification1.setVisibility(View.GONE);
                }
            }
        });


        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riwayat.setTextColor(hijau);
                upcoming.setTextColor(abuabu);
                if (cl2.getVisibility() == View.GONE) {
                    cl2.setVisibility(View.VISIBLE);
                    cl1.setVisibility(View.GONE);
                    background.setVisibility(View.GONE);
                } else {
                    cl2.setVisibility(View.VISIBLE);
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(NotifActivity.this,HomeMainActivity.class);
                    String namantf = namanana.getText().toString();
                    intent.putExtra("username",namantf);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.bottom_history:
                    Intent intent2 = new Intent(NotifActivity.this,EntertainmentActivity.class);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.bottom_notif:
                    return true;
                case R.id.bottom_profile:
                    Intent intent3 = new Intent(NotifActivity.this,ProfileActivity.class);
                    String namantf1 = namanana.getText().toString();
                    intent3.putExtra("username",namantf1);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
    }
    public void getData(){
        Intent intent = getIntent();

        getdata = intent.getStringExtra("username");
        namanana.setText(getdata);

    }
}