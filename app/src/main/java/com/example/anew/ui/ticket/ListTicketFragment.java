package com.example.anew.ui.ticket;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Activity.ActAddNewTicket;
import com.example.anew.Activity.ActViewTicketUnAccept;
import com.example.anew.Adapter.AdapterListTicket;
import com.example.anew.Model.ModelListTicket.Datum;
import com.example.anew.Model.ModelListTicket.ModelListTickKet;
import com.example.anew.Model.ModelTiepNhanTicket;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.IClickListTicket;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SelectDate;
import com.example.anew.utills.SharePrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTicketFragment extends Fragment {

    private FloatingActionButton fab;
    private ImageView mImgFilter;
    private TextView mTvDateStart;
    private TextView mTvDateEnd;
    private RecyclerView mRv;
    private FloatingActionButton mBtnAddTicket;

    private List<Datum> data = new ArrayList<>();
    private AdapterListTicket adapterListTicket;
    private LinearLayoutManager linearLayoutManager;
    private int from = 0;
    private int take = 50;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        mBtnAddTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActAddNewTicket.class);
                startActivity(intent);
            }
        });

        mImgFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()),
                        from, take, cookie);
            }
        });

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(linearLayoutManager);
        adapterListTicket = new AdapterListTicket(data, getActivity(), new IClickListTicket() {
            @Override
            public void onClickView(int id) {
                Intent intent = new Intent(getActivity(), ActViewTicketUnAccept.class);
                intent.putExtra(Constans.ID_TICKET, id);
                startActivity(intent);
            }

            @Override
            public void onClickAccept(int id, int position) {
                ApiClient.getInstance().acceptTicket(id, "accept_unaccept_ticket", cookie).enqueue(new Callback<ModelTiepNhanTicket>() {
                    @Override
                    public void onResponse(Call<ModelTiepNhanTicket> call, Response<ModelTiepNhanTicket> response) {
                        if (response.body() == null) return;
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getDateHienTai(cookie);
                    }

                    @Override
                    public void onFailure(Call<ModelTiepNhanTicket> call, Throwable t) {

                    }
                });
            }
        });
        mRv.setAdapter(adapterListTicket);
        getDateHienTai(cookie);


        mTvDateStart.setOnClickListener(view15 -> {
            SelectDate.select(getContext(), mTvDateStart);
        });

        mTvDateEnd.setOnClickListener(view12 -> {
            SelectDate.select(getContext(), mTvDateEnd);
        });


    }

    private void initView(View view) {
        mImgFilter = view.findViewById(R.id.imgFilter);
        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mRv = view.findViewById(R.id.rv);
        mBtnAddTicket = view.findViewById(R.id.btn_add_ticket);
    }

    private void getDateHienTai(String cookie) {

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);//+1
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mTvDateStart.setText("1" + "/" + (month + 1) + "/" + year);
        mTvDateEnd.setText(day + "/" + (month + 1) + "/" + year);

        int realMonth = month + 1;

        if (realMonth == 1 || realMonth == 3 || realMonth == 5 || realMonth == 7 || realMonth == 8 || realMonth == 10 || realMonth == 12) {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "31/" + realMonth + "/" + year;
            getListTicket(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate),
                    from, take, cookie);

        } else {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "30/" + realMonth + "/" + year;
            getListTicket(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate),
                    from, take, cookie);
        }
    }

    private void getListTicket(long dateStart, long dateEnd, int from, int take, String cookie) {
        ApiClient.getInstance().getListTicket(dateStart, dateEnd, "unaccept_ticket", from, take, cookie)
                .enqueue(new Callback<ModelListTickKet>() {
                    @Override
                    public void onResponse(Call<ModelListTickKet> call, Response<ModelListTickKet> response) {
                        if (response.body() == null) return;

                        data.clear();
                        data.addAll(response.body().getData());
                        adapterListTicket.updateData((List<Datum>) response.body().getData());

                        if (response.body().getData() != null) {
                            data.clear();
                            data.addAll(response.body().getData());
                            adapterListTicket.updateData((List<Datum>) response.body().getData());
                        }

                    }

                    @Override
                    public void onFailure(Call<ModelListTickKet> call, Throwable t) {

                    }
                });
    }

    private void filter(long dateStart, long dateEnd, int from, int take, String cookie) {
        ApiClient.getInstance().getListTicket(dateStart, dateEnd, "unaccept_ticket", from, take, cookie).enqueue(new Callback<ModelListTickKet>() {
            @Override
            public void onResponse(Call<ModelListTickKet> call, Response<ModelListTickKet> response) {

                if (response.body() != null) {
                    if (response.body().getMessage().equals("Lấy tickets thành công")) {
                        data.clear();
                        data.addAll(response.body().getData());
                        adapterListTicket.updateData((List<Datum>) response.body().getData());
                    } else if (response.body().getMessage().equals("Không có ticket mới")) {
                        data.clear();
                        adapterListTicket.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Không có ticket trong thời gian này", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelListTickKet> call, Throwable t) {
            }

        });

    }
}
