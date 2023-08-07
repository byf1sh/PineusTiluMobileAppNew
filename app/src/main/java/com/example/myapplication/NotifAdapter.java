package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotifAdapter extends RecyclerView.Adapter<MyViewHolderNotification> {

    private Context context;
    private List<NotifClass> dataList;

    public NotifAdapter(Context context, List<NotifClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderNotification onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notif_recycler_item, parent, false);
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

        Animation fadeInAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_out);
        fadeInAnimation.setDuration(300);
        fadeOutAnimation.setDuration(300);
        holder.itemView.startAnimation(fadeInAnimation);

        if ("Guest Loyality Program".equals(dataList.get(position).getDataMain())) {
            holder.Notification.setBackgroundResource(R.drawable.adminnotifrectangel);
            holder.notifImg.setImageResource(R.drawable.adminnotification);
        } else {
            holder.Notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.OnClickconstraint.getVisibility() == View.GONE) {
                        animateViewVisibility(View.VISIBLE, fadeInAnimation, holder.OnClickconstraint);
                    } else {
                        animateViewVisibility(View.GONE, fadeOutAnimation, holder.OnClickconstraint);
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

    private void animateViewVisibility(int visibility, Animation animation, View... views) {
        for (View view : views) {
            if (view.getVisibility() == visibility) continue;
            if (visibility == View.VISIBLE) {
                view.startAnimation(animation);
                view.setVisibility(visibility);
            } else {
                Animation fadeOutAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.fade_out);
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                view.startAnimation(fadeOutAnimation);
            }
        }
    }
}

class MyViewHolderNotification extends RecyclerView.ViewHolder {
    ImageView notifImg, delete;
    TextView mainNotif, childNotif, purchasedOnDetails, priceDetailsDetails, locationDetails;
    ConstraintLayout OnClickconstraint, Notification, cancellation;

    public MyViewHolderNotification(@NonNull View itemView) {
        super(itemView);
        cancellation = itemView.findViewById(R.id.cancellation);
        Notification = itemView.findViewById(R.id.Notification);
        OnClickconstraint = itemView.findViewById(R.id.onClickConstraint);
        notifImg = itemView.findViewById(R.id.notifImg);
        mainNotif = itemView.findViewById(R.id.mainNotif);
        childNotif = itemView.findViewById(R.id.childNotif);
        purchasedOnDetails = itemView.findViewById(R.id.purchasedOnDetails);
        priceDetailsDetails = itemView.findViewById(R.id.priceDetailsDetails);
        locationDetails = itemView.findViewById(R.id.locationDetails);
        delete = itemView.findViewById(R.id.delete);
    }
}
