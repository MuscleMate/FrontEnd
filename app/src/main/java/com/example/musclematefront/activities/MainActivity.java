package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupButtons();
        //hide password confirm
        binding.editTextPassword2.setVisibility(View.GONE);

    }
    private void setupButtons(){
        binding.logInChangeButton.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "LoginChange", Toast.LENGTH_SHORT).show();
            binding.editTextPassword.setVisibility(View.VISIBLE);
        });

        binding.signUpChangeButton.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "SignupChange", Toast.LENGTH_SHORT).show();
            binding.editTextPassword.setVisibility(View.GONE);
            binding.editTextPassword2.setVisibility(View.GONE);
        });

        binding.logInButton.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
            //String url = "http://192.168.1.13:4000/noauth/tournaments/";
            //ServerRequestHandler requestHandler = new ServerRequestHandler(new ServerRequestHandler.OnServerResponseListener() {
            //    @Override
            //    public void onResponse(JSONObject response) {
            //        TournamentParser tournamentParser = new TournamentParser();
            //        List<Tournament> tournaments = tournamentParser.parseTournaments(response);
//
            //        for (Tournament tournament : tournaments) {
            //            System.out.println(tournament.getName());
            //        }
            //    }
//
            //    @Override
            //    public void onError(String error) {
            //        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
            //    }
            //});
            //requestHandler.executeWithThreadPool(url);

            Intent intent = new Intent(MainActivity.this, TellMoreActivity.class);
            startActivity(intent);
        });

        binding.forgotPassword.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "forgotPassword", Toast.LENGTH_SHORT).show();
        });

        binding.facebookIcon.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "facebookIcon", Toast.LENGTH_SHORT).show();
        });

        binding.googleIcon.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "googleIcon", Toast.LENGTH_SHORT).show();
        });
        binding.appleIcon.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "appleIcon", Toast.LENGTH_SHORT).show();
        });

    }
}