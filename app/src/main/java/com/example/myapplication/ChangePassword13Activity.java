package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.myapplication.utils.AndroidUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ChangePassword13Activity extends AppCompatActivity {
    private ImageView buttonback;
    private Button buttonnext;
    String phoneNumber;
    Long timeoutSeconds = 60L;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    PinView pinView;
    TextView resend;
    ProgressBar progressbar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_13);

        buttonback = findViewById(R.id.buttonback);
        buttonnext = findViewById(R.id.buttonnext);
        resend = findViewById(R.id.resend);
        pinView = findViewById(R.id.pin_view);
        mAuth = FirebaseAuth.getInstance();
        progressbar = findViewById(R.id.progressbar);

        phoneNumber = getIntent().getExtras().getString("phone");
        Toast.makeText(getApplicationContext(),phoneNumber, Toast.LENGTH_LONG).show();
        sendOtp(phoneNumber, false);

        buttonnext.setOnClickListener(view -> {
            String enteredOtp = pinView.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, enteredOtp);
            signin(credential);


        });

//        resend.setOnClickListener((v) -> {
//            sendOtp(phoneNumber, true);
//
//        });



        pinView.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 4) {
                    Toast.makeText(getApplicationContext(), "Working", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePassword12();
            }
        });

//        buttonnext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChangePassword14();
//            }
//        });

    }

    public void ChangePassword12() {
        Intent intent = new Intent(this, ChangePassword12Activity.class);
        startActivity(intent);
    }

//    public void ChangePassword14() {
//        Intent intent = new Intent(this, ChangePassword14Activity.class);
//        startActivity(intent);
//    }

    void sendOtp(String phoneNumber, boolean isResend) {
//        startResendTimer();
        setInProgress(true);
        PhoneAuthOptions.Builder builder =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(timeoutSeconds, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signin(phoneAuthCredential);
                                setInProgress(false);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                AndroidUtil.showToast(getApplicationContext(), "OTP Verification failed");

                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verificationCode = s;
                                resendingToken = forceResendingToken;
                                AndroidUtil.showToast(getApplicationContext(), "OTP sent successfully");
                                setInProgress(false);

                            }
                        });
        if (isResend) {
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        } else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }

    }

    void setInProgress(boolean inProgress) {
        if (inProgress) {
            progressbar.setVisibility(View.GONE);
            buttonnext.setVisibility(View.VISIBLE);

        }
    }

    void signin(PhoneAuthCredential phoneAuthCredential) {
        //login and go to next activity
        setInProgress(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(ChangePassword13Activity.this, ChangePassword14Activity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);

                }else AndroidUtil.showToast(getApplicationContext(), "OTP Verification Failed");

            }
        });
    }
//    void startResendTimer(){
//        resend.setEnabled(false);
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                timeoutSeconds--;
//                resend.setText("Resend OTP in "+timeoutSeconds+" seconds");
//                if (timeoutSeconds<=0){
//                    timeoutSeconds = 60L;
//                    timer.cancel();
//                    runOnUiThread(() -> {
//                        resend.setEnabled(true);
//
//                    });
//                }
//
//            }
//        }, 0, 1000);
//
//    }
}