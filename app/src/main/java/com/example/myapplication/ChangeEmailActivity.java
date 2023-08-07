package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeEmailActivity extends AppCompatActivity {
    EditText changeEmail;
    String stringEmail,usernameUser;
    DatabaseReference reference;
    Button saveEmail;
    ImageView panah;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        reference = FirebaseDatabase.getInstance().getReference("users");

        changeEmail=findViewById(R.id.changeEmail);
        saveEmail=findViewById(R.id.saveEmail);

        showDataEmail();

        panah = findViewById(R.id.panah);

        panah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeEmailActivity.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });


        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmailChanged()){
                    Toast.makeText(ChangeEmailActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangeEmailActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean isEmailChanged() {
        if (!stringEmail.equals(changeEmail.getText().toString())) {
            reference.child(usernameUser).child("email").setValue(changeEmail.getText().toString());
            stringEmail = changeEmail.getText().toString();
            return true;

        } else {
            return false;
        }
    }
    public void showDataEmail() {
        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        stringEmail = intent.getStringExtra("email");

        changeEmail.setText(stringEmail);
    }
}