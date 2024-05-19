package com.example.musclematefront.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;

import org.w3c.dom.Text;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private List<String> equipmentList;
    private Context context;
    public EquipmentAdapter(List<String> equipmentList){this.equipmentList = equipmentList;}
    public void setEquipmentList(List<String> equipmentList) {this.equipmentList = equipmentList;}
    @NonNull
    @Override
    public EquipmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipment_card, parent, false);
        return new EquipmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView equipment = cardView.findViewById(R.id.equipmentTextView);
        Log.d("aaa","EquipmentTextView: "+equipment.getText());
        equipment.setText(equipmentList.get(position));
    }

    @Override
    public int getItemCount() {return equipmentList.size();}



    protected static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;

        public ViewHolder(View v){
            super(v);
            cardView = (CardView) v;
        }
    }
}
