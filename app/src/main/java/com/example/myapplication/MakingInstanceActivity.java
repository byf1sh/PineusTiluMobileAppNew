package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class MakingInstanceActivity extends AppCompatActivity {

    TextView textView5;
    Button button;
    String date;
    private DatabaseReference sourceRef, sourceRef2, sourceRef3;
    private DatabaseReference destinationRef, destinationRef2, destinationRef3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_instance);

        textView5= findViewById(R.id.textView5);
        button = findViewById(R.id.button);
        sourceRef = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu1");
        sourceRef2 = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu2");
        sourceRef3 = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu3");
        destinationRef = FirebaseDatabase.getInstance().getReference(textView5.getText().toString()).child("Pineustilu1");
        destinationRef2 = FirebaseDatabase.getInstance().getReference(textView5.getText().toString()).child("Pineustilu2");
        destinationRef3 = FirebaseDatabase.getInstance().getReference(textView5.getText().toString()).child("Pineustilu3");

        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView5.setText(date);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyReferencesFromPineustilu1ToNewDeck();
            }
        });
    }

    public void uploadData(){

        String title = "Deck1";
        String avail = "Tersedia";
        String image = " ";
        String asd = " ";
        String ghj = " ";


        DataClass dataClass = new DataClass(title, avail, image,asd,ghj);

        String currentDate = textView5.getText().toString();

        FirebaseDatabase.getInstance().getReference(currentDate).child("Pineustilu1").child("Deck1").setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MakingInstanceActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MakingInstanceActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void copyReferencesFromPineustilu1ToNewDeck() {
        sourceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Loop melalui semua child dan salin ke referensi baru
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String childKey = childSnapshot.getKey();
                        Object childValue = childSnapshot.getValue();
                        destinationRef.child(childKey).setValue(childValue);
                    }
                    // Tampilkan pesan sukses
                    Toast.makeText(MakingInstanceActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(MakingInstanceActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(MakingInstanceActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        sourceRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Loop melalui semua child dan salin ke referensi baru
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String childKey = childSnapshot.getKey();
                        Object childValue = childSnapshot.getValue();
                        destinationRef2.child(childKey).setValue(childValue);
                    }
                    // Tampilkan pesan sukses
                    Toast.makeText(MakingInstanceActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(MakingInstanceActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(MakingInstanceActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        sourceRef3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Loop melalui semua child dan salin ke referensi baru
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String childKey = childSnapshot.getKey();
                        Object childValue = childSnapshot.getValue();
                        destinationRef3.child(childKey).setValue(childValue);
                    }
                    // Tampilkan pesan sukses
                    Toast.makeText(MakingInstanceActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(MakingInstanceActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(MakingInstanceActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}