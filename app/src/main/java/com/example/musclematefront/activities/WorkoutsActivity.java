package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.ActivityWorkoutsBinding;

public class WorkoutsActivity extends AppCompatActivity {
    ActivityWorkoutsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
    }

    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Workouts");
        ImageView notificationIcon = findViewById(R.id.notification_icon);
        TextView notificationBadge = findViewById(R.id.notification_badge);
        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutsActivity.this, NotificationsActivity.class);
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
            if (item.getItemId() == R.id.action_notifications) {
                Intent intent = new Intent(WorkoutsActivity.this, NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_workouts) {
            }
            // Handle other actions
            else if (item.getItemId() == R.id.action_profile) {
                Intent intent = new Intent(WorkoutsActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_social) {
                Intent intent = new Intent(WorkoutsActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        });
    }
}
