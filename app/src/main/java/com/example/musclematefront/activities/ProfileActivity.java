package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.FriendsParser.parseFriends;
import static com.example.musclematefront.parsers.SupplementParser.parseSupplements;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.adapters.FriendRequestAdapter;
import com.example.musclematefront.adapters.SupplementsAdapter;
import com.example.musclematefront.databinding.ActivityProfileBinding;
import com.example.musclematefront.models.Friend;
import com.example.musclematefront.models.Supplement;
import com.example.musclematefront.parsers.SupplementParser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    RecyclerView supplementsRecyclerView;
    SupplementsAdapter supplementsAdapter;
    private List<Supplement> supplementList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupAppBar();
        setupBottomNavigation();
        setupSettings();
        sendFirstNameRequest();
        sendLastNameRequest();
        sendSupplemetnsRequest();
        setupSupplementsRecyclerView();
    }
    public void setupSupplementsRecyclerView(){
        supplementsRecyclerView = (RecyclerView) binding.supplementsRecyclerView;
        supplementsRecyclerView.setLayoutManager(new LinearLayoutManager(ProfileActivity.this));
        supplementsAdapter = new SupplementsAdapter(supplementList);
        supplementsRecyclerView.setAdapter(supplementsAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        supplementsAdapter.notifyDataSetChanged();
    }
    private void sendSupplemetnsRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(ProfileActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        supplementList = parseSupplements(response);
                        supplementsAdapter.setSupplementList(supplementList);
                        supplementsAdapter.notifyDataSetChanged();
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(ProfileActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(ProfileActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://192.168.1.4:4000/user/suplement/all";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void sendFirstNameRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(ProfileActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        String firstName = response.getString("firstName");
                        binding.firstNameTextView.setText(firstName);
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(ProfileActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(ProfileActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });


        String url = "http://192.168.1.4:4000/user/firstName";
        requestHandler.executeWithThreadPool(url,"GET","");

    }
    private void sendLastNameRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(ProfileActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        String lastName = response.getString("lastName");
                        binding.lastNameTextView.setText(lastName);
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(ProfileActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(ProfileActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ProfileActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });


        String url = "http://192.168.1.4:4000/user/lastName";
        requestHandler.executeWithThreadPool(url,"GET","");

    }



    private void setupSettings(){
        binding.applicationButton.setOnClickListener(view -> {
            Intent intent = new Intent(ProfileActivity.this, SettingsApplicationActivity.class);
            startActivity(intent);
        });
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
            } else if (item.getItemId() == R.id.action_home) {
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
