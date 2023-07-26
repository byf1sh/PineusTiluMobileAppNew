package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity2 extends AppCompatActivity {

    TextView nameasd, emailasd, usernameasd,contactasd;
    DatabaseReference databaseReference, childRef;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameasd=findViewById(R.id.nameasd);
        emailasd=findViewById(R.id.emailasd);
        usernameasd=findViewById(R.id.usernameasd);
        contactasd=findViewById(R.id.contactasd);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        childRef = databaseReference.child("admin");

        eventListener = childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HelperClass helperClass = dataSnapshot.getValue(HelperClass.class);
                if (helperClass != null){
                    nameasd.setText(helperClass.getName());
                    emailasd.setText(helperClass.getEmail());
                    usernameasd.setText(helperClass.getUsername());
                    contactasd.setText(helperClass.getPassword());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}