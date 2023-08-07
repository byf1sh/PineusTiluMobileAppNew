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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter3 extends RecyclerView.Adapter<MyViewHolder3> {

    private Context context;
    private List<DataClass> dataList;
    String tanggalAwal,additionalText;
    DatabaseReference reference, childRef;

    public MyAdapter3(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_home_item,parent,false);
        return new MyViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {
        holder.Title.setText(dataList.get(position).getDataTitle());
        holder.Avail.setText(dataList.get(position).getDataAvail());

        /////Membuat highseason button muncul jika user adalah admin
        TextView name = ((HomeMainActivity) context).findViewById(R.id.NameUser);
        String Name = name.getText().toString();
        if (Name.equals("admin")){
            holder.highSeason.setVisibility(View.VISIBLE);
        }else {
            holder.highSeason.setVisibility(View.GONE);
        }

        /////Membuat warna teks, jika tersedia maka hijau dan seterusnya
        int hijau = ContextCompat.getColor(holder.itemView.getContext(), R.color.hijau);
        int merah = ContextCompat.getColor(holder.itemView.getContext(), R.color.merah);
        int kuning = ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow);
        TextView textView = holder.itemView.findViewById(R.id.avail_deck);
        Drawable deckcolor = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.deck_color);

        String value = dataList.get(position).getDataAvail();
        Drawable wrappedDrawable = DrawableCompat.wrap(deckcolor.mutate());

        if (value.equals("Tersedia")) {
            textView.setTextColor(hijau);
            holder.highSeasonTV.setText("Add as Highseason");
            holder.highSeasonTV.setTextColor(kuning);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataRef = database.getReference("Harga/Pineustilu3/normal");

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
            DatabaseReference dataRef = database.getReference("Harga/Pineustilu3/highseason");

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
        holder.highSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataAvail = dataList.get(holder.getAdapterPosition()).getDataAvail();
                if (dataAvail.equals("Tersedia")){
                    TextView tanggal = ((HomeMainActivity) context).findViewById(R.id.tanggal);
                    tanggalAwal = tanggal.getText().toString();
                    additionalText = "Pineustilu3";
                    String dataTitle = dataList.get(holder.getAdapterPosition()).getDataTitle();
                    String path = tanggalAwal+"/"+additionalText.trim();
                    reference = FirebaseDatabase.getInstance().getReference(path);
                    childRef = reference.child(dataTitle);
                    childRef.child("dataAvail").setValue("Highseason");
                } else if (dataAvail.equals("Highseason")) {
                    TextView tanggal = ((HomeMainActivity) context).findViewById(R.id.tanggal);
                    tanggalAwal = tanggal.getText().toString();
                    additionalText = "Pineustilu3";
                    String dataTitle = dataList.get(holder.getAdapterPosition()).getDataTitle();
                    String path = tanggalAwal+"/"+additionalText.trim();
                    reference = FirebaseDatabase.getInstance().getReference(path);
                    childRef = reference.child(dataTitle);
                    childRef.child("dataAvail").setValue("Tersedia");
                }
            }
        });

        holder.picImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataAvailConfirm = dataList.get(holder.getAdapterPosition()).getDataAvail();
                if (dataAvailConfirm.equals("Penuh")){
                    Toast.makeText(context, "Penuh", Toast.LENGTH_SHORT).show();
                }else {
                    TextView tanggal = ((HomeMainActivity) context).findViewById(R.id.tanggal);
                    String Tanggalawal = tanggal.getText().toString();
                    TextView name = ((HomeMainActivity) context).findViewById(R.id.NameUser);
                    TextView rangetanggal = ((HomeMainActivity)context).findViewById(R.id.rangetanggal);
                    String Name = name.getText().toString();
                    String tanggalAkhir = rangetanggal.getText().toString();
                    String dataTitle2 = dataList.get(holder.getAdapterPosition()).getDataTitle();
                    String additionalLokasi = "Pineustilu3";
                    String title = dataTitle2 + additionalLokasi;
                    String harga = textView.getText().toString();
                    String rescheadule = "noRescheadule";


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

class MyViewHolder3 extends RecyclerView.ViewHolder{

    ImageView picImg;
    ImageView colordeck;
    TextView Title, Avail, highSeasonTV;
    LinearLayout highSeason;

    public MyViewHolder3(@NonNull View itemView) {
        super(itemView);

        picImg = itemView.findViewById(R.id.pic_deck);
        colordeck = itemView.findViewById(R.id.deck_color);
        Title = itemView.findViewById(R.id.title_deck);
        Avail = itemView.findViewById(R.id.avail_deck);
        highSeason=itemView.findViewById(R.id.highSeason);
        highSeasonTV=itemView.findViewById(R.id.highSeasonTV);
    }
}