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

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder>{
    private List friendsList;
    private Context context;
    public FriendRequestAdapter(List<String> friends){
        this.friendsList = friends;


    }
    @Override
    public FriendRequestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_request_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userNameTextView = cardView.findViewById(R.id.userNameTextView);
        TextView levelTextView = cardView.findViewById(R.id.levelTextView);
        Button acceptButton = cardView.findViewById(R.id.acceptButton);
        Button ignoreButton = cardView.findViewById(R.id.ignoreButton);
        userNameTextView.setText(friendsList.get(position).toString());
        levelTextView.setText("Level 3");
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //accept friend request
            }
        });
        ignoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ignore friend request
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
