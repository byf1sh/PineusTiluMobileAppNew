package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText loginemail, loginPassword;
    Button loginButton;
    //    TextView signupRedirectText;
    private FirebaseAuth auth; //Nambahin auth 31 Juli

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance(); //Nambahin auth 31 Juli

        loginemail = findViewById(R.id.loginemail);
        loginPassword = findViewById(R.id.pass);
        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginemail.getText().toString();
                String password = loginPassword.getText().toString();

                if (!username.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    if (!password.isEmpty()) {
                        auth.signInWithEmailAndPassword(username, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomeMainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    } else {
                        loginPassword.setError("Password cannot be empty");
                    }
                } else if (username.isEmpty()) {
                    loginemail.setError("Username cannot be empty");
                } else {
                    loginemail.setError("Please enter valid email ");
                }
            }
        });
//        signupRedirectText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//            }
//        }); //nambahin redirect tapi yang atas masih gamau dipanggil


    }

}