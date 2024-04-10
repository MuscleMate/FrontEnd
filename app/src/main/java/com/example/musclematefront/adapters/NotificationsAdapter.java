package com.example.musclematefront.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.models.Notification;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>{
    private List<Notification> notificationsList;
    private Context context;
    public NotificationsAdapter(List<Notification> notifications){
        this.notificationsList = notifications;
    }
    public void setNotificationsList(List<Notification> notificationsList) {
        this.notificationsList = notificationsList;
    }
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userMessageTextView = cardView.findViewById(R.id.userMessageTextView);
        TextView timeTextView = cardView.findViewById(R.id.timeTextView);
        ImageView imageView = cardView.findViewById(R.id.imageViewNotification);
        userMessageTextView.setText(notificationsList.get(position).getMessage());
        timeTextView.setText(notificationsList.get(position).getDate().toString());
        if (notificationsList.get(position).isRead()) {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
