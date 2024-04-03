package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.ActivityNotificationsBinding;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
    }

    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Notifications");
        RelativeLayout relativeLayout= findViewById(R.id.notification_layout);
        relativeLayout.setVisibility(View.GONE);
    }

    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_notifications);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(NotificationsActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_notifications) {

            } else if(item.getItemId()==R.id.action_profile){
                Intent intent = new Intent(NotificationsActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }else if(item.getItemId()==R.id.action_social){
                Intent intent = new Intent(NotificationsActivity.this, SocialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        });
    }
}
