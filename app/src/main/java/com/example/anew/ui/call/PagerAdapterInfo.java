package com.example.anew.ui.call;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapterInfo extends FragmentStatePagerAdapter {
    private String name;
    private String phone;
    private String address;
    private String skye;
    private String email;
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public PagerAdapterInfo(@NonNull FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String name, String phone, String address, String skye, String email) {
        mFragmentList.add(fragment);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.skye = skye;
        this.email = email;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new FragmentInfo().newInstance(this.name, this.address, this.phone, this.skye, this.email);
                break;
            case 1:
                frag = new FragmentTongHopCuocGoi();
                break;
            case 2:
                frag = new FragmentTicketDaGui();
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
                title = "Thông tin";
                break;
            case 1:
                title = "Tổng hợp cuộc gọi";
                break;
            case 2:
                title = "Ticket";
                break;
        }
        return title;    }
}
