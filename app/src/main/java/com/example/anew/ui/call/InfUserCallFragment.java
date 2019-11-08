package com.example.anew.ui.call;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.R;

public class InfUserCallFragment extends Fragment {


    private TextView mTvFullName;
    private TextView mTvDiaChi;
    private TextView mTvEm;
    private TextView mTvSDT;
    private TextView mTvSky;

    public static Fragment newInstance(String name, String address, String email, String phone, String sky) {
        Fragment fragment = new InfUserCallFragment();
        Bundle args = new Bundle();
        args.putString("n", name);
        args.putString("a", address);
        args.putString("e", email);
        args.putString("p", phone);
        args.putString("s", sky);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inf_user_call, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        if (getArguments() != null) {
            String name = getArguments().getString("n");
            String address = getArguments().getString("a");
            String email = getArguments().getString("e");
            String phone = getArguments().getString("p");
            String sky = getArguments().getString("s");
            mTvFullName.setText(name);
            mTvDiaChi.setText(address);
            mTvEm.setText(email);
            mTvSDT.setText(phone);
            mTvSky.setText(sky);
        }


    }

    private void initView(View view) {
        mTvFullName = view.findViewById(R.id.tvFullName);
        mTvDiaChi = view.findViewById(R.id.tvDiaChi);
        mTvEm = view.findViewById(R.id.tvEm);
        mTvSDT = view.findViewById(R.id.tvSDT);
        mTvSky = view.findViewById(R.id.tvSky);
    }
}
