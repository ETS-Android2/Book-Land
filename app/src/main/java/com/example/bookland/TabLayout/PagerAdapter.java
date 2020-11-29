package com.example.bookland.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numbOfTabs;

    public PagerAdapter(FragmentManager fm, int numbOfTabs) {
        super(fm);
        this.numbOfTabs = numbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TopFragment();
            case 1:
                return new NewFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return numbOfTabs;
    }
}

