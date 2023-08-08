package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingWindowsPageActivity extends AppCompatActivity {
    private Button buttonname_profilepage;
    private Button buttonaddress_profilepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_windows_page);
        buttonname_profilepage = findViewById(R.id.buttonname_profilepage);
        buttonaddress_profilepage = findViewById(R.id.buttonaddress_profilepage);
        buttonname_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword1();
            }
        });
//        buttonaddress_profilepage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddressPage();
//            }
//        });

    }


    public void ChangePassword1() {
        Intent intent = new Intent(this, ChangePassword1Activity.class);
        startActivity(intent);
    }
//    public void AddressPage() {
//        Intent intent = new Intent(this, AddressPageActivity.class);
//        startActivity(intent);
//    }
}

