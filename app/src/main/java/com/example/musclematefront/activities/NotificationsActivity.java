package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.NotificationParser.parseNotifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.adapters.FriendRequestAdapter;
import com.example.musclematefront.adapters.NotificationsAdapter;
import com.example.musclematefront.databinding.ActivityNotificationsBinding;
import com.example.musclematefront.models.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    ActivityNotificationsBinding binding;
    RecyclerView notificationRecyclerView;
    NotificationsAdapter notificationsAdapter;
    List<Notification> notificationsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        setupNotificationRecycler();
        sendRequest();
    }
    private void sendRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(NotificationsActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        notificationsList = parseNotifications(response);
                        notificationsAdapter.setNotificationsList(notificationsList);
                        notificationsAdapter.notifyDataSetChanged();
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(NotificationsActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(NotificationsActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(NotificationsActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://192.168.1.9:4000/user/notifications?count=30";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void setupNotificationRecycler(){

        notificationRecyclerView = (RecyclerView) binding.notificationRecyclerView;
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notificationsAdapter = new NotificationsAdapter(notificationsList);
        notificationRecyclerView.setAdapter(notificationsAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        notificationsAdapter.notifyDataSetChanged();
    }

    private void setupAppBar() {
        setSupportActionBar(binding.toolbar.getRoot());
        getSupportActionBar().setTitle("Notifications");
        RelativeLayout relativeLayout= findViewById(R.id.notification_layout);
        relativeLayout.setVisibility(View.GONE);
    }

    private void setupBottomNavigation(){
        binding.bottomNavigation.setSelectedItemId(R.id.action_home);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.action_workouts) {
                Intent intent = new Intent(NotificationsActivity.this, WorkoutsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (item.getItemId() == R.id.action_home) {

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
