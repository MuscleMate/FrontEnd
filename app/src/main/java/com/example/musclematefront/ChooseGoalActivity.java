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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityChooseGoalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupButtons();
        setupCards();
    }
    private void updateCardBackgrounds(CardView cardView) {
        binding.cardViewAbs.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewWeightLoss.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewFlexibility.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewGlutes.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewLegs.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        binding.cardViewUpperBody.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.white));
        cardView.setCardBackgroundColor(ContextCompat.getColor(ChooseGoalActivity.this, R.color.light_purple));
    }
    private void setupCards() {
        binding.cardViewAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "abs";
                updateCardBackgrounds(binding.cardViewAbs);

            }
        });
        binding.cardViewWeightLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "weightLoss";
                updateCardBackgrounds(binding.cardViewWeightLoss);
            }
        });
        binding.cardViewFlexibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "flexibility";
                updateCardBackgrounds(binding.cardViewFlexibility);
            }
        });
        binding.cardViewGlutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "glutes";
                updateCardBackgrounds(binding.cardViewGlutes);
            }
        });
        binding.cardViewLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "legs";
                updateCardBackgrounds(binding.cardViewLegs);
            }
        });
        binding.cardViewUpperBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCardId = "upperBody";
                updateCardBackgrounds(binding.cardViewUpperBody);
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
