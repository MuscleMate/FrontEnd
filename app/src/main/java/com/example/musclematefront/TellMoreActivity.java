package com.example.musclematefront;

import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import com.example.musclematefront.databinding.ActivityTellMoreBinding;

public class TellMoreActivity extends AppCompatActivity {
    ActivityTellMoreBinding binding;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTellMoreBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.numberPickerEV.setDisplayedValues(new String[]{"asd","dsa","asd","das","5","6","7","8","9","10"});
    }
}
