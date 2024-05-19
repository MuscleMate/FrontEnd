package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.databinding.ActivityTellMoreBinding;
import com.shawnlin.numberpicker.NumberPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class TellMoreActivity extends AppCompatActivity {
    ActivityTellMoreBinding binding;
    Integer day=1;
    String month="Jan";
    Integer year=1970;
    Intent intent;
    String dOT="";
    String genderString="";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTellMoreBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        intent = getIntent();
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
                ServerRequestHandler requestHandler = new ServerRequestHandler(TellMoreActivity.this,new ServerRequestHandler.OnServerResponseListener() {
                    @Override
                    public void onResponse(Pair<Integer, JSONObject> responsePair) {
                        int statusCode = responsePair.first;
                        JSONObject response = responsePair.second;
                        try{
                            String status = response.optString("status");

                            if (status.equals("OK")) {
                                // Start TellMoreActivity
                                Intent intent = new Intent(TellMoreActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // Handle other cases if needed
                                // For example, show an error message
                                Toast.makeText(TellMoreActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(TellMoreActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
                String url = "http://192.168.1.9:4000/auth/register/";

                // JSON payload
                JSONObject jsonPayload = new JSONObject();
                try {
                    jsonPayload.put("email", intent.getStringExtra("email"));
                    jsonPayload.put("password",intent.getStringExtra("password"));
                    jsonPayload.put("firstName", binding.editfirstName.getText().toString());
                    jsonPayload.put("lastName", binding.editLastName.getText().toString());
                    jsonPayload.put("gender", binding.editGender.getText().toString());
                    jsonPayload.put("dateOfBirth", dOT);
                    jsonPayload.put("height",binding.editHeight.getText().toString());
                    jsonPayload.put("weight", binding.editWeight.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Convert JSON payload to string
                String jsonString = jsonPayload.toString();
                requestHandler.executeWithThreadPool(url,"POST",jsonString);
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
        String[] genders = new String[]{"male","female","other"};
        binding.numberPickerGender.setDisplayedValues(genders);
        binding.numberPickerHeight.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editHeight.setText(String.valueOf(newVal)));
        binding.numberPickerWeight.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editWeight.setText(String.valueOf(newVal)));
        binding.numberPickerMonth.setOnValueChangedListener((picker, oldVal, newVal) -> {
            month = months[newVal-1];
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
            dOT=date;
        });
        binding.numberPickerDay.setOnValueChangedListener((picker, oldVal, newVal) -> {
            day = newVal;
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
            dOT=date;
        });
        binding.numberPickerYear.setOnValueChangedListener((picker, oldVal, newVal) -> {
            year = newVal;
            Locale locale = Locale.getDefault();
            String date = String.format(locale, "%d. %s. %d", day, month, year);
            binding.editDateOfBirth.setText(date);
            dOT=date;
        });
        binding.numberPickerGender.setOnValueChangedListener((picker, oldVal, newVal) -> binding.editGender.setText(genders[newVal-1]));
        genderString=binding.editGender.getText().toString();
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
