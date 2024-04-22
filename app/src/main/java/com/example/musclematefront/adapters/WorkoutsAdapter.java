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
import com.example.musclematefront.models.Workout;

import java.util.List;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.ViewHolder>{
    private List<Workout> workoutsList;
    private Context context;

    public WorkoutsAdapter(List<Workout> workoutList){
        this.workoutsList = workoutList;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutsList = workoutList;
    }

    @Override
    public WorkoutsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        TextView dateTextView = cardView.findViewById(R.id.dateTextView);
        TextView timeTextView = cardView.findViewById(R.id.timeTextView);
        TextView nameTextView = cardView.findViewById(R.id.nameTextView);
        TextView durationTextView = cardView.findViewById(R.id.durationTextView);
        TextView amountTextView = cardView.findViewById(R.id.amountTextView);
        ImageView favImageView = cardView.findViewById(R.id.favImageView);

        dateTextView.setText(dateFormat.format(workoutsList.get(position).getDate()));
        timeTextView.setText(timeFormat.format(workoutsList.get(position).getDate()));
        nameTextView.setText(workoutsList.get(position).getTitle());
        durationTextView.setText(String.valueOf(workoutsList.get(position).getDuration())+" mins");
        amountTextView.setText(String.format("%d exercises",workoutsList.get(position).getExercises().size()));
        if(workoutsList.get(position).isFavourite()){
            favImageView.setImageResource(R.drawable.fav);

        }else{favImageView.setImageResource(R.drawable.not_fav);}
    }

    @Override
    public int getItemCount() {return workoutsList.size();}
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
