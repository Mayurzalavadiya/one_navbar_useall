package com.myapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.myapplication.Fragment.TabFragment;
import com.myapplication.Fragment.TabFragment2;

public class mainAdapter extends FragmentPagerAdapter {

    public mainAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "tab_1";
            case 1:
                return "tab_2";
        }
        return super.getPageTitle(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabFragment();
            case 1:

                return new TabFragment2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
