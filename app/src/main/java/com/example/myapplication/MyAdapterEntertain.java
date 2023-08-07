package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterEntertain extends RecyclerView.Adapter<MyViewHolderEntertain> {

    private Context context;
    private List<EntertainmentDataClass> dataList;


    public MyAdapterEntertain(ArrayList<EntertainmentDataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderEntertain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_entertain,parent,false);

        return new MyViewHolderEntertain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderEntertain holder, int position) {
        holder.Title.setText(dataList.get(position).getDataTitle());
        holder.Avail.setText(dataList.get(position).getDataAvail());

        TextView textView = holder.itemView.findViewById(R.id.price_deck);

        String value = dataList.get(position).getDataAvail();

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.card.getContext(), EntertainmentDetailActivity.class);

                String dataTitle = dataList.get(holder.getAdapterPosition()).getDataTitle();
                String dataPrice = dataList.get(holder.getAdapterPosition()).getDataAvail();
                String dataDesc = holder.card.getResources().getString(R.string.deskripsi);
                String faciltyoffroad = holder.card.getResources().getString(R.string.fasilitas_offroad);
                String faciltyrafting = holder.card.getResources().getString(R.string.fasilitas_rafting);
                String faciltyatv = holder.card.getResources().getString(R.string.fasilitas_atv);
                String faciltyteambuilding = holder.card.getResources().getString(R.string.fasilitas_teambuilding);
                String faciltypaintball = holder.card.getResources().getString(R.string.fasilitas_paintball);
                String faciltyflyingfox = holder.card.getResources().getString(R.string.fasilitas_flyingfox);
                TextView name = ((EntertainmentActivity) holder.card.getContext()).findViewById(R.id.namanana);
                String Name = name.getText().toString();


                if (dataTitle.equals("4 Orang/1 Perahu") || dataTitle.equals("5 Orang/1 Perahu")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltyrafting);
                    intent.putExtra("username", Name);
                    dataTitle = "Rafting "+dataTitle;
                }
                if (dataTitle.equals("Single") || dataTitle.equals("Tandem")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltyatv);
                    intent.putExtra("username", Name);
                    dataTitle = "ATV "+dataTitle;
                }
                if (dataTitle.equals("1 Jeep") || dataTitle.equals("2 Jeep")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltyoffroad);
                    intent.putExtra("username", Name);
                    dataTitle = "Offroad "+dataTitle;
                }
                if (dataTitle.equals("4 Orang")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltyflyingfox);
                    intent.putExtra("username", Name);
                    dataTitle = "Flying Fox "+dataTitle;
                }
                if (dataTitle.equals("6 Orang")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltypaintball);
                    intent.putExtra("username", Name);
                    dataTitle = "Paintball "+dataTitle;
                }
                if (dataTitle.equals("10 Orang")){
                    intent.putExtra("Desc",dataDesc);
                    intent.putExtra("Facility",faciltyteambuilding);
                    intent.putExtra("username", Name);
                    dataTitle = "Team Building "+dataTitle;
                }

                intent.putExtra("Title",dataTitle);
                intent.putExtra("Price",dataPrice);
                intent.putExtra("username", Name);

                holder.card.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderEntertain extends RecyclerView.ViewHolder{

    ImageView picImg;
    ImageView colordeck;
    TextView Title, Avail;
    ImageButton card;

    public MyViewHolderEntertain(@NonNull View itemView) {
        super(itemView);

        card = itemView.findViewById(R.id.availabledeck);
        picImg = itemView.findViewById(R.id.pic_en);
        colordeck = itemView.findViewById(R.id.deck_color);
        Title = itemView.findViewById(R.id.title_deck);
        Avail = itemView.findViewById(R.id.price_deck);

    }
}