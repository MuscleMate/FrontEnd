package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
    }
    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Profile");
        RelativeLayout relativeLayout= findViewById(R.id.notification_layout);
        relativeLayout.setVisibility(View.GONE);
    }
    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_profile);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(ProfileActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_notifications) {
                Intent intent = new Intent(ProfileActivity.this, NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if(item.getItemId()==R.id.action_profile){
            }else if(item.getItemId()==R.id.action_social){
                Intent intent = new Intent(ProfileActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);}
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        });
    }
}
