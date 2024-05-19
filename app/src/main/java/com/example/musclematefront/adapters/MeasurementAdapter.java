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
import com.example.musclematefront.models.Measurement;
import com.example.musclematefront.models.Supplement;

import java.util.List;

public class MeasurementAdapter extends RecyclerView.Adapter<MeasurementAdapter.ViewHolder>{
    private List<Measurement> measurementList;
    private Context context;
    public MeasurementAdapter(List<Measurement> measurementList){
        this.measurementList = measurementList;


    }
    public void setSupplementList(List<Measurement> measurementList) {
        this.measurementList = measurementList;
    }
    @Override
    public MeasurementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suplements_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView suplementName = cardView.findViewById(R.id.suplementName);
        TextView dosageTextView = cardView.findViewById(R.id.dosageTextView);
        suplementName.setText(measurementList.get(position).getName());
        dosageTextView.setText(measurementList.get(position).getSize()+" cm");

    }

    @Override
    public int getItemCount() {
        return measurementList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
