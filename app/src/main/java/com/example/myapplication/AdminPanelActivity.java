package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminPanelActivity extends AppCompatActivity {

    TextView textView,textView2,textView3,textView4,textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        textView=findViewById(R.id.boobs);
        textView2 = findViewById(R.id.boobs2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataRef = database.getReference("Harga/Pineustilu1/normal");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Method ini akan dipanggil setiap kali data di bawah referensi berubah
                // DataSnapshot berisi data dari database Firebase
                // Gunakan dataSnapshot untuk mendapatkan nilai data

                if (dataSnapshot.exists()) {
                    // Data ditemukan di bawah referensi
                    // Ambil nilai data
                    String value = dataSnapshot.getValue(String.class);
                    textView.setText(value);
                    // Lakukan sesuatu dengan nilai data
                } else {
                    // Data tidak ditemukan di bawah referensi
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Method ini akan dipanggil jika ada error dalam mengambil data
                // Tindakan yang bisa dilakukan ketika terjadi error
            }
        });

    }
}