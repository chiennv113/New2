package com.example.anew.ui.sale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.anew.ui.call.DashboardFragmentInCall;
import com.example.anew.ui.call.ListCallFragment;
import com.example.anew.ui.call.RemindFragment;

public class SalePageAdapter extends FragmentStatePagerAdapter {
    public SalePageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new DanhsachFragment();
                break;
            case 1:
                frag = new KeyDungthuFragment();
                break;
            case 2:
                frag = new GiahanFragment();
                break;
            case 3:
                frag = new MagiamgiaFragment();
                break;
            case 4:
                frag = new LoaithanhtoanFragment();
                break;
            case 5:
                frag = new ThongkeSaleFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        String title = "";
        switch (position) {
            case 0:
                title = "Danh sach";
                break;
            case 1:
                title = "Key dung thu";
                break;
            case 2:
                title = "Gia han";
                break;
            case 3:
                title = "Ma giam gia";
                break;
            case 4:
                title = "Loai thanh toan";
                break;
            case 5:
                title = "Thống kê";
                break;
        }
        return title;
    }

}
