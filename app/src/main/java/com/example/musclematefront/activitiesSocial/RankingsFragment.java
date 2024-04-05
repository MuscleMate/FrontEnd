package com.example.musclematefront.activitiesSocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.musclematefront.R;
import com.example.musclematefront.databinding.FragmentFriendsBinding;
import com.example.musclematefront.databinding.FragmentRankingsBinding;
import com.example.musclematefront.utils.RankingsAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RankingsFragment extends Fragment {
    private FragmentRankingsBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRankingsBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            Toolbar toolbar = activity.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("Friends");
            }
        }
        RankingsAdapter adapter = new RankingsAdapter(RankingsFragment.this.getActivity());
        binding.viewPager.setAdapter(adapter);

        // Connect ViewPager with TabLayout
        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position) -> tab.setText("Tab " + (position + 1))
        ).attach();
        return rootView;
    }
}
