package com.example.musclematefront.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musclematefront.activitiesSocial.ActivityFragment;
import com.example.musclematefront.activitiesSocial.ChallengesFragment;
import com.example.musclematefront.activitiesSocial.FriendsFragment;
import com.example.musclematefront.activitiesSocial.RankingsFragment;
import com.example.musclematefront.activitiesSocial.TournamentsFragment;

public class mPagerAdapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 4; // Number of tabs

    public mPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return the appropriate fragment for each position
        switch (position) {
            case 0:
                return new ActivityFragment();
            case 1:
                return new FriendsFragment();
            case 2:
                return new RankingsFragment();
            case 3:
                return new ChallengesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}