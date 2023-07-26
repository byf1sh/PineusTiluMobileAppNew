package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView titleName, titleUsername;
    DatabaseReference databaseReference, childRef;
    ValueEventListener eventListener;
    ImageView btnsetting;
    String usernameUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.contact);
        profileEmail = findViewById(R.id.profileEmail);
        profileUsername = findViewById(R.id.profileName);
        profilePassword = findViewById(R.id.profilePassword);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        btnsetting = findViewById(R.id.btnsetting);

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        showUserData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(ProfileActivity.this,HomeMainActivity.class);
                    String homeName = profileUsername.getText().toString().trim();
                    intent.putExtra("username", homeName);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.bottom_history:
                    Intent intent1 = new Intent(ProfileActivity.this,HistoryActivity.class);
                    startActivity(intent1);
                    finish();
                    return true;
                case R.id.bottom_notif:
                    Intent intent2 = new Intent(ProfileActivity.this,NotifActivity.class);
                    String notifName = profileUsername.getText().toString().trim();
                    intent2.putExtra("username", notifName);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    return true;
            }
            return false;
        });

    }

    public void showUserData() {

        Intent intent = getIntent();

        String usernameUser = intent.getStringExtra("username");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        childRef = databaseReference.child(usernameUser);

        eventListener = childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HelperClass helperClass = dataSnapshot.getValue(HelperClass.class);
                if (helperClass != null){
                    titleName.setText(helperClass.getName());
                    titleUsername.setText(helperClass.getUsername());
                    profileEmail.setText(helperClass.getEmail());
                    profileUsername.setText(helperClass.getUsername());
                    profileName.setText(helperClass.getName());
                    profilePassword.setText(helperClass.getPassword());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void passUserData() {
        Intent intent = new Intent(ProfileActivity.this, ProfileSettingsActivity.class);

        String Contactstg = titleName.getText().toString();
        String Usernamestg = titleUsername.getText().toString();
        String Emailstg = profileEmail.getText().toString();
        String Passwordstg = profilePassword.getText().toString();

        intent.putExtra("username", Usernamestg);
        intent.putExtra("contact", Contactstg);
        intent.putExtra("email", Emailstg);
        intent.putExtra("password", Passwordstg);

        startActivity(intent);
    }
}