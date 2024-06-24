package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.WorkoutParser.parseWorkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.adapters.CompanyAdapter;
import com.example.musclematefront.adapters.EquipmentAdapter;
import com.example.musclematefront.adapters.ExerciseSetAdapter;
import com.example.musclematefront.databinding.ActivityWorkoutPreviewBinding;
import com.example.musclematefront.models.Exercise;
import com.example.musclematefront.models.Workout;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkoutPreviewActivity extends AppCompatActivity {
    ActivityWorkoutPreviewBinding binding;
    RecyclerView exerciseRecyclerView;
    RecyclerView equipmentRecyclerView;
    RecyclerView companyRecyclerView;
    ExerciseSetAdapter exerciseSetAdapter;
    EquipmentAdapter equipmentAdapter;
    CompanyAdapter companyAdapter;
    Context context;
    String workoutId;
    Workout workout = new Workout();
    private List<Exercise> exerciseList = new ArrayList<>();
    private List<String> equipmentList = new ArrayList<>();
    private HashMap<String, String> companyMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutPreviewBinding.inflate(getLayoutInflater());
        context = WorkoutPreviewActivity.this;
        workoutId = getIntent().getStringExtra("id");
        View view = binding.getRoot();
        sendRequest();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        setupExerciseRecycler();
        setupEquipmentRecycler();
        setupCompanyRecycler();
        binding.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteWorkoutRequest();
            }
        });
    }

    private void sendRequest() {
        ServerRequestHandler requestHandler = new ServerRequestHandler(context, new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try {
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: " + response.toString());
                    if (status.equals("OK") || statusCode == 200 || statusCode == 201) {
                        workout = parseWorkout(response);
                        Log.e("123",workout.getExercises().toString());
                        exerciseList = workout.getExercises();
                        setupWorkoutData();
                        exerciseSetAdapter.setExerciseList(exerciseList);
                        exerciseSetAdapter.notifyDataSetChanged();
                        equipmentList = workout.getEquipment();
                        if (equipmentList.size() != 0) {
                            equipmentAdapter.setEquipmentList(equipmentList);
                            equipmentAdapter.notifyDataSetChanged();
                        } else {
                            binding.equipmentRecyclerView.setVisibility(View.GONE);
                        }

                        companyMap = workout.getCompany();
                        if (companyMap.size() != 0) {
                            companyAdapter.setCompanyMap(companyMap);
                            companyAdapter.notifyDataSetChanged();
                        } else {
                            binding.companyRecyclerView.setVisibility(View.GONE);
                        }


                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(context, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = String.format("http://192.168.1.11:4000/workouts/%s", workoutId);


        requestHandler.executeWithThreadPool(url, "GET", "");
    }

    private void setupWorkoutData() {
        binding.workoutTitle.setText(workout.getTitle());
        binding.timeTextView.setText(workout.getTime());
        binding.durationTextView.setText(String.valueOf(workout.getDuration()) + " mins");
        binding.amountTextView.setText(String.format("%d exercises", exerciseList.size()));
        if (workout.isFavourite()) {
            binding.isFavImage.setImageResource(R.drawable.fav);
        } else {
            binding.isFavImage.setImageResource(R.drawable.not_fav);
        }

    }
    private void deleteWorkoutRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(context, new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try {
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: " + response.toString());
                    if (status.equals("OK") || statusCode == 200 || statusCode == 201) {
                        Toast.makeText(context, "Workout deleted", Toast.LENGTH_SHORT).show();
                        finish();


                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(context, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = String.format("http://192.168.1.11:4000/workouts/%s", workoutId);


        requestHandler.executeWithThreadPool(url, "DELETE", "");
    }

    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Workout");
        ImageView notificationIcon = findViewById(R.id.notification_icon);
        TextView notificationBadge = findViewById(R.id.notification_badge);
        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutPreviewActivity.this, NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        int notificationCount = 10;
        if (notificationCount > 0) {
            notificationBadge.setVisibility(View.VISIBLE);
            notificationBadge.setText(String.valueOf(notificationCount));
        } else {
            notificationBadge.setVisibility(View.GONE);
        }
    }

    private void setupBottomNavigation() {
        binding.bottomNavigation.setSelectedItemId(R.id.action_workouts);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_home) {
                Intent intent = new Intent(WorkoutPreviewActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_workouts) {
            }
            else if(item.getItemId()==R.id.action_add){
                Intent intent = new Intent(WorkoutPreviewActivity.this, AddWorkoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            // Handle other actions
            else if (item.getItemId() == R.id.action_profile) {
                Intent intent = new Intent(WorkoutPreviewActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_social) {
                Intent intent = new Intent(WorkoutPreviewActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        });
    }

    private void setupExerciseRecycler() {
        exerciseRecyclerView = (RecyclerView) binding.exerciseRecyclerView;
        exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        exerciseSetAdapter = new ExerciseSetAdapter(exerciseList);
        exerciseRecyclerView.setAdapter(exerciseSetAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        exerciseSetAdapter.notifyDataSetChanged();
    }

    private void setupEquipmentRecycler() {
        equipmentRecyclerView = (RecyclerView) binding.equipmentRecyclerView;
        equipmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        equipmentAdapter = new EquipmentAdapter(equipmentList);
        equipmentRecyclerView.setAdapter(equipmentAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        equipmentAdapter.notifyDataSetChanged();


    }

    private void setupCompanyRecycler() {
        companyRecyclerView = (RecyclerView) binding.companyRecyclerView;
        companyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        companyAdapter = new CompanyAdapter(companyMap);
        companyRecyclerView.setAdapter(companyAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        companyAdapter.notifyDataSetChanged();
    }

}
