package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RescheaduleMainActivity extends AppCompatActivity {

    TextView tanggal,rangetanggal, locationDetails, purchasedOnDetails, priceDetailsDetails, dekperson, completed,purchasedOnDetails1,userResTV;
    String deck, harga, lokasi, jumlah, tanggalfirst, tanggallast, username, userRes;
    DatabaseReference databaseReference1,childRef1,childRef2,childRef3;
    DatabaseReference sourceRef, sourceRef2, sourceRef3;
    DatabaseReference destinationRef, destinationRef2, destinationRef3;
    MyAdapterR1 adapter1;
    MyAdapterR2 adapter2;
    MyAdapterR3 adapter3;
    List<DataClass> dataList1,dataList2,dataList3;
    ValueEventListener eventListener;
    RecyclerView recyclerView1,recyclerView2,recyclerView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescheadule_main);

        tanggal=findViewById(R.id.tanggal);
        rangetanggal=findViewById(R.id.rangetanggal);
        locationDetails=findViewById(R.id.locationDetails);
        purchasedOnDetails=findViewById(R.id.purchasedOnDetails);
        priceDetailsDetails=findViewById(R.id.priceDetailsDetails);
        dekperson=findViewById(R.id.dekperson);
        recyclerView1=findViewById(R.id.view_deck1);
        recyclerView2=findViewById(R.id.view_deck2);
        completed=findViewById(R.id.completed);
        recyclerView3=findViewById(R.id.view_deck3);
        purchasedOnDetails1=findViewById(R.id.purchasedOnDetails1);
        userResTV=findViewById(R.id.userRes);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(RescheaduleMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(RescheaduleMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(RescheaduleMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(linearLayoutManager3);
        getData();
        storeData();

    }

    public void getData(){
        Intent intent = getIntent();
        String tglawal = intent.getStringExtra("date");
        String tglakhir = intent.getStringExtra("datelast");
        deck = intent.getStringExtra("deck");
        harga = intent.getStringExtra("harga");
        lokasi = intent.getStringExtra("lokasi");
        jumlah = intent.getStringExtra("jumlah");
        tanggalfirst = intent.getStringExtra("tglawal");
        tanggallast = intent.getStringExtra("tglakhir");
        username = intent.getStringExtra("name");
        userRes = intent.getStringExtra("resData");
        
        tanggal.setText(tglawal);
        rangetanggal.setText(tglakhir);
        locationDetails.setText(deck+" "+lokasi);
        purchasedOnDetails.setText(tanggalfirst);
        purchasedOnDetails1.setText(tanggallast);
        priceDetailsDetails.setText(harga);
        dekperson.setText("1 Deck "+jumlah+" Person");
        completed.setText(username);
        userResTV.setText(userRes);

    }
    public void storeData(){
        sourceRef = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu1");
        sourceRef2 = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu2");
        sourceRef3 = FirebaseDatabase.getInstance().getReference("Deck/Pineustilu3");
        destinationRef = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString()).child("Pineustilu1");
        destinationRef2 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString()).child("Pineustilu2");
        destinationRef3 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString()).child("Pineustilu3");

        checkIfDateExistsAndCopyReferences();
    }
    private void checkIfDateExistsAndCopyReferences() {
        String date = tanggal.getText().toString();
        DatabaseReference dateReference = FirebaseDatabase.getInstance().getReference(date);

        // Check if the date exists in the database
        dateReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Data sudah ada tampilkan data yang sudah ada
                    fetchDatafromDB();
                } else {
                    // The date does not exist, proceed with copying references
                    copyReferencesFromPineustilu1ToNewDeck();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Error occurred while checking the date reference
                Toast.makeText(RescheaduleMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void copyReferencesFromPineustilu1ToNewDeck() {
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
                    Toast.makeText(RescheaduleMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(RescheaduleMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(RescheaduleMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RescheaduleMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(RescheaduleMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(RescheaduleMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RescheaduleMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(RescheaduleMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(RescheaduleMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fetchDatafromDB(); // mengambil data dari db
    }
    public void fetchDatafromDB(){
        //1
        dataList1 = new ArrayList<>();
        adapter1 = new MyAdapterR1(RescheaduleMainActivity.this,dataList1);
        recyclerView1.setAdapter(adapter1);

        databaseReference1 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString());
        childRef1 = databaseReference1.child("Pineustilu1");

        eventListener = childRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList1.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataList1.add(dataClass);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //2
        dataList2 = new ArrayList<>();
        adapter2 = new MyAdapterR2(RescheaduleMainActivity.this,dataList2);
        recyclerView2.setAdapter(adapter2);

        databaseReference1 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString());
        childRef2 = databaseReference1.child("Pineustilu2");

        eventListener = childRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList2.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataList2.add(dataClass);
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //3
        dataList3 = new ArrayList<>();
        adapter3 = new MyAdapterR3(RescheaduleMainActivity.this,dataList3);
        recyclerView3.setAdapter(adapter3);

        databaseReference1 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString());
        childRef3 = databaseReference1.child("Pineustilu3");

        eventListener = childRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList3.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataList3.add(dataClass);
                }
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}