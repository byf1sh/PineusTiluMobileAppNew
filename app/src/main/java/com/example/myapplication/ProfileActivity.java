package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profileUsername, profilePassword;
    TextView titleName, titleUsername;
    DatabaseReference databaseReference, childRef;
    ValueEventListener eventListener;
    ImageView btnsetting, imgprofileclone;
    ShapeableImageView profilepic;
    String usernameUser, imageURL;
    Uri uri;
    Animation top_anim, bottom_anim;
    ConstraintLayout profilePiclyly;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.contact);
        profileEmail = findViewById(R.id.profileEmail);
        profilepic = findViewById(R.id.profilePic);
        profileUsername = findViewById(R.id.profileName);
        profilePassword = findViewById(R.id.profilePassword);
        titleName = findViewById(R.id.titleName);
        titleUsername = findViewById(R.id.titleUsername);
        btnsetting = findViewById(R.id.btnsetting);
        profilePiclyly=findViewById(R.id.profilePiclyly);
        imgprofileclone=findViewById(R.id.imgprofileclone);

        top_anim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        showUserData();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri = data.getData();
                            profilepic.setImageURI(uri);
                            saveProfilePic();

                        } else {
                            Toast.makeText(ProfileActivity.this, "No Image Selected",Toast.LENGTH_SHORT).show();
                            imgprofileclone.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        profilePiclyly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
                imgprofileclone.setVisibility(View.GONE);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(ProfileActivity.this,HomeMainActivity.class);
                    String homeName = profileUsername.getText().toString().trim();
                    intent.putExtra("username", homeName);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottom_history:
                    Intent intent1 = new Intent(ProfileActivity.this,EntertainmentActivity.class);
                    String notifName2 = profileUsername.getText().toString().trim();
                    intent1.putExtra("username", notifName2);
                    startActivity(intent1);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottom_notif:
                    Intent intent2 = new Intent(ProfileActivity.this,NotifActivity.class);
                    String notifName = profileUsername.getText().toString().trim();
                    intent2.putExtra("username", notifName);
                    startActivity(intent2);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    return true;
            }
            return false;
        });

    }

    public void saveProfilePic(){

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Users profile pic").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadProfilePic();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadProfilePic(){

        String name = profileName.getText().toString();
        String email = profileEmail.getText().toString();
        String username = profileUsername.getText().toString();
        String password = profilePassword.getText().toString();

        HelperClass helperClass = new HelperClass(name, email, username, password,imageURL);

        FirebaseDatabase.getInstance().getReference("users").child(username).setValue(helperClass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                            showUserData();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showUserData() {
        Intent intent = getIntent();
        String usernameUser = intent.getStringExtra("username");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        childRef = databaseReference.child(usernameUser);

        eventListener = childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HelperClass helperClass = dataSnapshot.getValue(HelperClass.class);
                if (helperClass != null) {
                    titleName.setText(helperClass.getName());
                    titleUsername.setText(helperClass.getUsername());
                    profileEmail.setText(helperClass.getEmail());
                    profileUsername.setText(helperClass.getUsername());
                    profileName.setText(helperClass.getName());
                    profilePassword.setText(helperClass.getPassword());

                    if (helperClass.getImageURL() != null && !helperClass.getImageURL().isEmpty()) {
                        imgprofileclone.setVisibility(View.GONE);
                    }else {
                        imgprofileclone.setVisibility(View.VISIBLE);
                    }
                    Glide.with(getApplicationContext())
                            .load(helperClass.getImageURL())
                            .into(profilepic);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void passUserData() {
        Intent intent = new Intent(ProfileActivity.this, ProfileSettingsActivity.class);

        String Contactstg = titleName.getText().toString();
        String Usernamestg = titleUsername.getText().toString();
        String Emailstg = profileEmail.getText().toString();
        String Passwordstg = profilePassword.getText().toString();

        intent.putExtra("username", Usernamestg);
        intent.putExtra("contact", Contactstg);
        intent.putExtra("email", Emailstg);
        intent.putExtra("password", Passwordstg);

        startActivity(intent);
    }
}