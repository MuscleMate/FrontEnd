package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.NotificationParser.parseNotifications;
import static com.example.musclematefront.parsers.SingleExerciseParser.parseSingleExercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.adapters.ExerciseAdapter;
import com.example.musclematefront.adapters.ExercisesAddAdapter;
import com.example.musclematefront.adapters.NotificationsAdapter;
import com.example.musclematefront.databinding.ActivityAddWorkoutBinding;
import com.example.musclematefront.databinding.ActivityWorkoutPreviewBinding;
import com.example.musclematefront.models.Notification;
import com.example.musclematefront.models.SingleExercise;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddWorkoutActivity extends AppCompatActivity {
    ActivityAddWorkoutBinding binding;
    RecyclerView exerciseRecyclerView;
    RecyclerView exerciseAddRecyclerView;
    ExerciseAdapter exerciseAdapter;
    static ExercisesAddAdapter exerciseAddAdapter;
    List<SingleExercise> singleExerciseList = new ArrayList<>();
    static List<SingleExercise> addedExercises = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupNumberPickers();
        sendSearchRequest();
        setupAddExerciseRecyclerView();
        setupSearchRecyclerView();
        setupBottomNavigation();
        binding.exerciseSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSearchRequest();
            }
        });
    }
    private void setupSearchRecyclerView() {
        exerciseRecyclerView = (RecyclerView) binding.exerciseSearchRecyclerView;
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        exerciseAdapter = new ExerciseAdapter(singleExerciseList);
        exerciseRecyclerView.setAdapter(exerciseAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        exerciseAdapter.notifyDataSetChanged();
    }
    private void setupAddExerciseRecyclerView() {
        exerciseAddRecyclerView = (RecyclerView) binding.exerciseRecyclerView;
        exerciseAddRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        exerciseAddAdapter = new ExercisesAddAdapter(singleExerciseList);
        exerciseAddRecyclerView.setAdapter(exerciseAddAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        exerciseAddAdapter.notifyDataSetChanged();
    }
    public static void addExercise(SingleExercise exercise){
        addedExercises.add(exercise);
        exerciseAddAdapter.setNotificationsList(addedExercises);
        exerciseAddAdapter.notifyDataSetChanged();
    }

    public void sendSearchRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(AddWorkoutActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        singleExerciseList = parseSingleExercise(response);
                        exerciseAdapter.setNotificationsList(singleExerciseList);
                        exerciseAdapter.notifyDataSetChanged();
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(AddWorkoutActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(AddWorkoutActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(AddWorkoutActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        JSONObject jsonPayload = new JSONObject();
        try {
            jsonPayload.put("searchText", binding.exerciseSearchText.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "http://192.168.1.11:4000/exercises/search";


        requestHandler.executeWithThreadPool(url,"POST",jsonPayload.toString());
    }


    private void setupNumberPickers() {
        binding.numberPickerDay.setVisibility(View.GONE);
        binding.numberPickerMonth2.setVisibility(View.GONE);
        binding.numberPickerYear.setVisibility(View.GONE);
        binding.numberPickerTime1.setVisibility(View.GONE);
        binding.numberPickerTime2.setVisibility(View.GONE);
        binding.numberPickerTime3.setVisibility(View.GONE);
        binding.numberPickerTime4.setVisibility(View.GONE);
        binding.dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.numberPickerDay.getVisibility()==View.VISIBLE){
                    binding.numberPickerDay.setVisibility(View.GONE);
                    binding.numberPickerMonth2.setVisibility(View.GONE);
                    binding.numberPickerYear.setVisibility(View.GONE);
                }
                else{
                    binding.numberPickerDay.setVisibility(View.VISIBLE);
                    binding.numberPickerMonth2.setVisibility(View.VISIBLE);
                    binding.numberPickerYear.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.numberPickerTime1.getVisibility()==View.VISIBLE){
                    binding.numberPickerTime1.setVisibility(View.GONE);
                    binding.numberPickerTime2.setVisibility(View.GONE);
                    binding.numberPickerTime3.setVisibility(View.GONE);
                    binding.numberPickerTime4.setVisibility(View.GONE);
                }
                else{
                    binding.numberPickerTime1.setVisibility(View.VISIBLE);
                    binding.numberPickerTime2.setVisibility(View.VISIBLE);
                    binding.numberPickerTime3.setVisibility(View.VISIBLE);
                    binding.numberPickerTime4.setVisibility(View.VISIBLE);
                }
            }
        });

    }
    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_home);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(AddWorkoutActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_home) {
                Intent intent = new Intent(AddWorkoutActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if(item.getItemId()==R.id.action_profile){
                Intent intent = new Intent(AddWorkoutActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }else if(item.getItemId()==R.id.action_social){
                Intent intent = new Intent(AddWorkoutActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }else if(item.getItemId()==R.id.action_add){

            }
            return true;
        });
    }
}
