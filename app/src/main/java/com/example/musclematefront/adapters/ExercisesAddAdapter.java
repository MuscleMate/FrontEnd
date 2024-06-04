package com.example.musclematefront.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;

import com.example.musclematefront.models.SingleExercise;

import java.util.List;

public class ExercisesAddAdapter extends RecyclerView.Adapter<ExercisesAddAdapter.ViewHolder>{
    private List<SingleExercise> exerciseList;
    private Context context;
    public ExercisesAddAdapter(List<SingleExercise> exerciseList){
        this.exerciseList = exerciseList;
    }
    public void setNotificationsList(List<SingleExercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
    @Override
    public ExercisesAddAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_small_card, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ExercisesAddAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView exerciseText = cardView.findViewById(R.id.exerciseText);
        exerciseText.setText(exerciseList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
