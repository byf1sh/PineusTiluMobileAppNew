package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class CancelationActivity extends AppCompatActivity {


    int daysDifference;
    CardView stj;
    String tanggalAwalNotf, tanggalAkhirNotf, Deck, Harga, Lokasi, Jumalh, Username;
    ImageView centang1, centang2, centang3, centang4, centang5, centang6, centang7;
    DatabaseReference childRef1,databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelation);
        centang1=findViewById(R.id.centang1);
        stj=findViewById(R.id.stj);
        centang2=findViewById(R.id.centang2);
        centang3=findViewById(R.id.centang3);
        centang4=findViewById(R.id.centang4);
        centang5=findViewById(R.id.centang5);
        centang6=findViewById(R.id.centang6);
        centang7=findViewById(R.id.centang7);
        getData();
        asd();
        terserah();


        String pathNtf = "users/" + Username + "/Rescheadule";
        databaseReference1 = FirebaseDatabase.getInstance().getReference(pathNtf);
        childRef1 = databaseReference1.child("RescheaduleData" + Lokasi+" "+ Deck);


        stj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataResceaduleDB();
                Intent intent = new Intent(CancelationActivity.this,RescheduleCancellationActivity.class);
                intent.putExtra("tanggalawalcancel", tanggalAwalNotf);
                intent.putExtra("tanggalakhircancel", tanggalAkhirNotf);
                intent.putExtra("deck", Deck);
                intent.putExtra("harga", Harga);
                intent.putExtra("lokasi", Lokasi);
                intent.putExtra("jumlah", Jumalh);
                intent.putExtra("name",Username);
                startActivity(intent);

            }
        });
    }


    public void asd(){
        String currentDateStr = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        // Tanggal yang ingin dihitung selisihnya (ganti dengan tanggal yang Anda inginkan)

        // Konversi string tanggal menjadi objek Date
        Date currentDate = null;
        Date endDate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            currentDate = format.parse(currentDateStr);
            endDate = format.parse(tanggalAwalNotf);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Hitung selisih antara tanggal
        if (currentDate != null && endDate != null) {
            long diff = endDate.getTime() - currentDate.getTime();

            // Konversi selisih dari miliseconds menjadi hari
            daysDifference = (int) (diff / (24 * 60 * 60 * 1000));

            // Tampilkan selisih tanggal dalam format "hari"
        }
    }
    public void terserah(){
        if (daysDifference > 3) {
            centang5.setVisibility(View.VISIBLE);
        } else {
            centang5.setVisibility(View.GONE);
        }
        //
        if (daysDifference >= 3 && daysDifference <= 7) {
            centang6.setVisibility(View.VISIBLE);
        } else {
            centang6.setVisibility(View.GONE);
        }
        //
        if (daysDifference == 1 || daysDifference == 2) {
            centang7.setVisibility(View.VISIBLE);
        } else {
            centang7.setVisibility(View.GONE);
        }
        //
        if (daysDifference > 3) {
            centang1.setVisibility(View.VISIBLE);
        } else {
            centang1.setVisibility(View.GONE);
        }
        //
        if (daysDifference >= 4 && daysDifference <=7) {
            centang2.setVisibility(View.VISIBLE);
        } else {
            centang2.setVisibility(View.GONE);
        }
        //
        if (daysDifference == 2 || daysDifference == 3 ) {
            centang3.setVisibility(View.VISIBLE);
        } else {
            centang3.setVisibility(View.GONE);
        }
        if (daysDifference == 0 || daysDifference == 1 ) {
            centang4.setVisibility(View.VISIBLE);
        } else {
            centang4.setVisibility(View.GONE);
        }
    }
    public void getData(){
        Intent intent = getIntent();
        tanggalAwalNotf = intent.getStringExtra("tanggal");
        tanggalAkhirNotf=intent.getStringExtra("tanggalakhir");
        Deck=intent.getStringExtra("deck");
        Harga=intent.getStringExtra("harga");
        Lokasi=intent.getStringExtra("lokasi");
        Jumalh=intent.getStringExtra("jumlah");
        Username=intent.getStringExtra("name");
    }
    public void DataResceaduleDB() {
        String destTitle="";
        String tanggalakhir="";
        String jml="";

        long timestamp = System.currentTimeMillis();
        //1
        NotifClass notifClass = new NotifClass(destTitle, tanggalakhir,jml ,tanggalAwalNotf , Harga,Lokasi , timestamp, Deck, tanggalAkhirNotf, Jumalh);

        childRef1.setValue(notifClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CancelationActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CancelationActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

