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

import com.example.musclematefront.activities.AddWorkoutActivity;
import com.example.musclematefront.models.SingleExercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder>{
    private List<SingleExercise> exerciseList;
    private Context context;
    public ExerciseAdapter(List<SingleExercise> exerciseList){
        this.exerciseList = exerciseList;
    }
    public void setNotificationsList(List<SingleExercise> exerciseList) {
        this.exerciseList = exerciseList;
    }
    @Override
    public ExerciseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_small_card, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView exerciseText = cardView.findViewById(R.id.exerciseText);
        exerciseText.setText(exerciseList.get(position).getTitle());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWorkoutActivity.addExercise(exerciseList.get(position));
            }
        });

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
