package com.example.musclematefront.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.models.Friend;
import com.example.musclematefront.models.Notification;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder>{
    private List<Friend> friendsList;
    private Context context;
    public FriendsAdapter(List<Friend> friends){
        this.friendsList = friends;


    }
    public void setFriendsList(List<Friend> friendList) {
        this.friendsList = friendList;
    }
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userNameTextView = cardView.findViewById(R.id.userNameTextView);
        TextView levelTextView = cardView.findViewById(R.id.levelTextView);
        userNameTextView.setText(friendsList.get(position).getFirstName());
        levelTextView.setText("lvl "+String.valueOf(friendsList.get(position).getRp().getLevel()));
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
