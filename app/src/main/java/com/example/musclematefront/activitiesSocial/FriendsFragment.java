package com.example.musclematefront.activitiesSocial;

import static com.example.musclematefront.parsers.FriendsParser.parseFriends;
import static com.example.musclematefront.parsers.NotificationParser.parseNotifications;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import com.example.musclematefront.activities.ProfileActivity;
import com.example.musclematefront.adapters.FriendInviteAdapter;
import com.example.musclematefront.adapters.FriendRequestAdapter;
import com.example.musclematefront.adapters.FriendsAdapter;
import com.example.musclematefront.databinding.FragmentFriendsBinding;
import com.example.musclematefront.models.Friend;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    RecyclerView friendsRequestRecyclerView;
    FriendRequestAdapter friendsRequestAdapter;
    RecyclerView friendsRecyclerView;
    FriendsAdapter friendsAdapter;
    RecyclerView friendsInviteRecyclerView;
    FriendInviteAdapter friendsInviteAdapter;
    private FragmentFriendsBinding binding;
    private List<Friend> friendsList = new ArrayList<>();
    private List<Friend> friendsRequestList = new ArrayList<>();
    private List<Friend> friendsInviteList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("Friends");
            }
        }
        sendRequestFriends();
        sendRequestFriendsRequest();
        setupFriendRequestRecycler();
        setupFriendsRecycler();
        setupInviteFriends();
        return rootView;
    }
    private void setupInviteFriends(){
        binding.searchTextView.setVisibility(View.GONE);
        binding.searchFriendButton.setVisibility(View.GONE);
        binding.friendsInviteRecyclerView.setVisibility(View.GONE);

        friendsInviteRecyclerView = (RecyclerView) binding.friendsInviteRecyclerView;
        friendsInviteRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        friendsInviteAdapter = new FriendInviteAdapter(friendsInviteList);
        friendsInviteRecyclerView.setAdapter(friendsInviteAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        friendsInviteAdapter.notifyDataSetChanged();

        binding.addSearchButton.setOnClickListener(view -> {
            if(binding.searchTextView.getVisibility()==View.VISIBLE) {
                binding.searchTextView.setVisibility(View.GONE);
                binding.searchFriendButton.setVisibility(View.GONE);
                binding.friendsInviteRecyclerView.setVisibility(View.GONE);
            }else {binding.searchTextView.setVisibility(View.VISIBLE);
                binding.searchFriendButton.setVisibility(View.VISIBLE);
                binding.friendsInviteRecyclerView.setVisibility(View.VISIBLE);

            }
        });
        binding.searchFriendButton.setOnClickListener(view -> {
            sendSearchRequest();
        });
    }
    public void setupFriendRequestRecycler(){
        friendsRequestRecyclerView = (RecyclerView) binding.friendsRequestRecyclerView;
        friendsRequestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        friendsRequestAdapter = new FriendRequestAdapter(friendsRequestList);
        friendsRequestRecyclerView.setAdapter(friendsRequestAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        friendsRequestAdapter.notifyDataSetChanged();
    }
    public void setupFriendsRecycler(){
        friendsRecyclerView = (RecyclerView) binding.friendsRecyclerView;
        friendsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        friendsAdapter = new FriendsAdapter(friendsList);
        friendsRecyclerView.setAdapter(friendsAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        friendsRequestAdapter.notifyDataSetChanged();
    }
    private void sendSearchRequest(){

        ServerRequestHandler requestHandler = new ServerRequestHandler(getContext(),new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        friendsInviteList = parseFriends(response);
                        friendsInviteAdapter.setFriendsInviteList(friendsInviteList);
                        friendsInviteAdapter.notifyDataSetChanged();
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
        String url = "http://192.168.1.4:4000/friends/search?count=20/";

        // JSON payload
        JSONObject jsonPayload = new JSONObject();
        try {
            jsonPayload.put("searchText", binding.searchTextView.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Convert JSON payload to string
        String jsonString = jsonPayload.toString();
        requestHandler.executeWithThreadPool(url,"POST",jsonString);
    }
    public static void sendPostFriendsRequest(String id, Context context){

        ServerRequestHandler requestHandler = new ServerRequestHandler(context,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
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
        String url = "http://192.168.1.4:4000/friends/request/send";

        // JSON payload
        JSONObject jsonPayload = new JSONObject();
        try {
            jsonPayload.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Convert JSON payload to string
        String jsonString = jsonPayload.toString();
        requestHandler.executeWithThreadPool(url,"POST",jsonString);
    }
    private void sendRequestFriends(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(getContext(),new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        friendsList = parseFriends(response);
                        friendsAdapter.setFriendsList(friendsList);
                        friendsAdapter.notifyDataSetChanged();
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
        String url = "http://192.168.1.4:4000/friends";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    private void sendRequestFriendsRequest(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(getContext(),new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        friendsRequestList = parseFriends(response);
                        friendsRequestAdapter.setFriendsRequestList(friendsRequestList);
                        friendsRequestAdapter.notifyDataSetChanged();
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
        String url = "http://192.168.1.4:4000/friends/request/received";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
    public static void sendAcceptFriend(String userID, Context context){
        ServerRequestHandler requestHandler = new ServerRequestHandler(context,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
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
        String url = "http://192.168.1.4:4000/friends/request/accept";
        JSONObject jsonObject = new JSONObject();
        try {
            // Put the ID into the JSON object
            jsonObject.put("id", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        requestHandler.executeWithThreadPool(url,"POST",jsonObject.toString());
    }

    public static void sendIgnoreFriend(String userID, Context context){
        ServerRequestHandler requestHandler = new ServerRequestHandler(context,new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
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
        String url = "http://192.168.1.4:4000/friends/request/deny";
        JSONObject jsonObject = new JSONObject();
        try {
            // Put the ID into the JSON object
            jsonObject.put("id", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        requestHandler.executeWithThreadPool(url,"POST",jsonObject.toString());
    }
}
