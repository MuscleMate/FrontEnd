package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.ActivitySocialBinding;
import com.example.musclematefront.utils.mPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class SocialActivity extends AppCompatActivity {
    ActivitySocialBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySocialBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();

       mPagerAdapter adapter = new mPagerAdapter(this);
       binding.viewPager.setAdapter(adapter);

       new TabLayoutMediator(binding.tabLayout, binding.viewPager,
               (tab,position) ->{
                   // Set the title for each tab
                   switch (position) {
                       case 0:
                           tab.setText("Activity");
                           break;
                       case 1:
                           tab.setText("Friends");
                           break;
                       case 2:
                           tab.setText("Rankings");
                           break;
                       case 3:
                           tab.setText("Challenges");
                           break;
                       case 4:
                           tab.setText("Tournaments");
                           break;
                   }
               }).attach();
    }
    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Social");
        RelativeLayout relativeLayout= findViewById(R.id.notification_layout);
        relativeLayout.setVisibility(View.GONE);
    }
    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_social);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(SocialActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_home) {
                Intent intent = new Intent(SocialActivity.this, NotificationsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if(item.getItemId()==R.id.action_profile){
                Intent intent = new Intent(SocialActivity.this, ProfileActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }else if(item.getItemId()==R.id.action_social){}
            return true;
        });
    }
}
