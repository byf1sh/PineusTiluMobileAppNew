package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeContactNumberActivity extends AppCompatActivity {

    EditText changeNumContact;
    TextView textView;
    String NumContact;
    DatabaseReference reference;
    String usernameUser;
    Button saveButton;
    ImageView panah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_contact_number);

        reference = FirebaseDatabase.getInstance().getReference("users");

        changeNumContact = findViewById(R.id.changeNumContact);
        saveButton = findViewById(R.id.saveButton);

        showData();

        panah = findViewById(R.id.panah);

        panah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeContactNumberActivity.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged()){
                    Toast.makeText(ChangeContactNumberActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangeContactNumberActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isNameChanged() {
        if (!NumContact.equals(changeNumContact.getText().toString())) {
            reference.child(usernameUser).child("name").setValue(changeNumContact.getText().toString());
            NumContact = changeNumContact.getText().toString();
            return true;

        } else {
            return false;
        }
    }

    public void showData() {
        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        NumContact = intent.getStringExtra("name");

        changeNumContact.setText(NumContact);
    }
}