package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class ChangePassword12Activity extends AppCompatActivity {
    private ImageView buttonback;

    private CountryCodePicker CountryCodePicker;
    private EditText login_phonenumber;
    private Button buttonnext;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_12);

        buttonback = findViewById(R.id.buttonback);
        CountryCodePicker = findViewById(R.id.CountryCodePicker);
        login_phonenumber = findViewById(R.id.login_phonenumber);
        buttonnext = findViewById(R.id.buttonnext);
        progressbar = findViewById(R.id.progressbar);

        progressbar.setVisibility(View.GONE);
        CountryCodePicker.registerCarrierNumberEditText(login_phonenumber);
        buttonnext.setOnClickListener((v)->{
            if(!CountryCodePicker.isValidFullNumber()){
                login_phonenumber.setError("Phone Number not Valid");
                return;
            }
            Intent intent = new Intent(ChangePassword12Activity.this, ChangePassword13Activity.class);
            intent.putExtra("phone", CountryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });

        progressbar.setVisibility(View.GONE);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword1();
            }
        });




    }
    public void ChangePassword1(){
        Intent intent = new Intent(this, ChangePassword1Activity.class);
        startActivity(intent);
    }

}