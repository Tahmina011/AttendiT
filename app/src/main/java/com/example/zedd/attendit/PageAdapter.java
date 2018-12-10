package com.example.zedd.attendit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sony.attendit.AttendFragment;
import com.example.sony.attendit.StatusFragment;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs =numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatusFragment();
            case 1:
                return new AttendFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
            return numOfTabs;
    }
}

