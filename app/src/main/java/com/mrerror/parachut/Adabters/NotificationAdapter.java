package com.mrerror.parachut.Adabters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrerror.parachut.Models.DetailsOrder.OrderProduct;
import com.mrerror.parachut.Models.NotificationsModel.GetNotification;
import com.mrerror.parachut.R;
import com.mrerror.parachut.utils.Utils;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationVHolder> {

    ArrayList<GetNotification> getNotificationArrayList = new ArrayList<>();

    public void setAllNotifications(ArrayList<GetNotification> getNotifications) {
        this.getNotificationArrayList = getNotifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationVHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotificationVHolder holder, int position) {

        GetNotification getNotification = getNotificationArrayList.get(position);

        if(getNotification.getType()==1){
          holder.textViewType.setText("تم ارسال طلب");
          holder.imageViewItem.setVisibility(View.GONE);
        }else{
            holder.textViewType.setText("عرض جديد");
            holder.imageViewItem.setVisibility(View.VISIBLE);
            Glide.with(holder.itemView.getContext()).load(getNotification.getImage()).into(holder.imageViewItem);
        }
        holder.textViewDesc.setText(getNotification.getMsg()+"");
        holder.textViewTime.setText(Utils.timeAgo(getNotification.getTimeCreates() +""));

    }

    @Override
    public int getItemCount() {
        return getNotificationArrayList.size();
    }

    class NotificationVHolder extends RecyclerView.ViewHolder {

        TextView  textViewDesc,textViewType,textViewTime;
        ImageView imageViewItem;
        public NotificationVHolder(@NonNull View itemView) {
            super(itemView);

            textViewDesc=itemView.findViewById(R.id.txtDesc);
            textViewTime=itemView.findViewById(R.id.txtTime);
            textViewType=itemView.findViewById(R.id.txtType);
            imageViewItem=itemView.findViewById(R.id.imgnotification);


        }

    }
}
