package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangePasswordActivity extends AppCompatActivity {

    String usernameUser, stringPassword;
    EditText currentPassword, newPassword;
    Button btnChangePassword;
    DatabaseReference reference;
    TextView btnforgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        reference = FirebaseDatabase.getInstance().getReference("users");

        currentPassword = findViewById(R.id.currentPassword);
        btnChangePassword = findViewById(R.id.btnChangePasword);
        newPassword = findViewById(R.id.newPassword);
        btnforgetpassword = findViewById(R.id.btnforgetpassword);

        showDataPassword();

        btnforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this, ChangePassword12Activity.class);
                startActivity(intent);
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordChanged()) {
                    Toast.makeText(ChangePasswordActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isPasswordChanged() {
        if (!stringPassword.equals(newPassword.getText().toString())) {
            reference.child(usernameUser).child("password").setValue(newPassword.getText().toString());
            stringPassword = newPassword.getText().toString();
            return true;

        } else {
            return false;
        }
    }

    public void showDataPassword() {
        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        stringPassword = intent.getStringExtra("password");
        currentPassword.setText(stringPassword);
    }
}