package com.example.musclematefront.activities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.databinding.ActivityChallengeBinding;
import com.example.musclematefront.databinding.ActivityHomeBinding;
import com.example.musclematefront.databinding.ActivityMainBinding;

public class ChallengeActivity extends AppCompatActivity {

    private ActivityChallengeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChallengeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
