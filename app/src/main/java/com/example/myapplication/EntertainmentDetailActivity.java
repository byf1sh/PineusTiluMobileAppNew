package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class EntertainmentDetailActivity extends AppCompatActivity {

    TextView title,price,desc,facility, username_12;
    Button kembali;
    ImageView mainpic_dbd, potoslide1, potoslide2, potoslide3, potoslide4, potoslide5;
    String usernameENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment_detail);

        kembali = findViewById(R.id.btn_kembali);
        title = findViewById(R.id.header_dbd);
        price = findViewById(R.id.harga);
        desc = findViewById(R.id.deskripsi);
        facility = findViewById(R.id.fasilitas);
        mainpic_dbd=findViewById(R.id.mainpic_dbd);
        username_12=findViewById(R.id.username_12);
        potoslide1 = findViewById(R.id.fotoslide1);
        potoslide2 = findViewById(R.id.fotoslide2);
        potoslide3 = findViewById(R.id.fotoslide3);
        potoslide4 = findViewById(R.id.fotoslide4);
        potoslide5 = findViewById(R.id.fotoslide5);

        showData();

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntertainmentDetailActivity.this, EntertainmentActivity.class);
                intent.putExtra("username",usernameENT);
                startActivity(intent);
            }
        });

        mainpic_dbd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntertainmentDetailActivity.this, FullScreenSliderImageActivity.class);
                intent.putExtra("username",usernameENT);
                startActivity(intent);
            }
        });

    }

    public void showData() {
        Intent intent = getIntent();
        String Titlesrc = intent.getStringExtra("Title");
        String Pricesrc = intent.getStringExtra("Price");
        String Descsrc = intent.getStringExtra("Desc");
        String Faciltysrc = intent.getStringExtra("Facility");
        usernameENT = intent.getStringExtra("username");

        if (Titlesrc.contains("Rafting 4 Orang/1 Perahu") || Titlesrc.contains("Rafting 5 Orang/1 Perahu")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FRafting.png?alt=media&token=4dd028d4-621e-4594-a456-7fd41a7d3c3e";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting2.jpeg?alt=media&token=add6c6b1-c717-4d68-8de7-acce192e76af";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting3.webp?alt=media&token=a0d1f345-ddf3-4666-b07a-49328d6aaedb";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting4.jpeg?alt=media&token=45dec8f2-65a7-4613-97b8-c6302e0d4821";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting5.jpg?alt=media&token=8fbab8b3-6b64-4140-8ea1-12f12fa45665";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Frafting6.jpg?alt=media&token=af723202-a1ea-4ee8-90b3-a0683abc2dd6";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        } else if (Titlesrc.contains("ATV Single") || Titlesrc.contains("ATV Tandem")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV%20(1).png?alt=media&token=2069f5cb-8f63-4afc-827a-55ba7a347f2d";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV2.webp?alt=media&token=836ed0d3-9b61-4a7f-8421-605ecf4d0dcc";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV3.jpg?alt=media&token=c2ec49a8-0cca-41c1-828b-974f945be672";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV4.jpg?alt=media&token=6840c9fe-2713-414b-96d5-ee55242d40c6";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV5.jpeg?alt=media&token=fe915b84-c380-4a0a-9144-8ab1e2781d95";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FATV6.jpg?alt=media&token=a52bca4e-bd90-4fe5-8afb-97bc2ac1c573";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        } else if (Titlesrc.contains("Offroad 1 Jeep")|| Titlesrc.contains("Offroad 2 Jeep")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FOffroad.jpeg?alt=media&token=e9ee3b5e-d40b-48ed-b213-84841e337704";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%201.jpg?alt=media&token=10baa5fc-5e99-4086-b36c-4bca54882a51";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%202.jpg?alt=media&token=bea8a8ef-068e-4bf1-96b7-13a63d5b6cd7";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%203.jpg?alt=media&token=978c7f5a-0f96-4be9-af51-006d20c7525b";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%204.jpg?alt=media&token=77fb0f1a-a2b4-4cd3-a820-ba32bfa8abf0";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Foffroad%205.jpg?alt=media&token=8dc5dd4f-1ac5-48f6-b500-8dc11561374a";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        } else if (Titlesrc.contains("Flying Fox")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflyngfox.jpeg?alt=media&token=10bd1282-7522-4a09-980f-8fc31d28fa86";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflying%20fox1.jpg?alt=media&token=48f244d5-7b36-4233-9a4d-419f5f9017fb";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflyingfox%202.jpg?alt=media&token=b76b7e62-7d66-41d2-abe4-8d295a2c8a12";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflying%20fox%203.webp?alt=media&token=9eaba682-b9a3-45ca-ad4b-ac5dc614d1fe";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflying%20fox%204.webp?alt=media&token=79357fe9-999a-429c-bd27-147005e10424";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fflying%20fox%205.jpeg?alt=media&token=bc2ed854-a6ea-411a-9815-ff76bbecca05";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        } else if (Titlesrc.contains("Paintball")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball.jpeg?alt=media&token=a66b0c5b-6b40-4056-b44a-be05ee48867a";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball%201.jpg?alt=media&token=125d1202-3d34-4a6a-be9f-8aac6a13bceb";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball%202.jpeg?alt=media&token=bf4e0bd5-ba3b-4b65-ba9c-6bc2fc66c294";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball%203.jpg?alt=media&token=4f89aea3-91ac-4a6a-b19b-24c1b5c2d373";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball%204.jpeg?alt=media&token=def91b88-e50e-4be2-8507-cb881a113e22";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fpaintball%205.jpg?alt=media&token=f18f82e1-5f6c-4404-af8f-735666b1adac";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        } else if (Titlesrc.contains("Team Building")) {
            String imageURL1 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2Fteambuilding.jpeg?alt=media&token=541b02ed-be78-4292-82a2-a651aac388d5";
            String imageURL2 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FTB1.jpeg?alt=media&token=d19d6e93-6651-42c9-8362-bd90c94b6ab2";
            String imageURL3 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FTB2.webp?alt=media&token=054070b0-cd98-4e6e-8411-f8b3275d0340";
            String imageURL4 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FTB3.jpeg?alt=media&token=ab40f809-ab09-4943-8500-9e754d104792";
            String imageURL5 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FTB4.jpg?alt=media&token=e891de11-8cd0-4396-864d-ffda357ec638";
            String imageURL6 = "https://firebasestorage.googleapis.com/v0/b/pineustiludb.appspot.com/o/Users%20profile%20pic%2FTB5.jpg?alt=media&token=24c1db56-b1ce-428b-b862-170403d9bd9b";

            Glide.with(this).load(imageURL1).into(mainpic_dbd);
            Glide.with(this).load(imageURL2).into(potoslide1);
            Glide.with(this).load(imageURL3).into(potoslide2);
            Glide.with(this).load(imageURL4).into(potoslide3);
            Glide.with(this).load(imageURL5).into(potoslide4);
            Glide.with(this).load(imageURL6).into(potoslide5);
        }

        facility.setText(Faciltysrc);
        desc.setText(Descsrc);
        title.setText(Titlesrc);
        price.setText(Pricesrc);
        username_12.setText(usernameENT);
    }

    public void showPopupMenuEntertainDet(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu); // Menu   yang ingin ditampilkan di PopupMenu
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Tambahkan logika untuk mengatasi setiap item yang dipilih dari menu dropdown
                switch (item.getItemId()) {
                    case R.id.settings:
                        // Aksi untuk menu item 1
                        return true;
                    case R.id.faq:
                        Intent intent1 = new Intent(EntertainmentDetailActivity.this, FAQActivity.class);
                        startActivity(intent1);
                        // Aksi untuk menu item 2
                        return true;
                    case R.id.tutorial:
                        Intent intent2 = new Intent(EntertainmentDetailActivity.this,BookingDateTutorialActivity.class);
                        startActivity(intent2);
                    // Tambahkan lebih banyak case sesuai dengan kebutuhan Anda
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}