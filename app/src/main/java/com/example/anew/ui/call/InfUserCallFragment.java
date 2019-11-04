package com.example.anew.ui.call;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfUserCallFragment extends Fragment {

    private Context context;
    private TextView mTvFullName;
    private TextView mTvAddress;
    private TextView mTvEmail;
    private TextView mTvPhone;
    private TextView mTvSkype;

    public static Fragment newInstance(String fullname, String address, String email, String phone, String skype) {
        Fragment fragment = new InfUserCallFragment();
        Bundle args = new Bundle();
        args.putString("fullname", fullname);
        args.putString("address", address);
        args.putString("email", email);
        args.putString("phone", phone);
        args.putString("skype", skype);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inf_user_call, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        if (getArguments() != null) {
            String fullname = getArguments().getString("fullname");
            String address = getArguments().getString("address");
            String email = getArguments().getString("email");
            String phone = getArguments().getString("phone");
            String skype = getArguments().getString("skype");

            mTvFullName.setText(fullname);
            mTvAddress.setText(address);
            mTvEmail.setText(email);
            mTvPhone.setText(phone);
            mTvSkype.setText(skype);
        }


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    private void initView(View view) {
        mTvFullName = view.findViewById(R.id.tvFullName);
        mTvAddress = view.findViewById(R.id.tvAddress);
        mTvEmail = view.findViewById(R.id.tvEmail);
        mTvPhone = view.findViewById(R.id.tvPhone);
        mTvSkype = view.findViewById(R.id.tvSkype);
    }
}
