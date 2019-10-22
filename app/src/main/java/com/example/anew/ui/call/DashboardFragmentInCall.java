package com.example.anew.ui.call;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.Model.ModelTKCuocgoiBanThan.ModelThongKeCuocGoiBanThan;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentInCall extends Fragment {
    private Context context;


    ModelThongKeCuocGoiBanThan modelThongKeCuocGoiBanThan;
    private TextView mTvItSelfAll;
    private TextView mTvItSelfOld;
    private TextView mTvItSelfNew;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard_in_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        initView(view);
        getDataTKCuocgoiBanthan(1571111000, 1571975000, cookie);
    }


    public void getDataTKCuocgoiBanthan(long start, long end, String cookie) {
        ApiClient.getInstance().tkTheoCuocGoiBanThan("SelfStatisticsPhones", start, end, cookie).enqueue(new Callback<List<ModelThongKeCuocGoiBanThan>>() {
            @Override
            public void onResponse(Call<List<ModelThongKeCuocGoiBanThan>> call, Response<List<ModelThongKeCuocGoiBanThan>> response) {
                int demNew = 0;
                int demOld = 0;
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        int a = response.body().get(i).getCustomernew().size();
                        int b = response.body().get(i).getCustomerold().size();
                        demNew = demNew + a;
                        demOld = demOld + b;
                    }
                }
                mTvItSelfOld.setText(String.valueOf(demOld));
                mTvItSelfNew.setText(String.valueOf(demNew));
                int demAll = demNew + demOld;
                mTvItSelfAll.setText(String.valueOf(demAll));
            }

            @Override
            public void onFailure(Call<List<ModelThongKeCuocGoiBanThan>> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {

        mTvItSelfAll = view.findViewById(R.id.tvItSelfAll);
        mTvItSelfOld = view.findViewById(R.id.tvItSelfOld);
        mTvItSelfNew = view.findViewById(R.id.tvItSelfNew);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
