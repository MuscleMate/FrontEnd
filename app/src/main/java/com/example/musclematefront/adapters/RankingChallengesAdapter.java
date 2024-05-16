package com.example.musclematefront.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.models.RankingChallenges;

import java.util.List;

public class RankingChallengesAdapter extends RecyclerView.Adapter<RankingChallengesAdapter.ViewHolder>{
    private List<RankingChallenges> challengesList;
    private Context context;
    public RankingChallengesAdapter(List<RankingChallenges> challengesList){
        this.challengesList = challengesList;


    }
    public void setChallengesList(List<RankingChallenges> challengesList) {
        this.challengesList = challengesList;
    }
    @Override
    public RankingChallengesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ranking_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingChallengesAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView userNameTextView = cardView.findViewById(R.id.hoursLeftTextView);
        TextView levelTextView = cardView.findViewById(R.id.xpTextView);
        TextView rankingValueText = cardView.findViewById(R.id.participantsTextView);
        userNameTextView.setText(String.format("%s %s", challengesList.get(position).getFirstName(), challengesList.get(position).getLastName()));
        rankingValueText.setText(String.valueOf(challengesList.get(position).getChallengesCompleted()));
        levelTextView.setVisibility(View.GONE);
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
