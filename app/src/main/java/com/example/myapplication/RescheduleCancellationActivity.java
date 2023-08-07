package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class RescheduleCancellationActivity extends AppCompatActivity {

    RadioButton reschedulebtn, cancellationbtn;
    Button btnBack;
    RadioGroup radioGroup;
    LinearLayout btnDeck, btn_pilihtanggal;
    EditText editTextAlasan;
    TextView txtDeck, text_tanggal, txtAlasan, txtReschedule;
    ConstraintLayout layoutExtraCharge;

    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;
    String tglawal, tglakhir, deck, harga, lokasi, jumalh, userName, userRes, userResTangkap;
    String tanggalawalNew, tanggalakhirNew, deckNew, lokasiNew, hargaNew, jumlahNew;
    TextView deck_sebelumnya, harga_pilihandeck, rangetanggal, jumlah_orang, jumlah_reschedule;

    TextView close_btn, Date, Date1, header_dp, deck_reschedule, harga_reschedule;
    Dialog myDialog;
    Button Btn;
    DatabaseReference databaseReference, childRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule_cancellation);


        reschedulebtn = findViewById(R.id.rescheduleButton);
        cancellationbtn = findViewById(R.id.cancellationButton);
        radioGroup = findViewById(R.id.buttonGroup);
        btnDeck = findViewById(R.id.btn_pilihdek);
        txtDeck = findViewById(R.id.text_deck);
        txtAlasan = findViewById(R.id.text_alasan);
        btnBack = findViewById(R.id.btn_kembali);
        editTextAlasan = findViewById(R.id.edittext_alasan);
        txtReschedule = findViewById(R.id.text_tanggalpindah);
        layoutExtraCharge = findViewById(R.id.layout_biayatambahan);
        deck_sebelumnya = findViewById(R.id.deck_sebelumnya);
        harga_pilihandeck = findViewById(R.id.harga_pilihandeck);
        rangetanggal = findViewById(R.id.rangetanggal);
        jumlah_orang = findViewById(R.id.jumlah_orang);
        btn_pilihtanggal = findViewById(R.id.btn_pilihtanggal);
        header_dp = findViewById(R.id.header_dp);
        text_tanggal = findViewById(R.id.text_tanggal);
        deck_reschedule = findViewById(R.id.deck_reschedule);
        harga_reschedule = findViewById(R.id.harga_reschedule);
        jumlah_reschedule = findViewById(R.id.jumlah_reschedule);

        getData();

        editTextAlasan.setText("");

        ColorStateList radioButtonColors = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_checked}, // state_checked
                        new int[]{-android.R.attr.state_checked} // default state
                },
                new int[]{
                        Color.BLACK, // warna saat checked true
                        Color.GRAY    // warna saat checked false
                }
        );


        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton != null) {
                    if (radioButton.getId() == R.id.rescheduleButton) {
                        animateViewVisibility(View.VISIBLE, fadeInAnimation,
                                btnDeck, btn_pilihtanggal, text_tanggal, txtDeck, txtReschedule, layoutExtraCharge);
                        reschedulebtn.setTextColor(radioButtonColors);
                        cancellationbtn.setTextColor(radioButtonColors);
                        editTextAlasan.setText("");
                        txtAlasan.setText("Alasan Reschedule");
                    } else if (radioButton.getId() == R.id.cancellationButton) {
                        animateViewVisibility(View.GONE, fadeOutAnimation,
                                btnDeck, btn_pilihtanggal, text_tanggal, txtDeck, txtReschedule, layoutExtraCharge);
                        cancellationbtn.setTextColor(radioButtonColors);
                        reschedulebtn.setTextColor(radioButtonColors);
                        editTextAlasan.setText("");
                        txtAlasan.setText("Alasan Membatalkan");
                    }
                }
            }
        });

        layoutExtraCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RescheduleCancellationActivity.this, DetailRescheduleCharge.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RescheduleCancellationActivity.this, CancelationActivity.class);
                startActivity(intent);
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

        myDialog = new Dialog(this, R.style.dialog);

        btn_pilihtanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_DialogRescheadule();
            }
        });
    }

    public void getData() {
        Intent intent = getIntent();

        //dataAwal (sblm Rescheadule)
        tglawal = intent.getStringExtra("tanggalawalcancel");
        tglakhir = intent.getStringExtra("tanggalakhircancel");
        deck = intent.getStringExtra("deck");
        harga = intent.getStringExtra("harga");
        lokasi = intent.getStringExtra("lokasi");
        jumalh = intent.getStringExtra("jumlah");
        userName = intent.getStringExtra("name");

        userRes = "users/" + userName + "/Rescheadule/RescheaduleData" + lokasi + " " + deck;
        userResTangkap = intent.getStringExtra("userRes");

        if (userResTangkap != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child(userResTangkap);
            //dataRescheadule
            tanggalawalNew = intent.getStringExtra("tanggalawal");
            tanggalakhirNew = intent.getStringExtra("tanggalakhir");
            deckNew = intent.getStringExtra("resDeck");
            lokasiNew = intent.getStringExtra("resLokasi");
            hargaNew = intent.getStringExtra("resHarga");
            jumlahNew = intent.getStringExtra("resJumlah");
            text_tanggal.setText("Date = " + tanggalawalNew + " s/d " + tanggalakhirNew);
            deck_reschedule.setText(deckNew + " " + lokasiNew);
            harga_reschedule.setText(hargaNew);
            jumlah_reschedule.setText(jumlahNew + " Orang");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Ambil nilai dari tiap field di Firebase dan tampilkan ke TextView
                        String dataLokasi = dataSnapshot.child("dataLokasi").getValue(String.class);
                        String dataDeck = dataSnapshot.child("dataDeck").getValue(String.class);
                        String dataHarga = dataSnapshot.child("dataHarga").getValue(String.class);
                        String dataTanggal = dataSnapshot.child("dataTanggal").getValue(String.class);
                        String dataTanggalakhir = dataSnapshot.child("dataTanggalakhir").getValue(String.class);
                        String dataJumlah = dataSnapshot.child("dataJml").getValue(String.class);

                        // Set nilai ke TextView
                        deck_sebelumnya.setText(dataDeck + " " + dataLokasi);
                        harga_pilihandeck.setText(dataHarga);
                        rangetanggal.setText(dataTanggal + "\n s/d \n" + dataTanggalakhir);
                        jumlah_orang.setText(dataJumlah);

                        deck = dataDeck;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle jika ada error
                }
            });
        } else {
            deck_sebelumnya.setText(deck + " " + lokasi);
            harga_pilihandeck.setText(harga);
            rangetanggal.setText(tglawal + "\n s/d \n" + tglakhir);
            jumlah_orang.setText(jumalh);
            //dataRescheadule
            text_tanggal.setText("Date = DD/MM/YYYY");
            deck_reschedule.setText("belum memilih Deck");
            harga_reschedule.setText("Rp. 0");
            jumlah_reschedule.setText("0 Orang");
        }
    }

    public void show_DialogRescheadule() {


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
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RescheduleCancellationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        Date1.setText(date);
                        String tanggalakhir = Date1.getText().toString();
                        rangetanggal.setText(tanggalakhir);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RescheduleCancellationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "-" + month + "-" + year;
                        Date.setText(date);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String Datess = Date.getText().toString();
                String Dates = Date1.getText().toString();
                Intent intent = new Intent(RescheduleCancellationActivity.this, RescheaduleMainActivity.class);
                intent.putExtra("date", Datess);
                intent.putExtra("datelast", Dates);
                intent.putExtra("tglawal", tglawal);
                intent.putExtra("tglakhir", tglakhir);
                intent.putExtra("deck", deck);
                intent.putExtra("harga", harga);
                intent.putExtra("lokasi", lokasi);
                intent.putExtra("jumlah", jumalh);
                intent.putExtra("name", userName);
                ///////////keperluan db
                intent.putExtra("resData", userRes);
                startActivity(intent);
                myDialog.dismiss();

            }
        });
        myDialog.show();
    }

}