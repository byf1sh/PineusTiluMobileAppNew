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

public class AdminNotifAdapter extends RecyclerView.Adapter<MyViewHolderAdminNotification> {

    private Context context;
    private List<NotifClass> dataList;


    public AdminNotifAdapter(Context context, List<NotifClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderAdminNotification onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminnotif_recycler_item,parent,false);

        return new MyViewHolderAdminNotification(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAdminNotification holder, int position) {
        holder.mainNotif.setText(dataList.get(position).getDataMain());
        holder.childNotif.setText(dataList.get(position).getDataChild());
        holder.purchasedOnDetails.setText(dataList.get(position).getDataTanggal());
        holder.priceDetailsDetails.setText(dataList.get(position).getDataHarga());
        holder.locationDetails.setText(dataList.get(position).getDataLokasi());

        holder.Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.OnClickconstraint.getVisibility() == View.GONE){
                    holder.OnClickconstraint.setVisibility(View.VISIBLE);
                }else {
                    holder.OnClickconstraint.setVisibility(View.GONE);
                }
            }
        });
        holder.cancellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggalnotf = dataList.get(holder.getAdapterPosition()).getDataTanggal();
                Intent intent = new Intent(context, CancelationActivity.class);
                intent.putExtra("tanggal", tanggalnotf);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderAdminNotification extends RecyclerView.ViewHolder{
    ImageView notifImg;
    TextView mainNotif, childNotif, purchasedOnDetails, priceDetailsDetails, locationDetails;
    ConstraintLayout OnClickconstraint, Notification, cancellation;

    public MyViewHolderAdminNotification(@NonNull View itemView) {
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

    }
}