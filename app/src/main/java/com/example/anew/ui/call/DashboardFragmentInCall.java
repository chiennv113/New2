package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anew.Adapter.AdapterTkTheoNV;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.Model.ModelTKCuocGoiAdmin.ModelThongKeCuocGoiAdmin;
import com.example.anew.Model.ModelTKCuocgoiBanThan.ModelThongKeCuocGoiBanThan;
import com.example.anew.Model.ModelTKTheoDoHaiLongKH.ModelThongKeTheoDoHaiLongCuaKhachAdmin;
import com.example.anew.Model.ModelTKTheoNV.ModelThongKeTheoNVAdmin;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.IClickRv;
import com.example.anew.helper.IClickShowDialogNV;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentInCall extends Fragment {
    private Context context;


    private TextView mTvDateStart;
    private TextView mTvDateEnd;
    private Spinner mSpinerFeel;
    private ImageView mBtnSend;
    private TextView mTvAllMySelf;
    private TextView mTvOldMySelf;
    private TextView mTvNewMyself;
    private TextView mTvAllCall;
    private TextView mTvOldCall;
    private TextView mTvNewCall;

    private List<ModelCustomeFeelNew> modelThongKeTheoDoHaiLongCuaKhachAdmins = new ArrayList<>();

    private List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmins = new ArrayList<>();
    private RecyclerView mRvNV;
    private LinearLayoutManager linearLayoutManager;
    private AdapterTkTheoNV adapterTkTheoNV;
    private SwipeRefreshLayout mSwipeRefresh;


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
        LoadCustomerFeel();

        mSwipeRefresh.setOnRefreshListener(() -> new Handler().postDelayed(() -> mSwipeRefresh.setRefreshing(false), 2000));


        mTvDateEnd.setOnClickListener(view14 -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view1, year, monthOfYear, dayOfMonth) -> mTvDateEnd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mTvDateStart.setOnClickListener(view12 -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view121, year, monthOfYear, dayOfMonth) -> mTvDateStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mBtnSend.setOnClickListener(view13 -> {

            if (mSpinerFeel.getSelectedItem().toString().equals("Tất cả")) {
                getDataTKCuocgoiBanthan(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie);
                thongKeCuocGoi(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie);
            } else if (mSpinerFeel.getSelectedItem().toString().equals("Rất hài lòng")) {
                thongKeTheoDoHaiLong(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        modelThongKeTheoDoHaiLongCuaKhachAdmins.get(0).getId(), cookie);
            } else if (mSpinerFeel.getSelectedItem().toString().equals("Hài lòng")) {
                thongKeTheoDoHaiLong(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        modelThongKeTheoDoHaiLongCuaKhachAdmins.get(1).getId(), cookie);
            } else if (mSpinerFeel.getSelectedItem().toString().equals("Bình thường")) {
                thongKeTheoDoHaiLong(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        modelThongKeTheoDoHaiLongCuaKhachAdmins.get(2).getId(), cookie);
            } else if (mSpinerFeel.getSelectedItem().toString().equals("Tệ")) {
                thongKeTheoDoHaiLong(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        modelThongKeTheoDoHaiLongCuaKhachAdmins.get(3).getId(), cookie);
            } else if (mSpinerFeel.getSelectedItem().toString().equals("Rất tệ")) {
                thongKeTheoDoHaiLong(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        modelThongKeTheoDoHaiLongCuaKhachAdmins.get(4).getId(), cookie);
            } else {
                thongKeCuocGoi(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim())
                        , ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie);
            }
            getDataTKCuocgoiBanthan(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim())
                    , ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie);

        });

        adapterTkTheoNV = new AdapterTkTheoNV(modelThongKeTheoNVAdmins, context, new IClickRv() {
            @Override
            public void onClick(int position) {
                showdialog();
            }
        });
        mRvNV.setAdapter(adapterTkTheoNV);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvNV.setLayoutManager(linearLayoutManager);

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);//+1
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);

        mTvDateStart.setText("1" + "/" + (month + 1) + "/" + year);
        mTvDateEnd.setText(day + "/" + (month + 1) + "/" + year);
        int realMonth = month + 1;
        if (realMonth == 1 || realMonth == 3 || realMonth == 5 || realMonth == 7 || realMonth == 8 || realMonth == 10 || realMonth == 12) {
            String start = "1/" + realMonth + "/" + year;
            String end = "31/" + realMonth + "/" + year;
            getDataTKCuocgoiBanthan(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);
            thongKeCuocGoi(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);
            ThongKeTheoNV(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);

        } else {
            String start = "1/" + realMonth + "/" + year;
            String end = "30/" + realMonth + "/" + year;
            getDataTKCuocgoiBanthan(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);
            thongKeCuocGoi(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);
            ThongKeTheoNV(ConvertHelper.convertStringToTimestampMilisecond(start),
                    ConvertHelper.convertStringToTimestampMilisecond(end), cookie);
        }


    }

    public void thongKeCuocGoi(long start, long end, String cookie) {
        ApiClient.getInstance().tkTheoCuocGoi("StatisticsPhones", start, end, cookie).enqueue(new Callback<List<ModelThongKeCuocGoiAdmin>>() {
            @Override
            public void onResponse(Call<List<ModelThongKeCuocGoiAdmin>> call, Response<List<ModelThongKeCuocGoiAdmin>> response) {
                int demAllNew = 0;
                int demAllOld = 0;
                int demAll = 0;
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        int a = response.body().get(i).getCustomernew().size();
                        int b = response.body().get(i).getCustomerold().size();
                        int c = response.body().get(i).getPhone().size();
                        demAllNew = demAllNew + a;
                        demAllOld = demAllOld + b;
                        demAll = demAll + c;
                    }
                }
                mTvOldCall.setText(String.valueOf(demAllOld));
                mTvNewCall.setText(String.valueOf(demAllNew));
                mTvAllCall.setText(String.valueOf(demAll));
            }

            @Override
            public void onFailure(Call<List<ModelThongKeCuocGoiAdmin>> call, Throwable t) {

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
        mRvNV = view.findViewById(R.id.rvNV);
        mSwipeRefresh = view.findViewById(R.id.swipeRefresh);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    private void LoadCustomerFeel() {
        ApiClient.getInstance().getFeel("get_PhoneCallFeel").enqueue(new Callback<List<ModelCustomeFeelNew>>() {
            @Override
            public void onResponse(Call<List<ModelCustomeFeelNew>> call, Response<List<ModelCustomeFeelNew>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(0, "Tất cả");
                if (response.isSuccessful() && response.body() != null) {
                    modelThongKeTheoDoHaiLongCuaKhachAdmins.addAll(response.body());
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mSpinerFeel.setAdapter(arrayAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelCustomeFeelNew>> call, Throwable t) {

            }
        });
    }

    private void thongKeTheoDoHaiLong(long start, long end, int id, String cookie) {
        ApiClient.getInstance().tkTheoDoHaiLongCuaKhach("statistic_phone_customer_feel", start, end, id, cookie).enqueue(new Callback<List<ModelThongKeTheoDoHaiLongCuaKhachAdmin>>() {
            @Override
            public void onResponse(Call<List<ModelThongKeTheoDoHaiLongCuaKhachAdmin>> call, Response<List<ModelThongKeTheoDoHaiLongCuaKhachAdmin>> response) {
                int demFeelNew = 0;
                int demFeelOld = 0;
                int demFeel = 0;
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        int a = response.body().get(i).getCustomernew().size();
                        int b = response.body().get(i).getCustomerold().size();
                        int c = response.body().get(i).getPhone().size();
                        demFeelNew = demFeelNew + a;
                        demFeelOld = demFeelOld + b;
                        demFeel = demFeel + c;
                    }
                }
                mTvOldCall.setText(String.valueOf(demFeelOld));
                mTvNewCall.setText(String.valueOf(demFeelNew));
                mTvAllCall.setText(String.valueOf(demFeel));
            }

            @Override
            public void onFailure(Call<List<ModelThongKeTheoDoHaiLongCuaKhachAdmin>> call, Throwable t) {

            }
        });
    }

    private void ThongKeTheoNV(long start, long end, String cookie) {
        ApiClient.getInstance().tkTheoNV("EmployeesStatisticPhones", start, end, cookie).enqueue(new Callback<List<ModelThongKeTheoNVAdmin>>() {
            @Override
            public void onResponse(Call<List<ModelThongKeTheoNVAdmin>> call, Response<List<ModelThongKeTheoNVAdmin>> response) {
                modelThongKeTheoNVAdmins.clear();
                modelThongKeTheoNVAdmins.addAll(response.body());
                adapterTkTheoNV.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelThongKeTheoNVAdmin>> call, Throwable t) {

            }
        });
    }

    private void showdialog() {
        final DialogItemNV dialogItemNV = new DialogItemNV();
        dialogItemNV.setOnClickPositive(new IClickShowDialogNV() {
            @Override
            public void onClick() {

            }
        });
        dialogItemNV.show(getChildFragmentManager(), null);
    }
}
