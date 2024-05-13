package com.example.musclematefront.activitiesRankings;

import static com.example.musclematefront.parsers.FriendsParser.parseFriends;

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
import com.example.musclematefront.adapters.FriendRequestAdapter;
import com.example.musclematefront.adapters.FriendsAdapter;
import com.example.musclematefront.adapters.RankingExperienceAdapter;
import com.example.musclematefront.databinding.FragmentExperienceBinding;
import com.example.musclematefront.databinding.FragmentFriendsBinding;
import com.example.musclematefront.models.Friend;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExperienceFragment extends Fragment  {
    RecyclerView experienceRecyclerView;
    RankingExperienceAdapter rankingExperienceAdapter;
    private List<Friend> friendsList = new ArrayList<>();
    private FragmentExperienceBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExperienceBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("Challenges");
            }
        }
        setupexperienceRecycler();
        sendRequestExperience();
        return rootView;
    }
    private void setupexperienceRecycler() {
        experienceRecyclerView = (RecyclerView) binding.experienceRecyclerView;
        experienceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankingExperienceAdapter = new RankingExperienceAdapter(friendsList);
        experienceRecyclerView.setAdapter(rankingExperienceAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        rankingExperienceAdapter.notifyDataSetChanged();
    }
    private void sendRequestExperience(){
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
                        rankingExperienceAdapter.setFriendsRequestList(friendsList);
                        rankingExperienceAdapter.notifyDataSetChanged();
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
        String url = "http://192.168.1.4:4000/friends/rankings/exp";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
}
