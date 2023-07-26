package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeNameActivity extends AppCompatActivity {

    EditText changeName;
    Button saveName;
    DatabaseReference reference;
    String usernameUser, stringName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        reference = FirebaseDatabase.getInstance().getReference("users");

        changeName = findViewById(R.id.changeName);
        saveName = findViewById(R.id.saveName);

        showDataName();

        saveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged()) {
                    Toast.makeText(ChangeNameActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChangeNameActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isNameChanged() {
        if (!stringName.equals(changeName.getText().toString())) {
            reference.child(usernameUser).child("username").setValue(changeName.getText().toString());
            stringName = changeName.getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private void showDataName() {
        Intent intent = getIntent();
        usernameUser = intent.getStringExtra("username");
        stringName = intent.getStringExtra("username");

        changeName.setText(stringName);
    }
}