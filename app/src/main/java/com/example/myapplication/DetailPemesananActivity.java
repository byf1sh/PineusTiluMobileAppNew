package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DetailPemesananActivity extends AppCompatActivity {
    CardView btn_book_dp, btn_kembali_dp;
    TextView plus_dp, jumlahorang_dp, minus_dp, cekinTgl_dp, deck, fas_dp, harga_dp;
    String value, destTanggal, destLokasi, param, destHarga, imageNtf, destTitle, userRess, avail, image, name, jml, Avail, Name, tanggalawal, tanggalakhir, mainNtf, childNtf, childNtfadm, mainNtfadm, imageNtfadm;
    DatabaseReference databaseReference, childRef, childRef1, databaseReference1, databaseReference2, childRef2;

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
        childRef1 = databaseReference1.child("notifP" + destTitle + " " + destLokasi);

        String pathNtfAdm = "users/" + Name + "/Notif/NotifPurchased";
        databaseReference2 = FirebaseDatabase.getInstance().getReference(pathNtfAdm);
        childRef2 = databaseReference2.child("notifAd" + destTitle + " " + destLokasi);

        /*threedots = findViewById(R.id.threedots_pp);*/

        btn_book_dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (param.equals("Rescheadule")) {
                    Intent intent = new Intent(DetailPemesananActivity.this, RescheduleCancellationActivity.class);
                    String jmlah = jumlahorang_dp.getText().toString();
                    intent.putExtra("username", Name);
                    intent.putExtra("tanggalawal", tanggalawal);
                    intent.putExtra("tanggalakhir", tanggalakhir);
                    intent.putExtra("resDeck", destTitle);
                    intent.putExtra("resLokasi", destLokasi);
                    intent.putExtra("resHarga", destHarga);
                    intent.putExtra("resJumlah", jmlah);
                    intent.putExtra("userRes", userRess);
                    intent.putExtra("availRes", Avail);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DetailPemesananActivity.this, HomeMainActivity.class);
                    intent.putExtra("username", Name);
                    intent.putExtra("tanggalawal", tanggalawal);
                    intent.putExtra("tanggalakhir", tanggalakhir);
                    updateData();
                    sendNotification();
                    startActivity(intent);
                }

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
        destTanggal = intent.getStringExtra("tanggal");
        String destFasilites = intent.getStringExtra("fasilities2");
        destHarga = intent.getStringExtra("harga");
        Avail = intent.getStringExtra("avail");
        Name = intent.getStringExtra("name");
        tanggalawal = intent.getStringExtra("tanggalawal");
        tanggalakhir = intent.getStringExtra("tanggalakhir");
        param = intent.getStringExtra("param");
        userRess = intent.getStringExtra("userRes");

        harga_dp.setText("Rp. " + destHarga);
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
        DatabaseReference dataRef;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataRef = database.getReference(destTanggal + "/" + destLokasi + "/" + destTitle+"/dataImage");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Method ini akan dipanggil setiap kali data di bawah referensi berubah
                // DataSnapshot berisi data dari database Firebase
                // Gunakan dataSnapshot untuk mendapatkan nilai data

                if (dataSnapshot.exists()) {
                    // Data ditemukan di bawah referensi
                    // Ambil nilai data
                    value = dataSnapshot.getValue(String.class);
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
        image = "https://www.selera.id/wp-content/uploads/2022/07/shutterstock_358158596_3.jpg";
        jml = jumlahorang_dp.getText().toString().trim();

        if (Avail.equals("Tersedia")) {
            Avail = "Penuh";
        } else if (Avail.equals("Highseason")) {
            Avail = "Penuh";
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
        mainNtf = "You've purchased" + destTitle;
        childNtf = "Booking ID 000001";
        imageNtf = "";

        mainNtfadm = "Guest Loyality Program";
        childNtfadm = "whatever";
        imageNtfadm = "";

        long timestamp = System.currentTimeMillis();
        //1
        NotifClass notifClass = new NotifClass(mainNtf, childNtf, imageNtf, tanggalawal, destHarga, destLokasi, timestamp, destTitle, tanggalakhir, jml);

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

        //2
        NotifClass notifClass2 = new NotifClass(mainNtfadm, childNtfadm, imageNtfadm, tanggalawal, destHarga, destLokasi, timestamp, destTitle, tanggalakhir, jml);

        childRef2.setValue(notifClass2).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void showPopupMenuDP(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu); // Menu yang ingin ditampilkan di PopupMenu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Tambahkan logika untuk mengatasi setiap item yang dipilih dari menu dropdown
                switch (item.getItemId()) {
                    case R.id.settings:
                        // Aksi untuk menu item 1
                        return true;
                    case R.id.faq:
                        Intent intent1 = new Intent(DetailPemesananActivity.this, FAQActivity.class);
                        startActivity(intent1);
                        // Aksi untuk menu item 2
                        return true;
                    case R.id.tutorial:
                        Intent intent2 = new Intent(DetailPemesananActivity.this, BookingDateTutorialActivity.class);
                        startActivity(intent2);
                        return true;
                    // Tambahkan lebih banyak case sesuai dengan kebutuhan Anda
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

}