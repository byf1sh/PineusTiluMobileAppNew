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

public class NotifAdapter extends RecyclerView.Adapter<MyViewHolderNotification> {

    private Context context;
    private List<NotifClass> dataList;
    DatabaseReference databaseReference,databaseReference1, childRef,childRef1;


    public NotifAdapter(Context context, List<NotifClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderNotification onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notif_recycler_item,parent,false);

        return new MyViewHolderNotification(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNotification holder, int position) {
        TextView nama = ((NotifActivity) context).findViewById(R.id.namanana);
        String Nama = nama.getText().toString();
        holder.mainNotif.setText(dataList.get(position).getDataMain());
        holder.childNotif.setText(dataList.get(position).getDataChild());
        holder.purchasedOnDetails.setText(dataList.get(position).getDataTanggal());
        holder.priceDetailsDetails.setText(dataList.get(position).getDataHarga());
        holder.locationDetails.setText(dataList.get(position).getDataLokasi().trim());

        if ("Guest Loyality Program".equals(dataList.get(position).getDataMain())) {
            holder.Notification.setBackgroundResource(R.drawable.adminnotifrectangel);
            holder.notifImg.setImageResource(R.drawable.adminnotification);

        } else {
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
                    TextView name = ((NotifActivity) context).findViewById(R.id.namanana);
                    String Name = name.getText().toString();
                    String tanggalnotf = dataList.get(holder.getAdapterPosition()).getDataTanggal();
                    String tanggalakhir = dataList.get(holder.getAdapterPosition()).getDataTanggalakhir();
                    String Deck = dataList.get(holder.getAdapterPosition()).getDataDeck();
                    String Harga = dataList.get(holder.getAdapterPosition()).getDataHarga();
                    String Lokasi = dataList.get(holder.getAdapterPosition()).getDataLokasi();
                    String Jumlah = dataList.get(holder.getAdapterPosition()).getDataJml();
                    Intent intent = new Intent(context, CancelationActivity.class);
                    intent.putExtra("tanggal", tanggalnotf);
                    intent.putExtra("tanggalakhir", tanggalakhir);
                    intent.putExtra("deck", Deck);
                    intent.putExtra("harga", Harga);
                    intent.putExtra("lokasi", Lokasi);
                    intent.putExtra("jumlah", Jumlah);
                    intent.putExtra("name",Name);
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class MyViewHolderNotification extends RecyclerView.ViewHolder{
    ImageView notifImg, delete;
    TextView mainNotif, childNotif, purchasedOnDetails, priceDetailsDetails, locationDetails;
    ConstraintLayout OnClickconstraint, Notification, cancellation;

    public MyViewHolderNotification(@NonNull View itemView) {
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