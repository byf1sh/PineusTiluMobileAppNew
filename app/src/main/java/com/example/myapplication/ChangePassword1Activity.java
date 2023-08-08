package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword1Activity extends AppCompatActivity {
    private ImageView buttonback;
    TextView buttonforgetpasword;
    private Button buttonchangepassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password1);
        buttonback = findViewById(R.id.buttonback);
        buttonforgetpasword = findViewById(R.id.buttonforgetpassword);
        buttonchangepassword = findViewById(R.id.buttonchangepassword);


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingWindows();
            }
        });
        buttonforgetpasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ChangePassword1Activity.this, ChangePassword12Activity.class);
                startActivity(intent);
            }
        });
        buttonchangepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingWindowsPage();
            }
        });

    Intent intent = getIntent();
    String phone = intent.getStringExtra("phone");


    }

    public void SettingWindows() {
        Intent intent = new Intent(this, SettingWindowsPageActivity.class);
        startActivity(intent);
    }
    public void SettingWindowsPage(){
        Intent intent = new Intent(this, SettingWindowsPageActivity.class);
        startActivity(intent);
    }

}