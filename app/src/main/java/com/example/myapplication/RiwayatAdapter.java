package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<MyViewHolderRiwayat> {

    private Context context;
    private List<NotifClass> dataList;
    DatabaseReference databaseReference,databaseReference1, childRef,childRef1;


    public RiwayatAdapter(Context context, List<NotifClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderRiwayat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notif_recycler_item,parent,false);

        return new MyViewHolderRiwayat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderRiwayat holder, int position) {
        TextView nama = ((NotifActivity) context).findViewById(R.id.namanana);
        String Nama = nama.getText().toString();
        holder.mainNotif.setText(dataList.get(position).getDataMain());
        holder.childNotif.setText(dataList.get(position).getDataChild());
        holder.purchasedOnDetails.setText(dataList.get(position).getDataTanggal());
        holder.priceDetailsDetails.setText(dataList.get(position).getDataHarga());
        holder.locationDetails.setText(dataList.get(position).getDataLokasi().trim());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderRiwayat extends RecyclerView.ViewHolder{
    ImageView notifImg, delete;
    TextView mainNotif, childNotif, purchasedOnDetails, priceDetailsDetails, locationDetails;
    ConstraintLayout OnClickconstraint, Notification, cancellation;

    public MyViewHolderRiwayat(@NonNull View itemView) {
        super(itemView);

        cancellation=itemView.findViewById(R.id.cancellation);
        Notification = itemView.findViewById(R.id.Notification);
        OnClickconstraint = itemView.findViewById(R.id.onClickConstraint);
        notifImg = itemView.findViewById(R.id.notifImg);
        mainNotif = itemView.findViewById(R.id.mainNotif);
        childNotif = itemView.findViewById(R.id.childNotif);
        purchasedOnDetails = itemView.findViewById(R.id.purchasedOnDetails);
        priceDetailsDetails = itemView.findViewById(R.id.priceDetailsDetails);
        locationDetails= itemView.findViewById(R.id.locationDetails);
        delete = itemView.findViewById(R.id.delete);

    }
}