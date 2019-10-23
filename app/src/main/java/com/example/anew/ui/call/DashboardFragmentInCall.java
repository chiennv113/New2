package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.Model.ModelTKCuocgoiBanThan.ModelThongKeCuocGoiBanThan;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentInCall extends Fragment {
    private Context context;


    ModelThongKeCuocGoiBanThan modelThongKeCuocGoiBanThan;
    private TextView mTvDateStart;
    private TextView mTvDateEnd;
    private Spinner mSpinerFeel;
    private Button mBtnSend;
    private TextView mTvAllMySelf;
    private TextView mTvOldMySelf;
    private TextView mTvNewMyself;
    private TextView mTvAllCall;
    private TextView mTvOldCall;
    private TextView mTvNewCall;


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


        mTvDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mTvDateEnd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        mTvDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                mTvDateStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataTKCuocgoiBanthan(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim())
                        , ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie);
            }
        });


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
                mTvOldMySelf.setText(String.valueOf(demOld));
                mTvNewMyself.setText(String.valueOf(demNew));
                int demAll = demNew + demOld;
                mTvAllMySelf.setText(String.valueOf(demAll));
            }

            @Override
            public void onFailure(Call<List<ModelThongKeCuocGoiBanThan>> call, Throwable t) {

            }
        });
    }

    private void initView(View view) {

        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mSpinerFeel = view.findViewById(R.id.spinerFeel);
        mBtnSend = view.findViewById(R.id.btnSend);
        mTvAllMySelf = view.findViewById(R.id.tvAllMySelf);
        mTvOldMySelf = view.findViewById(R.id.tvOldMySelf);
        mTvNewMyself = view.findViewById(R.id.tvNewMyself);
        mTvAllCall = view.findViewById(R.id.tvAllCall);
        mTvOldCall = view.findViewById(R.id.tvOldCall);
        mTvNewCall = view.findViewById(R.id.tvNewCall);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


}
