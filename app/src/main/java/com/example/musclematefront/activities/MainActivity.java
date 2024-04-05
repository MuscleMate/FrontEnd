package com.example.musclematefront.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.databinding.ActivityMainBinding;
import com.example.musclematefront.models.Tournament;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int signUpStage=0;
    private boolean isLogIn=true;
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
            binding.logInButton.setText("Log In");
            isLogIn=true;
        });

        binding.signUpChangeButton.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "SignupChange", Toast.LENGTH_SHORT).show();
            binding.editTextPassword.setVisibility(View.GONE);
            binding.editTextPassword2.setVisibility(View.GONE);
            binding.logInButton.setText("Sign Up");
            isLogIn=false;
        });

        binding.logInButton.setOnClickListener(view -> {
            if(isLogIn==true){
                Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                ServerRequestHandler requestHandler = new ServerRequestHandler(new ServerRequestHandler.OnServerResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            String status = response.getString("status");
                            if (status.equals("OK")) {
                                // Start TellMoreActivity
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // Handle other cases if needed
                                // For example, show an error message
                                Toast.makeText(MainActivity.this, "Response not OK", Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error
                            Toast.makeText(MainActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });
                String url = "http://192.168.1.4:4000/auth/login/";

                // JSON payload
                JSONObject jsonPayload = new JSONObject();
                try {
                    jsonPayload.put("email", "example@example.com");
                    jsonPayload.put("password", "123456");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Convert JSON payload to string
                String jsonString = jsonPayload.toString();
                requestHandler.executeWithThreadPool(url,"GET",jsonString);

            }else{
                if(signUpStage==0){
                    binding.editTextName.setVisibility(View.GONE);
                    binding.editTextPassword2.setVisibility(View.VISIBLE);
                    binding.editTextPassword.setVisibility(View.VISIBLE);
                    signUpStage=1;
                }else{
                    if (!(binding.editTextPassword.getText().toString().equals(binding.editTextPassword2.getText().toString()))) {
                        Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }else{
                        binding.editTextPassword2.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this, TellMoreActivity.class);
                        intent.putExtra("email",binding.editTextName.getText().toString());
                        intent.putExtra("password",binding.editTextPassword.getText().toString());
                        startActivity(intent);
                    }

                }
            }


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