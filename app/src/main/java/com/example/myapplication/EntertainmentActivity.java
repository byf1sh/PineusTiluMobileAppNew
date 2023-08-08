package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class EntertainmentActivity extends AppCompatActivity {

    RecyclerView card1,card2,card3,card4,card5,card6;
    RecyclerView.Adapter adaptercard;
    TextView namanana;
    List<EntertainmentDataClass> dataList1;
    MyAdapterEntertain adapterEntertain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        card1 = findViewById(R.id.entertain_card1);
        card2 = findViewById(R.id.entertain_card2);
        card3 = findViewById(R.id.entertain_card3);
        card4 = findViewById(R.id.entertain_card4);
        card5 = findViewById(R.id.entertain_card5);
        card6 = findViewById(R.id.entertain_card6);
        namanana = findViewById(R.id.namanana);

        initRecycleView1();
        initRecycleView2();
        initRecycleView3();
        initRecycleView4();
        initRecycleView5();
        initRecycleView6();
        getData();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_history);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    Intent intent = new Intent(EntertainmentActivity.this,HomeMainActivity.class);
                    String namantf = namanana.getText().toString();
                    intent.putExtra("username",namantf);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottom_history:
                    return true;
                case R.id.bottom_notif:
                    Intent intent2 = new Intent(EntertainmentActivity.this,NotifActivity.class);
                    String namantf2 = namanana.getText().toString();
                    intent2.putExtra("username",namantf2);
                    startActivity(intent2);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
                case R.id.bottom_profile:
                    Intent intent3 = new Intent(EntertainmentActivity.this,ProfileActivity.class);
                    String namantf1 = namanana.getText().toString();
                    intent3.putExtra("username",namantf1);
                    startActivity(intent3);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                    return true;
            }
            return false;
        });
    }

    private void initRecycleView1(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("4 Orang/1 Perahu","600.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FRafting.png?alt=media&token=4dd028d4-621e-4594-a456-7fd41a7d3c3e"));
        items.add(new EntertainmentDataClass("5 Orang/1 Perahu","750.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting4.jpeg?alt=media&token=45dec8f2-65a7-4613-97b8-c6302e0d4821"));

        card1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card1.setAdapter(adaptercard);
    }
    private void initRecycleView2(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("Single","175.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV%20(1).png?alt=media&token=2069f5cb-8f63-4afc-827a-55ba7a347f2d"));
        items.add(new EntertainmentDataClass("Tandem","225.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV2.webp?alt=media&token=836ed0d3-9b61-4a7f-8421-605ecf4d0dcc"));

        card2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card2.setAdapter(adaptercard);
    }
    private void initRecycleView3(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("1 Jeep","1.500.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FOffroad.jpeg?alt=media&token=e9ee3b5e-d40b-48ed-b213-84841e337704"));
        items.add(new EntertainmentDataClass("2 Jeep","3.000.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%201.jpg?alt=media&token=10baa5fc-5e99-4086-b36c-4bca54882a51"));

        card3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card3.setAdapter(adaptercard);
    }
    private void initRecycleView4(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("4 Orang","140.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflyngfox.jpeg?alt=media&token=10bd1282-7522-4a09-980f-8fc31d28fa86"));

        card4.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card4.setAdapter(adaptercard);
    }
    private void initRecycleView5(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("6 Orang","360.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball.jpeg?alt=media&token=a66b0c5b-6b40-4056-b44a-be05ee48867a"));

        card5.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card5.setAdapter(adaptercard);
    }
    private void initRecycleView6(){
        ArrayList<EntertainmentDataClass> items = new ArrayList<>();
        items.add(new EntertainmentDataClass("10 Orang","800.000","https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fteambuilding.jpeg?alt=media&token=541b02ed-be78-4292-82a2-a651aac388d5"));

        card6.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        adaptercard = new MyAdapterEntertain(items);
        card6.setAdapter(adaptercard);
    }
    public void getData(){
        Intent intent = getIntent();
        String usernameEntertain = intent.getStringExtra("username");

        namanana.setText(usernameEntertain);
    }
    public void showPopupMenuEntertain(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu); // Menu yang ingin ditampilkan di PopupMenu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Tambahkan logika untuk mengatasi setiap item yang dipilih dari menu dropdown
                switch (item.getItemId()) {
                    case R.id.settings:
                        // Aksi untuk menu item 1
                        return true;
                    case R.id.faq:
                        Intent intent1 = new Intent(EntertainmentActivity.this, FAQActivity.class);
                        startActivity(intent1);
                        // Aksi untuk menu item 2
                        return true;
                    case R.id.tutorial:
                        Intent intent2 = new Intent(EntertainmentActivity.this,BookingDateTutorialActivity.class);
                        startActivity(intent2);
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