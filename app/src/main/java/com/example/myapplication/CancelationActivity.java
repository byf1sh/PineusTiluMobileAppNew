package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;


public class CancelationActivity extends AppCompatActivity {


    TextView tglsekarang, sel;
    CardView stj;
    EditText etTanggal;

    ImageView centang1, centang2, centang3, centang4, centang5, centang6, centang7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelation);
        AndroidThreeTen.init(this);
        tglsekarang=findViewById(R.id.tglsekarang);
        centang1=findViewById(R.id.centang1);
        etTanggal=findViewById(R.id.etTanggal);
        stj=findViewById(R.id.stj);
        centang2=findViewById(R.id.centang2);
        centang3=findViewById(R.id.centang3);
        centang4=findViewById(R.id.centang4);
        centang5=findViewById(R.id.centang5);
        centang6=findViewById(R.id.centang6);
        sel=findViewById(R.id.sel);
        centang7=findViewById(R.id.centang7);
        String hari = "Before H-7 cut 25%";
        String hari2 = "H-7 to H-4 cut 50%";
        String hari3 = "H-3 to H-2 cut 75%";
        String hari4 = "H-1 to H cut 100%";
        String hari5 = "Before H-7 Free";
        String hari6 = "H-7 to H-3 150K";
        String hari7 = "H-2 to H-1 250K";


        stj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asd();

            }
        });



    }
    public void asd(){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        System.out.println("Tanggal saat ini tanpa nama bulan: " + currentDate);
        tglsekarang.setText(currentDate);
        String et = etTanggal.getText().toString();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(currentDate, formatter);
        LocalDate endDate = LocalDate.parse(et, formatter);

        // Hitung selisih antara startDate dan endDate
        Period period = Period.between(startDate, endDate);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        // Tampilkan selisih tanggal dalam format "tahun-bulan-hari"
        String selisihTanggal = years + " tahun, " + months + " bulan, " + days + " hari";
        System.out.println("Selisih tanggal: " + selisihTanggal);
    }
    public void terserah(){
        String selisih=sel.getText().toString();
        int number=Integer.parseInt(selisih);
        if (number > 3) {
            centang5.setVisibility(View.VISIBLE);
        } else {
            centang5.setVisibility(View.GONE);
        }
        //
        if (number >= 3 && number <= 7) {
            centang6.setVisibility(View.VISIBLE);
        } else {
            centang6.setVisibility(View.GONE);
        }
        //
        if (number == 1 || number == 2) {
            centang7.setVisibility(View.VISIBLE);
        } else {
            centang7.setVisibility(View.GONE);
        }
        //
        if (number > 3) {
            centang1.setVisibility(View.VISIBLE);
        } else {
            centang1.setVisibility(View.GONE);
        }
        //
        if (number >= 4 && number <=7) {
            centang2.setVisibility(View.VISIBLE);
        } else {
            centang2.setVisibility(View.GONE);
        }
        //
        if (number == 2 || number == 3 ) {
            centang3.setVisibility(View.VISIBLE);
        } else {
            centang3.setVisibility(View.GONE);
        }
        if (number == 0 || number == 1 ) {
            centang4.setVisibility(View.VISIBLE);
        } else {
            centang4.setVisibility(View.GONE);
        }
    }
}

