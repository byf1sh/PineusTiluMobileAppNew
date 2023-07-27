package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.jar.Attributes;

public class HomeMainActivity extends AppCompatActivity {

    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    List<DataClass> dataList1,dataList2,dataList3;
    DatabaseReference databaseReference1,childRef1,childRef2,childRef3;
    ValueEventListener eventListener;
    MyAdapter1 adapter1;
    MyAdapter2 adapter2;
    MyAdapter3 adapter3;
    ImageButton btnbantuan, setTanggal;
    Dialog myDialog;
    TextView tanggal, rangetanggal;
    TextView close_btn;
    TextView Date, Date1, NameUser;
    String tanggalmulai, destContact, destEmail, destPassword;
    Button Btn;
    DatePickerDialog.OnDateSetListener setListener;
    DatabaseReference sourceRef, sourceRef2, sourceRef3;
    DatabaseReference destinationRef, destinationRef2, destinationRef3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        recyclerView1 = findViewById(R.id.view_deck1);
        recyclerView2 = findViewById(R.id.view_deck2);
        recyclerView3 = findViewById(R.id.view_deck3);
        btnbantuan =findViewById(R.id.btnbantuan);
        setTanggal=findViewById(R.id.setTanggal);
        tanggal = findViewById(R.id.tanggal);
        rangetanggal = findViewById(R.id.rangetanggal);
        NameUser = findViewById(R.id.NameUser);

        myDialog = new Dialog(this,R.style.dialog);

        tanggal.setText("15-4-2023");
        getData();


        btnbantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMainActivity.this,TutorialActivity.class);
                startActivity(intent);
            }
        });


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(HomeMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(HomeMainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView3.setLayoutManager(linearLayoutManager3);






        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        String srcName = NameUser.getText().toString().trim();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    return true;
                case R.id.bottom_history:
                    Intent intent = new Intent(HomeMainActivity.this,EntertainmentActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.bottom_notif:
                    Intent intent2 = new Intent(HomeMainActivity.this,NotifActivity.class);
                    String notifName = NameUser.getText().toString().trim();
                    intent2.putExtra("username", notifName);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    Intent intent3 = new Intent(HomeMainActivity.this,ProfileActivity.class);
                    String profileName = NameUser.getText().toString().trim();
                    intent3.putExtra("username", profileName);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
    }



    public void getData(){
        Intent intent = getIntent();

        String destName = intent.getStringExtra("username");
        String tanggalfirst = intent.getStringExtra("tanggalawal");
        String tanggalend = intent.getStringExtra("tanggalakhir");
        NameUser.setText(destName);

        if (tanggalfirst != null) {
            // Jalankan fungsi showData() karena tanggalfirst memiliki isinya
            tanggal.setText(tanggalfirst);
            rangetanggal.setText(tanggalend);
            fetchDatafromDB();
        }
    }

    public void show_Dialog(View v){


        myDialog.setContentView(R.layout.date_popup);

        close_btn = myDialog.findViewById(R.id.close_button);
        Date = myDialog.findViewById(R.id.date);
        Date1 = myDialog.findViewById(R.id.date1);
        Btn = myDialog.findViewById(R.id.btn);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        HomeMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        Date1.setText(date);
                        String tanggalakhir = Date1.getText().toString();
                        rangetanggal.setText(tanggalakhir);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        HomeMainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"-"+month+"-"+year;
                        Date.setText(date);
                        tanggalmulai = Date.getText().toString();
                        tanggal.setText(tanggalmulai);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeData();
                myDialog.dismiss();
            }
        });
        myDialog.show();
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
                Toast.makeText(HomeMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(HomeMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(HomeMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(HomeMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(HomeMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(HomeMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(HomeMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(HomeMainActivity.this, "References copied successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika referensi sumber kosong, tampilkan pesan error
                    Toast.makeText(HomeMainActivity.this, "Source reference is empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Jika terjadi pembatalan, tampilkan pesan error
                Toast.makeText(HomeMainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fetchDatafromDB(); // mengambil data dari db
    }



    public void fetchDatafromDB(){
        //1
        dataList1 = new ArrayList<>();
        adapter1 = new MyAdapter1(HomeMainActivity.this,dataList1);
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
        adapter2 = new MyAdapter2(HomeMainActivity.this,dataList2);
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
        adapter3 = new MyAdapter3(HomeMainActivity.this,dataList3);
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