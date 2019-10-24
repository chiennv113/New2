package com.example.anew.ui.call;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new ListCallFragment();
                break;
            case 1:
                frag = new RemindFragment();
                break;
            case 2:
                frag = new DashboardFragmentInCall();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        String title = "";
        switch (position){
            case 0:
                title = "Tìm cuộc gọi";
                break;
            case 1:
                title = "Nhắc nhở";
                break;
            case 2:
                title = "Thống kê";
                break;
        }
        return title;    }
}
