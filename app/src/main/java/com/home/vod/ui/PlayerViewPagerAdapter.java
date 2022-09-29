package com.home.vod.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Muvi on 9/1/2017.
 */

 public class PlayerViewPagerAdapter extends FragmentPagerAdapter {

    public PlayerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new PlayerFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}