package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RescheduleCancellationActivity extends AppCompatActivity {

    RadioButton reschedulebtn,cancellationbtn;
    Button btnBack;
    RadioGroup radioGroup;
    LinearLayout btnDeck,btnDate;
    EditText editTextAlasan;
    TextView txtDeck,txtDate,txtAlasan,txtReschedule;
    ConstraintLayout layoutExtraCharge;

    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule_cancellation);

        reschedulebtn = findViewById(R.id.rescheduleButton);
        cancellationbtn = findViewById(R.id.cancellationButton);
        radioGroup = findViewById(R.id.buttonGroup);
        btnDeck = findViewById(R.id.btn_pilihdek);
        btnDate = findViewById(R.id.btn_pilihtanggal);
        txtDeck = findViewById(R.id.text_deck);
        txtDate = findViewById(R.id.text_tanggal);
        txtAlasan = findViewById(R.id.text_alasan);
        btnBack = findViewById(R.id.btn_kembali);
        editTextAlasan = findViewById(R.id.edittext_alasan);
        txtReschedule = findViewById(R.id.text_tanggalpindah);
        layoutExtraCharge = findViewById(R.id.layout_biayatambahan);

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
                                btnDeck, btnDate, txtDate, txtDeck, txtReschedule, layoutExtraCharge);
                        reschedulebtn.setTextColor(radioButtonColors);
                        cancellationbtn.setTextColor(radioButtonColors);
                        editTextAlasan.setText("");
                        txtAlasan.setText("Alasan Reschedule");
                    } else if (radioButton.getId() == R.id.cancellationButton) {
                        animateViewVisibility(View.GONE, fadeOutAnimation,
                                btnDeck, btnDate, txtDate, txtDeck, txtReschedule, layoutExtraCharge);
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
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
                view.startAnimation(fadeOutAnimation);
            }
        }
    }
}