package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.WorkoutsParser.parseWorkouts;

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
import com.example.musclematefront.databinding.ActivityWorkoutsBinding;
import com.example.musclematefront.models.Workout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsActivity extends AppCompatActivity {
    ActivityWorkoutsBinding binding;
    RecyclerView workoutRecyclerView;
    WorkoutsAdapter workoutsAdapter;
    Context context;
    private static final String USER_PREFS = "UserPrefs";
    private static final String USER_KEY = "User";
    private String userId;

    private List<Workout> workoutList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkoutsBinding.inflate(getLayoutInflater());
        context = WorkoutsActivity.this;
        userId = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE).getString(USER_KEY,"");
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        setupWorkoutRecycler();
        sendRequest();



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

    private void setupClickHereTextView(){
        TextView textView = binding.clickHere;
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append("Click  ");
        ssb.setSpan(
                new ImageSpan(this, R.drawable.add),
                ssb.length()-1,
                ssb.length(),
                0
        );
        ssb.append("  to add workout");
        textView.setText(ssb);
    }

    private void setupBottomNavigation() {
        binding.bottomNavigation.setSelectedItemId(R.id.action_workouts);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_home) {
                Intent intent = new Intent(WorkoutsActivity.this, HomeActivity.class);
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
            else if(item.getItemId()==R.id.action_add){
                Intent intent = new Intent(WorkoutsActivity.this, AddWorkoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return true;
        });
    }

    private void sendRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(context,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        workoutList = parseWorkouts(response);
                        Log.e("123",workoutList.toString());
                        if(workoutList.isEmpty()) setupClickHereTextView();
                      else{
                           workoutsAdapter.setWorkoutList(workoutList);
                           workoutsAdapter.notifyDataSetChanged();
                       }

                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(context, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
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
        String url = String.format("http://192.168.1.11:4000/workouts");


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void setupWorkoutRecycler(){

        workoutRecyclerView = (RecyclerView) binding.workoutRecyclerView;
        workoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        workoutsAdapter = new WorkoutsAdapter(workoutList);
        workoutRecyclerView.setAdapter(workoutsAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        workoutsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sendRequest();
    }
}
