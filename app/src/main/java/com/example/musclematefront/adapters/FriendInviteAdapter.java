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
import com.example.musclematefront.activitiesSocial.FriendsFragment;
import com.example.musclematefront.models.Friend;

import java.util.List;

public class FriendInviteAdapter extends RecyclerView.Adapter<FriendInviteAdapter.ViewHolder>{
    private List<Friend> friendsList;
    private Context context;
    public FriendInviteAdapter(List<Friend> friends){
        this.friendsList = friends;
    }
    public void setFriendsInviteList(List<Friend> friendList) {
        this.friendsList = friendList;
    }
    @Override
    public FriendInviteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendInviteAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userNameTextView = cardView.findViewById(R.id.hoursLeftTextView);
        Button acceptButton = cardView.findViewById(R.id.friendsButton);
        userNameTextView.setText(friendsList.get(position).getFirstName());
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accept friend request
                FriendsFragment.sendPostFriendsRequest(friendsList.get(position).getId(),context);
            }
        });
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
