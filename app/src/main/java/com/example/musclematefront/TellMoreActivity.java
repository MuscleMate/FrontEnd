package com.example.musclematefront;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.musclematefront.databinding.ActivityTellMoreBinding;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.Locale;

public class TellMoreActivity extends AppCompatActivity {
    ActivityTellMoreBinding binding;
    Integer day=1;
    String month="Jan";
    Integer year=1970;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTellMoreBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        hideNumberPickers();
        setupNumberPickers();
        setupEditTexts();
        setupButton();
    }

    private void setupButton() {
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TellMoreActivity.this, ChooseGoalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupEditTexts() {
        binding.editHeight.setFocusable(false);
        binding.editGender.setFocusable(false);
        binding.editWeight.setFocusable(false);
        binding.editDateOfBirth.setFocusable(false);
        binding.editHeight.setOnClickListener(v -> showNumberPicker(binding.numberPickerHeight));
        binding.editWeight.setOnClickListener(v -> showNumberPicker(binding.numberPickerWeight));
        binding.editDateOfBirth.setOnClickListener(v -> showDateNumberPicker());
        binding.editGender.setOnClickListener(v -> showNumberPicker(binding.numberPickerGender));
    }

    private void setupNumberPickers() {
        String[] months = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        binding.numberPickerMonth.setDisplayedValues(months);
        String[] genders = new String[]{"Male","Female","Other"};
        binding.numberPickerGender.setDisplayedValues(genders);
        binding.numberPickerHeight.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editHeight.setText(String.valueOf(newVal)));
        binding.numberPickerWeight.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editWeight.setText(String.valueOf(newVal)));
        binding.numberPickerMonth.setOnValueChangedListener((picker, oldVal, newVal) -> {
            month = months[newVal-1];
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
        });
        binding.numberPickerDay.setOnValueChangedListener((picker, oldVal, newVal) -> {
            day = newVal;
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
        });
        binding.numberPickerYear.setOnValueChangedListener((picker, oldVal, newVal) -> {
            year = newVal;
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
        });
        binding.numberPickerGender.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editGender.setText(genders[newVal-1]));
    }
    private void hideNumberPickers(){
        binding.numberPickerHeight.setVisibility(View.GONE);
        binding.numberPickerWeight.setVisibility(View.GONE);
        binding.numberPickerDay.setVisibility(View.GONE);
        binding.numberPickerMonth.setVisibility(View.GONE);
        binding.numberPickerYear.setVisibility(View.GONE);
        binding.numberPickerGender.setVisibility(View.GONE);
    }
    private void showNumberPicker(NumberPicker numberPicker) {
        hideNumberPickers();
        numberPicker.setVisibility(View.VISIBLE);
    }
    private void showDateNumberPicker() {
        hideNumberPickers();
        binding.numberPickerDay.setVisibility(View.VISIBLE);
        binding.numberPickerMonth.setVisibility(View.VISIBLE);
        binding.numberPickerYear.setVisibility(View.VISIBLE);
    }
}
