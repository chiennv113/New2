package com.example.anew.ui.call;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.R;

public class FragmentInfo extends Fragment {


    private TextView mTvFullName;
    private TextView mTvDiaChi;
    private TextView mTvEm;
    private TextView mTvSDT;
    private TextView mTvSky;


    public Fragment newInstance(String name, String address, String phone, String skye, String email) {
        FragmentInfo fragmentInfo = new FragmentInfo();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("address", address);
        bundle.putString("phone", phone);
        bundle.putString("skye", skye);
        bundle.putString("email", email);
        fragmentInfo.setArguments(bundle);
        return fragmentInfo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inf_user_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String address = getArguments().getString("address");
            String email = getArguments().getString("email");
            String phone = getArguments().getString("phone");
            String sky = getArguments().getString("skye");
            mTvFullName.setText(name);
            mTvDiaChi.setText(address);
            mTvEm.setText(email);
            mTvSDT.setText(phone);
            mTvSky.setText(sky);
        }
    }

    private void initView(View view) {
        mTvFullName = view.findViewById(R.id.tvHoVaTen);
        mTvDiaChi = view.findViewById(R.id.tvDiaChi);
        mTvEm = view.findViewById(R.id.tvEm);
        mTvSDT = view.findViewById(R.id.tvSDT);
        mTvSky = view.findViewById(R.id.tvSky);
    }

}
