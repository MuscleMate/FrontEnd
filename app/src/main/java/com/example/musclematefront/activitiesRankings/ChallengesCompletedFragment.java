package com.example.musclematefront.activitiesRankings;

import static com.example.musclematefront.parsers.RankingChallengesParser.parseRankingChallenges;

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
import com.example.musclematefront.adapters.RankingChallengesAdapter;
import com.example.musclematefront.databinding.FragmentCompletedChallengesBinding;
import com.example.musclematefront.models.RankingChallenges;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChallengesCompletedFragment extends Fragment {
    RecyclerView challengesRecyclerView;
    RankingChallengesAdapter rankingChallengesAdapter;
    private List<RankingChallenges> rankingChallengesList = new ArrayList<>();
    private FragmentCompletedChallengesBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCompletedChallengesBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        setupChallengesRecycler();
        sendRequestChallenge();
        return rootView;
    }
    private void setupChallengesRecycler() {
        challengesRecyclerView = (RecyclerView) binding.challengesRecyclerView;
        challengesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rankingChallengesAdapter = new RankingChallengesAdapter(rankingChallengesList);
        challengesRecyclerView.setAdapter(rankingChallengesAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        rankingChallengesAdapter.notifyDataSetChanged();
    }
    private void sendRequestChallenge(){
        ServerRequestHandler requestHandler = new ServerRequestHandler(getContext(),new ServerRequestHandler.OnServerResponseListener() {
            @Override
            public void onResponse(Pair<Integer, JSONObject> responsePair) {
                int statusCode = responsePair.first;
                JSONObject response = responsePair.second;
                try{
                    String status = response.optString("status");
                    Log.d("asd", "onResponse: "+response.toString());
                    if (status.equals("OK")||statusCode==200||statusCode==201) {
                        rankingChallengesList = parseRankingChallenges(response);
                        rankingChallengesAdapter.setChallengesList(rankingChallengesList);
                        rankingChallengesAdapter.notifyDataSetChanged();
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
        String url = "http://192.168.1.11:4000/friends/rankings/challenges";


        requestHandler.executeWithThreadPool(url,"GET","");
    }
}
