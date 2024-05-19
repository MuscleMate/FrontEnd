package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.ActivityHomeBinding;
import com.example.musclematefront.databinding.ActivityNotificationsBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
    }

    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Home");
        RelativeLayout relativeLayout= findViewById(R.id.notification_layout);
        relativeLayout.setVisibility(View.GONE);
    }

    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_home);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(HomeActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_home) {

            } else if(item.getItemId()==R.id.action_profile){
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }else if(item.getItemId()==R.id.action_social){
                Intent intent = new Intent(HomeActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        });
    }
}

