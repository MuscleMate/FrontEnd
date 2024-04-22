package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.WorkoutParser.parseWorkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
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
import com.example.musclematefront.adapters.WorkoutsAdapter;
import com.example.musclematefront.databinding.ActivityWorkoutPreviewBinding;
import com.example.musclematefront.models.Workout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPreviewActivity extends AppCompatActivity{
    ActivityWorkoutPreviewBinding binding;
    WorkoutsAdapter workoutsAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutPreviewBinding.inflate(getLayoutInflater());
        context = WorkoutPreviewActivity.this;
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        //sendRequest();
    }
    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Workouts");
        ImageView notificationIcon = findViewById(R.id.notificationIcon);
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
                Intent intent = new Intent(WorkoutPreviewActivity.this, NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_workouts) {
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


}
