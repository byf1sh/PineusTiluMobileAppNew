package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapterR2 extends RecyclerView.Adapter<MyViewHolderR2> {

    private Context context;
    private List<DataClass> dataList;
    DatabaseReference reference, childRef;
    String tanggalAwal, additionalText;


    public MyAdapterR2(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderR2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_item,parent,false);

        return new MyViewHolderR2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderR2 holder, int position) {
        holder.Title.setText(dataList.get(position).getDataTitle());
        holder.Avail.setText(dataList.get(position).getDataAvail());
        Glide.with(context).load(dataList.get(position).getDataImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.picImg);

        /////Membuat highseason button muncul jika user adalah admin
        TextView name = ((RescheaduleMainActivity) context).findViewById(R.id.completed);
        String Name = name.getText().toString();
        holder.highSeason.setVisibility(View.GONE);

        /////Membuat warna teks, jika tersedia maka hijau dan seterusnya
        int hijau = ContextCompat.getColor(holder.itemView.getContext(), R.color.hijau);
        int kuning = ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow);
        int merah = ContextCompat.getColor(holder.itemView.getContext(), R.color.merah);

        TextView textView = holder.itemView.findViewById(R.id.avail_deck);
        Drawable deckcolor = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.deck_color);

        String value = dataList.get(position).getDataAvail();
        Drawable wrappedDrawable = DrawableCompat.wrap(deckcolor.mutate());

        if (value.equals("Tersedia")) {
            textView.setTextColor(hijau);
            holder.highSeasonTV.setText("Add as Highseason");
            holder.highSeasonTV.setTextColor(kuning);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataRef = database.getReference("Harga/Pineustilu2/normal");

            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Method ini akan dipanggil setiap kali data di bawah referensi berubah
                    // DataSnapshot berisi data dari database Firebase
                    // Gunakan dataSnapshot untuk mendapatkan nilai data

                    if (dataSnapshot.exists()) {
                        // Data ditemukan di bawah referensi
                        // Ambil nilai data
                        String value = dataSnapshot.getValue(String.class);
                        textView.setText(value);
                        // Lakukan sesuatu dengan nilai data
                    } else {
                        // Data tidak ditemukan di bawah referensi
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Method ini akan dipanggil jika ada error dalam mengambil data
                    // Tindakan yang bisa dilakukan ketika terjadi error
                }
            });

            DrawableCompat.setTint(wrappedDrawable, hijau);
        } else if (value.equals("Highseason")) {
            textView.setTextColor(kuning);
            holder.highSeasonTV.setText("Remove as Highseason");
            holder.highSeasonTV.setTextColor(merah);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataRef = database.getReference("Harga/Pineustilu2/highseason");

            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Method ini akan dipanggil setiap kali data di bawah referensi berubah
                    // DataSnapshot berisi data dari database Firebase
                    // Gunakan dataSnapshot untuk mendapatkan nilai data

                    if (dataSnapshot.exists()) {
                        // Data ditemukan di bawah referensi
                        // Ambil nilai data
                        String value = dataSnapshot.getValue(String.class);
                        textView.setText(value);
                        // Lakukan sesuatu dengan nilai data
                    } else {
                        // Data tidak ditemukan di bawah referensi
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Method ini akan dipanggil jika ada error dalam mengambil data
                    // Tindakan yang bisa dilakukan ketika terjadi error
                }
            });

        } else {
            textView.setTextColor(merah);
            DrawableCompat.setTint(wrappedDrawable, merah);
        }
        holder.colordeck.setImageDrawable(wrappedDrawable);


        //////jika buttn highseason ditekan oleh admin maka syntax dibawah mengubah dataAvail di database menjadi "Highseason"

        holder.picImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataAvailConfirm = dataList.get(holder.getAdapterPosition()).getDataAvail();
                if (dataAvailConfirm.equals("Penuh")){
                    Toast.makeText(context, "Penuh", Toast.LENGTH_SHORT).show();
                }else {
                    TextView tanggal = ((RescheaduleMainActivity) context).findViewById(R.id.tanggal);
                    TextView res = ((RescheaduleMainActivity) context).findViewById(R.id.userRes);
                    String userRes = res.getText().toString();
                    String Tanggalawal = tanggal.getText().toString();
                    TextView name = ((RescheaduleMainActivity) context).findViewById(R.id.completed);
                    TextView rangetanggal = ((RescheaduleMainActivity)context).findViewById(R.id.rangetanggal);
                    String Name = name.getText().toString();
                    String tanggalAkhir = rangetanggal.getText().toString();
                    String dataTitle2 = dataList.get(holder.getAdapterPosition()).getDataTitle();
                    String additionalLokasi = "Pineustilu2";
                    String title = dataTitle2 + additionalLokasi;
                    String harga = textView.getText().toString();
                    String rescheadule = "Rescheadule";

                    Intent intent = new Intent(context, DetailBookingDeckActivity.class);
                    String fasilities = context.getResources().getString(R.string.DetailFasilitasPt1Pt2);
                    String fasilitiesText = context.getResources().getString(R.string.PineusTilu1Fasilitas);
                    intent.putExtra("deck", dataTitle2);
                    intent.putExtra("lokasi", additionalLokasi);
                    intent.putExtra("title", title);
                    intent.putExtra("avail", dataList.get(holder.getAdapterPosition()).getDataAvail());
                    intent.putExtra("fasilities", fasilities);
                    intent.putExtra("fasilitiesText", fasilitiesText);
                    intent.putExtra("tanggalawal", Tanggalawal);
                    intent.putExtra("tanggalakhir", tanggalAkhir);
                    intent.putExtra("name", Name);
                    intent.putExtra("harga", harga);
                    intent.putExtra("parameter", rescheadule);
                    intent.putExtra("userRes",userRes);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderR2 extends RecyclerView.ViewHolder{

    ImageView picImg;
    ImageView colordeck;
    TextView Title, Avail,highSeasonTV;
    ConstraintLayout deck;
    LinearLayout highSeason;

    public MyViewHolderR2(@NonNull View itemView) {
        super(itemView);

        deck = itemView.findViewById(R.id.deck);
        picImg = itemView.findViewById(R.id.pic_deck);
        colordeck = itemView.findViewById(R.id.deck_color);
        Title = itemView.findViewById(R.id.title_deck);
        Avail = itemView.findViewById(R.id.avail_deck);
        highSeason = itemView.findViewById(R.id.highSeason);
        highSeasonTV = itemView.findViewById(R.id.highSeasonTV);

    }
}