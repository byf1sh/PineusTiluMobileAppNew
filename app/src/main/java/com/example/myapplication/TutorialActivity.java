package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    TextView textView6, textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
    }
}