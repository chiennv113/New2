package com.example.anew.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anew.Adapter.AdapterTKTungNV;
import com.example.anew.Model.ModelTKTheoNV.ModelThongKeTheoNVAdmin;
import com.example.anew.Model.ModelTKTheoNV.Phone;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TKTheoNVActivity extends AppCompatActivity {

    private ImageView mBtnCancel;
    private TextView mNameNV;
    private RecyclerView mRv;
    LinearLayoutManager linearLayoutManager;
    List<Phone> phoneList = new ArrayList<>();
    AdapterTKTungNV adapterTKTungNV;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tktheo_nv);
        initView();
        mBtnCancel.setOnClickListener(v -> finish());
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        long datestart = intent.getLongExtra("startdate", 0);
        long dateend = intent.getLongExtra("enddate", 0);
        linearLayoutManager = new LinearLayoutManager(this);
        adapterTKTungNV = new AdapterTKTungNV(phoneList, this);
        mRv.setAdapter(adapterTKTungNV);
        mRv.setLayoutManager(linearLayoutManager);
        requestLoad(datestart, dateend, position);

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestLoad(datestart, dateend, position);
                new Handler().postDelayed(() -> mSwipeRefresh.setRefreshing(false), 2000);
            }
        });
    }

    private void initView() {
        mBtnCancel = findViewById(R.id.btnCancel);
        mNameNV = findViewById(R.id.nameNV);
        mRv = findViewById(R.id.rv);
        mSwipeRefresh = findViewById(R.id.swipeRefresh);
    }

    public void requestLoad(long datestart, long dateend, int position) {
        ApiClient.getInstance().tkTheoNV("EmployeesStatisticPhones", datestart, dateend, SharePrefs.getInstance().get(Constans.COOKIE, String.class))
                .enqueue(new Callback<List<ModelThongKeTheoNVAdmin>>() {
                    @Override
                    public void onResponse(Call<List<ModelThongKeTheoNVAdmin>> call, Response<List<ModelThongKeTheoNVAdmin>> response) {
                        if (response.body() != null) {
                            mNameNV.setText(response.body().get(position).getFullname());
                            phoneList.addAll(response.body().get(position).getStatistics().getPhone());
                            adapterTKTungNV.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ModelThongKeTheoNVAdmin>> call, Throwable t) {
                    }
                });
    }
}
