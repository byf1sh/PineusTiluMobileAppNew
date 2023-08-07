package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity {

    TextView texx1, credit, payment, texx2, order, texx3;
    ImageView panah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        texx1 = findViewById(R.id.texx1);
        credit = findViewById(R.id.credit);
        payment = findViewById(R.id.payment);
        texx2 = findViewById(R.id.texx2);
        order = findViewById(R.id.order);
        texx3 = findViewById(R.id.texx3);
        panah = findViewById(R.id.panah);

            panah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FAQActivity.this, ProfileSettingsActivity.class);
                    startActivity(intent);
                }
            });

        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texx1.getVisibility() == View.GONE) {
                    texx1.setVisibility(View.VISIBLE);
                } else {
                    texx1.setVisibility(View.GONE);
                }
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texx2.getVisibility() == View.GONE) {
                    texx2.setVisibility(View.VISIBLE);
                } else {
                    texx2.setVisibility(View.GONE);
                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (texx3.getVisibility() == View.GONE) {
                    texx3.setVisibility(View.VISIBLE);
                } else {
                    texx3.setVisibility(View.GONE);
                }
            }
        });
    }
}