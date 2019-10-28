package com.example.anew.ui.ticket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.anew.ui.call.DashboardFragmentInCall;
import com.example.anew.ui.call.ListCallFragment;
import com.example.anew.ui.call.RemindFragment;
import com.example.anew.ui.sale.DanhsachFragment;
import com.example.anew.ui.sale.GiahanFragment;
import com.example.anew.ui.sale.KeyDungthuFragment;
import com.example.anew.ui.sale.LoaithanhtoanFragment;
import com.example.anew.ui.sale.MagiamgiaFragment;
import com.example.anew.ui.sale.ThongkeSaleFragment;

public class TicketPageAdapter extends FragmentStatePagerAdapter {
    public TicketPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new ListTicketFragment();
                break;
            case 1:
                frag = new ListReceivedFragment();
                break;
            case 2:
                frag = new ListWaitFragment();
                break;
            case 3:
                frag = new DashboardTicketFragment();
                break;

        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {


        String tile = "";
        switch (position){
            case 0:
                tile = "Danh sách ";
                break;
            case 1:
                tile = "Danh sách đã nhận";
                break;
            case 2:
                tile = "Danh sách chờ";
                break;
            case 3:
                tile = "Thống kê";
                break;

        }
        return  tile;
    }
}
