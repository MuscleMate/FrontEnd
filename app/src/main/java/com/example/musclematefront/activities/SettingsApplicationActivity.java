package com.example.musclematefront.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.musclematefront.databinding.ActivitySettingsApplicationBinding;

public class SettingsApplicationActivity extends AppCompatActivity {
    ActivitySettingsApplicationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsApplicationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
