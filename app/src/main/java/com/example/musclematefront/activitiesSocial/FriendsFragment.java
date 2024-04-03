package com.example.musclematefront.activitiesSocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musclematefront.R;
import com.example.musclematefront.adapters.FriendRequestAdapter;
import com.example.musclematefront.databinding.FragmentFriendsBinding;

import java.util.ArrayList;
import java.util.List;

public class FriendsFragment extends Fragment {
    RecyclerView friendsRequestRecyclerView;
    FriendRequestAdapter friendsRequestAdapter;
    private FragmentFriendsBinding binding;
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
            setupFriendRequestRecycler();
        return rootView;
    }
    public void setupFriendRequestRecycler(){
        List<String> friendsList = new ArrayList<>();
        friendsList.add("Hubert Bubert");
        friendsList.add("Hubert Bubert");
        friendsList.add("Hubert Bubert");
        friendsRequestRecyclerView = (RecyclerView) binding.friendsRequestRecyclerView;
        friendsRequestRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        friendsRequestAdapter = new FriendRequestAdapter(friendsList);
        friendsRequestRecyclerView.setAdapter(friendsRequestAdapter);
        // The list we passed to the mAdapter was changed so we have to notify it in order to update
        friendsRequestAdapter.notifyDataSetChanged();
    }
}
