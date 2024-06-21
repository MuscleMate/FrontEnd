package com.example.musclematefront.adapters;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.models.Exercise;


import org.w3c.dom.Text;

import java.util.List;

public class ExerciseSetAdapter extends RecyclerView.Adapter<ExerciseSetAdapter.ViewHolder> {
    private List<Exercise> exerciseList;
    private Context context;
    public ExerciseSetAdapter(List<Exercise> exerciseList){this.exerciseList = exerciseList;}
    public void setExerciseList(List<Exercise> exerciseList){this.exerciseList = exerciseList;}
    @NonNull
    @Override
    public ExerciseSetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card, parent, false);
        return new ExerciseSetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseSetAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView exerciseTitle = cardView.findViewById(R.id.exerciseTitle);
        TextView exerciseMusclePart = cardView.findViewById(R.id.exerciseMusclePart);
        TextView exerciseSetAmount = cardView.findViewById(R.id.exerciseSetAmount);
        TextView exerciseTime = cardView.findViewById(R.id.exerciseTime);

        exerciseTitle.setText(exerciseList.get(position).getTitle());
        exerciseMusclePart.setText(exerciseList.get(position).getType());
       // exerciseSetAmount.setText(String.valueOf(exerciseList.get(position).getSets()));
        //exerciseTime.setText(String.valueOf(exerciseList.get(position).getWeight()) +" kg, "+String.valueOf(exerciseList.get(position).getReps())+ " reps");

    }

    @Override
    public int getItemCount() {return exerciseList.size();}
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
