package com.example.musclematefront.activitiesSocial;

import static com.example.musclematefront.parsers.NotificationParser.parseNotifications;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.ServerRequestHandler;
import com.example.musclematefront.activities.NotificationsActivity;
import com.example.musclematefront.adapters.NotificationsAdapter;
import com.example.musclematefront.databinding.ActivityNotificationsBinding;
import com.example.musclematefront.databinding.FragmentActivityBinding;
import com.example.musclematefront.models.Notification;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {
    FragmentActivityBinding binding;
    RecyclerView notificationRecyclerView;
    NotificationsAdapter notificationsAdapter;
    List<Notification> notificationsList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityBinding.inflate(inflater, container, false);  // Properly initialize the class-level binding
        View rootView = binding.getRoot();


        setupNotificationRecycler();
        sendRequest();

        return rootView;
    }
    private void sendRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(getContext(),new ServerRequestHandler.OnServerResponseListener() {
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
                        Toast.makeText(getContext(), "Response not OK", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    // Handle JSON parsing error
                    Toast.makeText(getContext(), "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        String url = "http://192.168.1.11:4000/user/notifications?count=30";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void setupNotificationRecycler(){

        notificationRecyclerView = (RecyclerView) binding.notification2RecyclerView;
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notificationsAdapter = new NotificationsAdapter(notificationsList);
        notificationRecyclerView.setAdapter(notificationsAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        notificationsAdapter.notifyDataSetChanged();
    }
}