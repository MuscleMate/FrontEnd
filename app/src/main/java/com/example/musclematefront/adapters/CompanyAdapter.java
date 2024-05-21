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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private HashMap<String,String> companyMap;
    private List<String> valueList;
    private Context context;
    public CompanyAdapter(HashMap<String,String> companyMap){
        this.companyMap = companyMap;
    }
    public void setCompanyMap(HashMap<String,String> companyMap) {
        this.companyMap = companyMap;
        valueList = new ArrayList<>(companyMap.values());
    }
    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.companion_card, parent, false);
        return new CompanyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView companion = cardView.findViewById(R.id.companionTextView);
        companion.setText(valueList.get(position));
    }

    @Override
    public int getItemCount() {return companyMap.size();}



    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
