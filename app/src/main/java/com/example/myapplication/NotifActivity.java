package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    ConstraintLayout nodata, nodataRiwayat, notifpurchased2, notification1;
    LinearLayout cl1,cl2;
    ImageView background;
    DatabaseReference databaseReference1,databaseReference, childRef1,childRef;
    String getdata;
    ScrollView scrollView,scrollView2;
    NotifAdapter adapter1;
    RiwayatAdapter adapter;
    List<NotifClass> dataListNotif, dayaListRiwayat;
    RecyclerView NotifRecyclerview,RiwayatRecyclerview;
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
        scrollView2=findViewById(R.id.scrollView2);
        scrollView=findViewById(R.id.scrollView);
        background = findViewById(R.id.background);
        namanana=findViewById(R.id.namanana);
        NotifRecyclerview=findViewById(R.id.NotifRecyclerview);
        nodata=findViewById(R.id.nodata);
        RiwayatRecyclerview=findViewById(R.id.RiwayatRecyclerview);
        nodataRiwayat=findViewById(R.id.nodataRiwayat);

        getData();
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(NotifActivity.this,LinearLayoutManager.VERTICAL,false);
        NotifRecyclerview.setLayoutManager(linearLayoutManager3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotifActivity.this,LinearLayoutManager.VERTICAL,false);
        RiwayatRecyclerview.setLayoutManager(linearLayoutManager);

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        fadeInAnimation.setDuration(140);
        fadeOutAnimation.setDuration(140);

        showDataRVUpcoming();
        showDataRVRiwayat();

        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upcoming.setTextColor(hijau);
                riwayat.setTextColor(abuabu);
                if (scrollView2.getVisibility() == View.GONE) {
                    animateViewVisibility(View.VISIBLE, fadeInAnimation,
                            scrollView2);
                    animateViewVisibility(View.GONE, fadeOutAnimation,
                            scrollView);
                    animateViewVisibility(View.VISIBLE, fadeInAnimation,
                            background);

                } else {
                    animateViewVisibility(View.VISIBLE, fadeInAnimation,
                            cl1);
                }
            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                riwayat.setTextColor(hijau);
                upcoming.setTextColor(abuabu);
                if (scrollView.getVisibility() == View.GONE) {
                    animateViewVisibility(View.GONE, fadeOutAnimation,
                            scrollView2);
                    animateViewVisibility(View.VISIBLE, fadeInAnimation,
                            scrollView);
                    animateViewVisibility(View.GONE, fadeOutAnimation,
                            background);
                } else {
                    animateViewVisibility(View.VISIBLE, fadeInAnimation,
                            cl2);
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
    public void dataNotPresentRiwayat(){
        nodataRiwayat.setVisibility(View.VISIBLE);
        RiwayatRecyclerview.setVisibility(View.GONE);
    }
    public void dataPresentRiwayat(){
        nodataRiwayat.setVisibility(View.GONE);
        RiwayatRecyclerview.setVisibility(View.VISIBLE);
    }
    public void showDataRVUpcoming(){
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

    private void animateViewVisibility(int visibility, Animation animation, View... views) {
        for (View view : views) {
            if (view.getVisibility() == visibility) continue;
            if (visibility == View.VISIBLE) {
                view.startAnimation(animation);
                view.setVisibility(visibility);
            } else {
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                view.startAnimation(fadeOutAnimation);
            }
        }
    }
    public void showDataRVRiwayat(){
        String pathRwyt = "users/"+getdata+"/Notif";
        dayaListRiwayat = new ArrayList<>();
        adapter = new RiwayatAdapter(NotifActivity.this,dayaListRiwayat);
        RiwayatRecyclerview.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference(pathRwyt);
        childRef = databaseReference.child("NotifRiwayat");

        eventListener = childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dayaListRiwayat.clear();
                if (snapshot.exists()) {
                    dataPresentRiwayat();
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        NotifClass notifClass = itemSnapshot.getValue(NotifClass.class);
                        dayaListRiwayat.add(notifClass);
                    }
                    adapter.notifyDataSetChanged();
                }else {
                    dataNotPresentRiwayat();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}