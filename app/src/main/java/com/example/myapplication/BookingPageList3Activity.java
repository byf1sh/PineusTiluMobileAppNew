package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingPageList3Activity extends AppCompatActivity {

    RecyclerView recyclerViewList;
    List<DataClass> dataList1;
    TextView tanggal,rangetanggal,NameUser;
    DatabaseReference databaseReference1,childRef1;
    ValueEventListener eventListener;
    //myadapter
    MyAdapterList3 adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity
        setContentView(R.layout.activity_booking_page_list3);
        //recycler view
        recyclerViewList = findViewById(R.id.view_decklist3);
        NameUser = findViewById(R.id.NameUser);
        tanggal = findViewById(R.id.tanggal);
        rangetanggal = findViewById(R.id.rangetanggal);

        tanggal.setText("15-4-2023");
        //Activity
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(BookingPageList3Activity.this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager1);

        dataList1 = new ArrayList<>();
        adapter1 = new MyAdapterList3(BookingPageList3Activity.this,dataList1);
        recyclerViewList.setAdapter(adapter1);

        getData();


    }

    public void getData(){
        Intent intent = getIntent();

        String destName = intent.getStringExtra("username");
        String tanggalfirst = intent.getStringExtra("tanggal");
        String tanggalend = intent.getStringExtra("tanggalakhir");
        NameUser.setText(destName);

        if (tanggalfirst != null) {
            // Jalankan fungsi showData() karena tanggalfirst memiliki isinya
            tanggal.setText(tanggalfirst);
            rangetanggal.setText(tanggalend);
            fetchDatafromDB();
        }
    }
    public void fetchDatafromDB(){
        //1
        //child
        databaseReference1 = FirebaseDatabase.getInstance().getReference(tanggal.getText().toString());
        childRef1 = databaseReference1.child("Pineustilu3");

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
    }
}