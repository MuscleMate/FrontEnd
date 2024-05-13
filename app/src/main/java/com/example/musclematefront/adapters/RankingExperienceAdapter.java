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

public class RankingExperienceAdapter extends RecyclerView.Adapter<RankingExperienceAdapter.ViewHolder>{
    private List<Friend> friendsList;
    private Context context;
    public RankingExperienceAdapter(List<Friend> friends){
        this.friendsList = friends;
    }
    public void setFriendsRequestList(List<Friend> friendList) {
        this.friendsList = friendList;
    }
    @Override
    public RankingExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingExperienceAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userNameTextView = cardView.findViewById(R.id.userNameTextView);
        TextView levelTextView = cardView.findViewById(R.id.levelTextView);
        TextView rankingValueText = cardView.findViewById(R.id.rankingValueText);
        userNameTextView.setText(friendsList.get(position).getFirstName());
        levelTextView.setText(String.valueOf(friendsList.get(position).getRp().getLevel()));
        rankingValueText.setText(String.valueOf(friendsList.get(position).getRp().getTotalPoints()));

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
