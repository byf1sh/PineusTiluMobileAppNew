package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword14Activity extends AppCompatActivity {
    private ImageView buttonback;
    private Button buttonnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_14);
        buttonback = findViewById(R.id.buttonback);
        buttonnext = findViewById(R.id.buttonnext);

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword13();
            }
        });
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingWindowsPage();
            }
        });

    }
    public void ChangePassword13(){
        Intent intent = new Intent(this, ChangePassword13Activity.class);
        startActivity(intent);
    }
    public void SettingWindowsPage(){
        Intent intent = new Intent(this, SettingWindowsPageActivity.class);
        startActivity(intent);
    }
}