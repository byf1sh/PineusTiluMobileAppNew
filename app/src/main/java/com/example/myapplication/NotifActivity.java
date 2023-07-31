package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotifActivity extends AppCompatActivity {

    TextView riwayat, upcoming, namanana;
    ConstraintLayout cl2, nodata, notifpurchased, notifpurchased2, notification1;
    LinearLayout cl1;
    ImageView background;
    DatabaseReference databaseReference1, childRef1;
    String getdata;
    NotifAdapter adapter1;
    List<NotifClass> dataListNotif;
    RecyclerView NotifRecyclerview;
    ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_notif);
        int hijau = ContextCompat.getColor(getApplicationContext(), R.color.green);
        int abuabu = ContextCompat.getColor(getApplicationContext(), R.color.abu3);

        upcoming = findViewById(R.id.upcoming);
        riwayat = findViewById(R.id.riwayat);
        cl1 = findViewById(R.id.cl1);
        cl2 = findViewById(R.id.cl2);
        background = findViewById(R.id.background);
        namanana=findViewById(R.id.namanana);
        NotifRecyclerview=findViewById(R.id.NotifRecyclerview);
        nodata=findViewById(R.id.nodata);

        getData();
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(NotifActivity.this,LinearLayoutManager.VERTICAL,false);
        NotifRecyclerview.setLayoutManager(linearLayoutManager3);

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upcoming.setTextColor(hijau);
                riwayat.setTextColor(abuabu);
                if (cl1.getVisibility() == View.GONE) {
                    cl1.setVisibility(View.VISIBLE);
                    cl2.setVisibility(View.GONE);
                    background.setVisibility(View.VISIBLE);

                } else {
                    cl1.setVisibility(View.VISIBLE);
                }
            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riwayat.setTextColor(hijau);
                upcoming.setTextColor(abuabu);
                if (cl2.getVisibility() == View.GONE) {
                    cl2.setVisibility(View.VISIBLE);
                    cl1.setVisibility(View.GONE);
                    background.setVisibility(View.GONE);
                } else {
                    cl2.setVisibility(View.VISIBLE);
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(NotifActivity.this,HomeMainActivity.class);
                    String namantf = namanana.getText().toString();
                    intent.putExtra("username",namantf);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.bottom_history:
                    Intent intent2 = new Intent(NotifActivity.this,EntertainmentActivity.class);
                    String namantf2 = namanana.getText().toString();
                    intent2.putExtra("username",namantf2);
                    startActivity(intent2);
                    finish();
                    return true;
                case R.id.bottom_notif:
                    return true;
                case R.id.bottom_profile:
                    Intent intent3 = new Intent(NotifActivity.this,ProfileActivity.class);
                    String namantf1 = namanana.getText().toString();
                    intent3.putExtra("username",namantf1);
                    startActivity(intent3);
                    finish();
                    return true;
            }
            return false;
        });
        String pathNtf = "users/"+getdata+"/Notif";
        dataListNotif = new ArrayList<>();
        adapter1 = new NotifAdapter(NotifActivity.this,dataListNotif);
        NotifRecyclerview.setAdapter(adapter1);

        databaseReference1 = FirebaseDatabase.getInstance().getReference(pathNtf);
        childRef1 = databaseReference1.child("NotifPurchased");

        eventListener = childRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataListNotif.clear();
                if (snapshot.exists()) {
                    dataPresent();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        NotifClass notifClass = itemSnapshot.getValue(NotifClass.class);
                        dataListNotif.add(notifClass);
                    }
                    adapter1.notifyDataSetChanged();
                }else {
                    dataNotPresent();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getData(){
        Intent intent = getIntent();

        getdata = intent.getStringExtra("username");
        namanana.setText(getdata);

    }
    public void dataNotPresent(){
        nodata.setVisibility(View.VISIBLE);
        NotifRecyclerview.setVisibility(View.GONE);
    }
    public void dataPresent(){
        nodata.setVisibility(View.GONE);
        NotifRecyclerview.setVisibility(View.VISIBLE);
    }
    public void showPopupMenuNotif(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu); // Menu yang ingin ditampilkan di PopupMenu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Tambahkan logika untuk mengatasi setiap item yang dipilih dari menu dropdown
                switch (item.getItemId()) {
                    case R.id.menu_item_1:
                        // Aksi untuk menu item 1
                        return true;
                    case R.id.menu_item_2:
                        // Aksi untuk menu item 2
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