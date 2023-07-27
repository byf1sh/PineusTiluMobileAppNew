package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailPemesananActivity extends AppCompatActivity {
    CardView btn_book_dp, btn_kembali_dp;
    TextView plus_dp, jumlahorang_dp, minus_dp, cekinTgl_dp, deck, fas_dp, harga_dp;
    String destLokasi, destHarga, imageNtf, destTitle, title, avail, image, name, jml, Avail, Name, tanggalawal, tanggalakhir, mainNtf, childNtf;
    DatabaseReference databaseReference, childRef, childRef1, databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemesanan);
        btn_book_dp = findViewById(R.id.btn_book_dp);
        btn_kembali_dp = findViewById(R.id.btn_kembali_dp);
        minus_dp = findViewById(R.id.minus_dp);
        jumlahorang_dp = findViewById(R.id.jumlahorang_dp);
        plus_dp = findViewById(R.id.plus_dp);
        cekinTgl_dp = findViewById(R.id.cekinTgl_dp);
        deck = findViewById(R.id.deck);
        fas_dp = findViewById(R.id.fas_dp);
        harga_dp = findViewById(R.id.harga_dp);

        showData();

        String path = tanggalawal + "/" + destLokasi.trim();
        databaseReference = FirebaseDatabase.getInstance().getReference(path);
        childRef = databaseReference.child(destTitle);

        String pathNtf = "users/" + Name + "/Notif/NotifPurchased";
        databaseReference1 = FirebaseDatabase.getInstance().getReference(pathNtf);
        childRef1 = databaseReference1.child("notifP");

        /*threedots = findViewById(R.id.threedots_pp);*/

        btn_book_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPemesananActivity.this, HomeMainActivity.class);
                intent.putExtra("username", Name);
                intent.putExtra("tanggalawal", tanggalawal);
                intent.putExtra("tanggalakhir", tanggalakhir);
                updateData();
                sendNotification();
                startActivity(intent);
            }
        });
        btn_kembali_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPemesananActivity.this, DetailBookingDeckActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showData() {
        Intent intent = getIntent();

        destTitle = intent.getStringExtra("deckDBD");
        destLokasi = intent.getStringExtra("lokasiDBD");
        String destTanggal = intent.getStringExtra("tanggal");
        String destFasilites = intent.getStringExtra("fasilities2");
        destHarga = intent.getStringExtra("harga");
        Avail = intent.getStringExtra("avail");
        Name = intent.getStringExtra("name");
        tanggalawal = intent.getStringExtra("tanggalawal");
        tanggalakhir = intent.getStringExtra("tanggalakhir");

        harga_dp.setText(destHarga);
        deck.setText(destTitle + " " + destLokasi);
        cekinTgl_dp.setText(destTanggal);
        fas_dp.setText(destFasilites);
    }

    public void decreaseJumlahOrang(View view) {
        int currentValue = Integer.parseInt(jumlahorang_dp.getText().toString());

        // Kurangi nilai TextView2 dan pastikan tidak kurang dari 1
        currentValue = Math.max(currentValue - 1, 1);

        jumlahorang_dp.setText(String.valueOf(currentValue));
    }

    public void increaseJumlahOrang(View view) {
        int currentValue = Integer.parseInt(jumlahorang_dp.getText().toString());
        if (destLokasi.contains("Pineustilu1") || destLokasi.contains("Pineustilu2")) {
            // Kurangi nilai TextView2 dan pastikan tidak kurang dari 1
            currentValue = Math.min(currentValue + 1, 6);

            jumlahorang_dp.setText(String.valueOf(currentValue));
        } else {
            currentValue = Math.min(currentValue + 1, 9);

            jumlahorang_dp.setText(String.valueOf(currentValue));
        }
    }

    public void updateData() {
        image = " ";
        jml = jumlahorang_dp.getText().toString().trim();

        if (Avail.equals("Tersedia")) {
            Avail = "Penuh";
        } else if (avail.equals("Penuh")) {
            Avail = "Tersedia";
        }

        DataClass dataClass = new DataClass(destTitle, Avail, image, Name, jml);

        childRef.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DetailPemesananActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetailPemesananActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNotification() {
        mainNtf = "You've purchased";
        childNtf = "Booking ID 000001";
        String Lokasi = destLokasi+", "+destTitle;
        imageNtf = "";

        NotifClass notifClass = new NotifClass(mainNtf, childNtf, imageNtf, tanggalawal, destHarga, Lokasi);

        childRef1.setValue(notifClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(DetailPemesananActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DetailPemesananActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}