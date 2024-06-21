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

import java.util.List;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder>{
    private List<Challenge> challengesList;
    private Context context;
    public ChallengeAdapter(List<Challenge> challengesList){
        this.challengesList = challengesList;


    }
    public void setChallengesList(List<Challenge> challengesList) {
        this.challengesList = challengesList;
    }
    @Override
    public ChallengeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.challenge_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChallengeActivity.class);
                intent.putExtra("ID",challengesList.get(holder.getAdapterPosition()).getId());
                context.startActivity(intent);
            }
        });
        TextView hoursLeftTextView = cardView.findViewById(R.id.hoursLeftTextView);
        TextView levelTextView = cardView.findViewById(R.id.xpTextView);
        TextView participantsTextView = cardView.findViewById(R.id.participantsTextView);
        TextView workoutTitleTextView = cardView.findViewById(R.id.workoutTitleTextView);
        TextView byTextView = cardView.findViewById(R.id.byTextView);
        hoursLeftTextView.setText(String.valueOf(challengesList.get(position).getDuration())+ " hours left");
        levelTextView.setText(challengesList.get(position).getDifficulty());
        workoutTitleTextView.setText(challengesList.get(position).getTitle());
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
