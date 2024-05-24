package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.ChallengeParser.parseChallenges;
import static com.example.musclematefront.parsers.HomeChallengeParser.parseHomeChallenges;
import static com.example.musclematefront.parsers.NotificationParser.parseNotifications;
import static com.example.musclematefront.parsers.RankingChallengesParser.parseRankingChallenges;
import static com.example.musclematefront.parsers.RpParser.parseRP;
import static com.example.musclematefront.parsers.SingleChallengeParser.parseSingleChallenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.adapters.HomeChallengeAdapter;
import com.example.musclematefront.adapters.NotificationsAdapter;
import com.example.musclematefront.databinding.ActivityHomeBinding;
import com.example.musclematefront.databinding.ActivityNotificationsBinding;
import com.example.musclematefront.models.Challenge;
import com.example.musclematefront.models.HomeChallenge;
import com.example.musclematefront.models.Notification;
import com.example.musclematefront.models.RP;
import com.example.musclematefront.models.RankingChallenges;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    RecyclerView homeChallengesRecyclerView;
    HomeChallengeAdapter homeChallengeAdapter;
    List<HomeChallenge> challengeList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        setupHomeChallengeRecycler();
        sendMainRequest();
    }
    private void sendMainRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(HomeActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        RP rp = parseRP(response);
                        binding.levelTextView.setText("Level "+rp.getLevel());
                        binding.progressBar.setProgress(rp.getLevelPoints());
                        binding.progressBar.setMax(rp.getLevelPointsMax());
                        binding.progressBar.setMin(0);
                        challengeList = parseHomeChallenges(response);
                        homeChallengeAdapter.setChallengesList(challengeList);
                        homeChallengeAdapter.notifyDataSetChanged();
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(HomeActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(HomeActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(HomeActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://192.168.1.4:4000/main";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void setupHomeChallengeRecycler(){

        homeChallengesRecyclerView = (RecyclerView) binding.homeChallengeRecyclerView;
        homeChallengesRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false));

        homeChallengeAdapter = new HomeChallengeAdapter(challengeList);
        homeChallengesRecyclerView.setAdapter(homeChallengeAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        homeChallengeAdapter.notifyDataSetChanged();
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

