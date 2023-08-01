package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterList3 extends RecyclerView.Adapter<MyViewHolderList3> {

    private Context context;
    private List<DataClass> dataList;


    public MyAdapterList3(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderList3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_deck_list,parent,false);

        return new MyViewHolderList3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderList3 holder, int position) {
        holder.Title.setText(dataList.get(position).getDataTitle());
        holder.Avail.setText(dataList.get(position).getDataAvail());

        int hijau = ContextCompat.getColor(holder.itemView.getContext(), R.color.hijau);
        int merah = ContextCompat.getColor(holder.itemView.getContext(), R.color.merah);
        int kuning = ContextCompat.getColor(holder.itemView.getContext(), R.color.yellow);
        TextView textView = holder.itemView.findViewById(R.id.avail_deck);
        Drawable deckcolor = ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.deck_color);

        String value = dataList.get(position).getDataAvail();
        Drawable wrappedDrawable = DrawableCompat.wrap(deckcolor.mutate());

        if (value.equals("Tersedia")) {
            textView.setTextColor(hijau);
            DrawableCompat.setTint(wrappedDrawable, hijau);
            textView.setText("Rp. 950.000");
        }else if (value.equals("Highseason")) {
            textView.setTextColor(kuning);
            textView.setText("Rp. 750.000");
        } else {
            textView.setTextColor(merah);
            DrawableCompat.setTint(wrappedDrawable, merah);
        }
        holder.colordeck.setImageDrawable(wrappedDrawable);

        holder.picImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tanggal = ((BookingPageList3Activity) context).findViewById(R.id.tanggal);
                TextView name = ((BookingPageList3Activity) context).findViewById(R.id.NameUser);
                TextView rangetanggal = ((BookingPageList3Activity)context).findViewById(R.id.rangetanggal);
                String Name = name.getText().toString();
                String tanggalAwal = tanggal.getText().toString();
                String tanggalAkhir = rangetanggal.getText().toString();


                String dataTitle = dataList.get(holder.getAdapterPosition()).getDataTitle();
                //pineustilu
                String additionalText = " Pineustilu3";
                String title = dataTitle + additionalText;

                Intent intent = new Intent(context, DetailBookingDeckActivity.class);
                String fasilities = context.getResources().getString(R.string.DetailFasilitasPt1Pt2);
                String fasilitiesText = context.getResources().getString(R.string.PineusTilu1Fasilitas);
                intent.putExtra("deck", dataTitle);
                intent.putExtra("lokasi", additionalText);
                intent.putExtra("title", title);
                intent.putExtra("avail", dataList.get(holder.getAdapterPosition()).getDataAvail());
                intent.putExtra("fasilities", fasilities);
                intent.putExtra("fasilitiesText", fasilitiesText);
                intent.putExtra("tanggalawal", tanggalAwal);
                intent.putExtra("tanggalakhir", tanggalAkhir);
                intent.putExtra("name", Name);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderList3 extends RecyclerView.ViewHolder{

    ImageView picImg;
    ImageView colordeck;
    TextView Title, Avail;
    ConstraintLayout deck;

    public MyViewHolderList3(@NonNull View itemView) {
        super(itemView);

        deck = itemView.findViewById(R.id.deck);
        picImg = itemView.findViewById(R.id.pic_deck);
        colordeck = itemView.findViewById(R.id.deck_color);
        Title = itemView.findViewById(R.id.title_deck);
        Avail = itemView.findViewById(R.id.avail_deck);

    }
}