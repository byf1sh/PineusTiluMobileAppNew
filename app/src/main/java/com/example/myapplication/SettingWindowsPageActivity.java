package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingWindowsPageActivity extends AppCompatActivity {
    ConstraintLayout buttonname_profilepage, buttonaddress_profilepage, buttonnumber_profilepage, buttonemail_profilepage, buttoncustomersupport_profilepage, buttonFAQ_profilepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_windows_page);
        buttonname_profilepage = findViewById(R.id.buttonname_profilepage);
        buttonaddress_profilepage = findViewById(R.id.buttonaddress_profilepage);
        buttonnumber_profilepage = findViewById(R.id.buttonnumber_profilepage);
        buttonemail_profilepage = findViewById(R.id.buttonemail_profilepage);
        buttoncustomersupport_profilepage = findViewById(R.id.buttoncustomersupport_profilepage);
        buttonFAQ_profilepage = findViewById(R.id.buttonFAQ_profilepage);


        buttonname_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingWindowsPageActivity.this, ChangePassword1Activity.class);
                startActivity(intent);
            }
        });
        buttonaddress_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingWindowsPageActivity.this, ChangeEmailActivity.class);
                startActivity(intent);
            }
        });

        buttonnumber_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingWindowsPageActivity.this, ChangeContactNumberActivity.class);
                startActivity(intent);
            }
        });
        buttonemail_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingWindowsPageActivity.this, ChangeEmailActivity.class);
                startActivity(intent);
            }
        });
        buttoncustomersupport_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingWindowsPageActivity.this, CustomerSupportActivity.class);
                startActivity(intent);
            }
        });
        buttonFAQ_profilepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingWindowsPageActivity.this, FAQActivity.class);
                startActivity(intent);
            }
        });
    }
}

