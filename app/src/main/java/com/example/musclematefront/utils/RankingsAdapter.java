package com.example.musclematefront.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musclematefront.activitiesRankings.ChallengesCompletedFragment;
import com.example.musclematefront.activitiesRankings.ExperienceFragment;
import com.example.musclematefront.activitiesRankings.WonFragment;
import com.example.musclematefront.activitiesSocial.ChallengesFragment;

public class RankingsAdapter extends FragmentStateAdapter {

    public RankingsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return fragment for each position
        switch (position) {
            case 0:
                return new ExperienceFragment();
            case 1:
                return new ChallengesCompletedFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        // Return the number of fragments
        return 2;
    }
}
