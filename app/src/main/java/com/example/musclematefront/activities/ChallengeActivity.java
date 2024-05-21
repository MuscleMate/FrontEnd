package com.example.musclematefront.activities;

import static com.example.musclematefront.parsers.ChallengeParser.parseChallenges;
import static com.example.musclematefront.parsers.SingleChallengeParser.parseSingleChallenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.databinding.ActivityChallengeBinding;
import com.example.musclematefront.databinding.ActivityHomeBinding;
import com.example.musclematefront.databinding.ActivityMainBinding;
import com.example.musclematefront.models.Challenge;

import org.json.JSONObject;

public class ChallengeActivity extends AppCompatActivity {

    private ActivityChallengeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChallengeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        sendRequestChallenge(String.valueOf(id));
    }
    private void sendRequestChallenge(String id){
        ServerRequestHandler requestHandler = new ServerRequestHandler(ChallengeActivity.this,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        Challenge challenge = parseSingleChallenge(response);
                        binding.goalText.setText(challenge.getGoal());
                        binding.difficultyText.setText(challenge.getDifficulty());
                        binding.durationText.setText(String.valueOf(challenge.getDuration()));
                        binding.titleTextView.setText(challenge.getTitle().toString());
                        binding.xPText.setText(String.valueOf(challenge.getExp()));
                        binding.statusText.setText(challenge.getStatus());
                        binding.targetText.setText(String.valueOf(challenge.getTarget()));
                    } else {
                        // Handle other cases if needed
                        // For example, show an error message
                        Toast.makeText(ChallengeActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(ChallengeActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(ChallengeActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://192.168.1.4:4000/challenges/"+id;


        requestHandler.executeWithThreadPool(url,"GET","");
    }
}
