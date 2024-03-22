package com.example.musclematefront;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.musclematefront.databinding.ActivityChooseGoalBinding;

public class ChooseGoalActivity extends AppCompatActivity {
    ActivityChooseGoalBinding binding;
    String selectedCardId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseGoalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupButtons();
        setupCards();
    }
    private void updateCardBackgrounds(CardView cardView) {
        binding.cardViewWeightLoss.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewMaintenance.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewMuscleGain.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        cardView.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.light_purple));
    }
    private void setupCards() {
        binding.cardViewWeightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "weightLoss";
                updateCardBackgrounds(binding.cardViewWeightLoss);
            }
        });
        binding.cardViewMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "maintenance";
                updateCardBackgrounds(binding.cardViewMaintenance);
            }
        });
        binding.cardViewMuscleGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "musclegain";
                updateCardBackgrounds(binding.cardViewMuscleGain);
            }
        });
    }


    private void setupButtons() {
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseGoalActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });
    }
}
