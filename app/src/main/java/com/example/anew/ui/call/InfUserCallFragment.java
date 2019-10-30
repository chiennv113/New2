package com.example.anew.ui.call;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

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


    public static Fragment newInstance(String phone) {
        Fragment fragment = new InfUserCallFragment();
        Bundle args = new Bundle();
        args.putString("ABC", phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_inf_user_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        if (getArguments() != null) {
            String phone = getArguments().getString("ABC");
            ApiClient.getInstance().search(phone, "search_customer", cookie).enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    if (response.code() == Constans.SERVER_SUCCESS && response.body() != null) {
                        Log.e("GGG", "onResponse: " + response.body());
                        Log.e("GGG", "onResponse: " + response.body().getFullname());

                        String name = response.body().getFullname();
                        String email = response.body().getEmail();
                        String skype = String.valueOf(response.body().getSkype());
                        String phone = response.body().getPhone1();
                        String add = String.valueOf(response.body().getAddress());

                        mTvEmail.setText(email);
                        mTvAddress.setText(add);
                        mTvPhone.setText(phone);
                        mTvFullName.setText(name);
                        mTvSkype.setText(skype);

                    }
                }

                @Override
                public void onFailure(Call<Search> call, Throwable t) {

                }
            });
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
