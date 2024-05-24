package com.example.musclematefront.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.activities.ChallengeActivity;
import com.example.musclematefront.models.Challenge;
import com.example.musclematefront.models.HomeChallenge;

import java.util.List;

public class HomeChallengeAdapter extends RecyclerView.Adapter<HomeChallengeAdapter.ViewHolder>{
    private List<HomeChallenge> challengesList;
    private Context context;
    public HomeChallengeAdapter(List<HomeChallenge> challengesList){
        this.challengesList = challengesList;


    }
    public void setChallengesList(List<HomeChallenge> challengesList) {
        this.challengesList = challengesList;
    }
    @Override
    public HomeChallengeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeChallengeAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView workoutTitleTextView = cardView.findViewById(R.id.workoutTitleTextView);
        TextView hoursLeftTextView = cardView.findViewById(R.id.hoursLeftTextView);
        TextView xpTextView = cardView.findViewById(R.id.xpTextView);
        hoursLeftTextView.setText(challengesList.get(position).getTitle());
        workoutTitleTextView.setText(challengesList.get(position).getTimeToEnd()+" days left");
        xpTextView.setText(challengesList.get(position).getExp()+" exp");
    }

    @Override
    public int getItemCount() {
        return challengesList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
