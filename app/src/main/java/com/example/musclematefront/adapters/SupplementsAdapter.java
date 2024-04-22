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
import com.example.musclematefront.models.Supplement;

import java.util.List;

public class SupplementsAdapter extends RecyclerView.Adapter<SupplementsAdapter.ViewHolder>{
    private List<Supplement> supplementList;
    private Context context;
    public SupplementsAdapter(List<Supplement> supplementList){
        this.supplementList = supplementList;


    }
    public void setSupplementList(List<Supplement> supplementList) {
        this.supplementList = supplementList;
    }
    @Override
    public SupplementsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suplements_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplementsAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView suplementName = cardView.findViewById(R.id.suplementName);
        TextView dosageTextView = cardView.findViewById(R.id.dosageTextView);

        suplementName.setText(supplementList.get(position).getName());
        dosageTextView.setText(String.valueOf(supplementList.get(position).getCurrentDose().getDose())+"g");

    }

    @Override
    public int getItemCount() {
        return supplementList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
