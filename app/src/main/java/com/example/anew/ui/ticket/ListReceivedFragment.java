package com.example.anew.ui.ticket;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.anew.Activity.ActViewTicketUnAccept;
import com.example.anew.Adapter.AdapterListTicketAccepted;
import com.example.anew.Model.ModelListTicket.ModelListTickKet;
import com.example.anew.Model.ModelListTicketAccepted.Datum;
import com.example.anew.Model.ModelListTicketAccepted.Example;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.IClickListTicketAccepted;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SelectDate;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListReceivedFragment extends Fragment {


    private ImageView mFilterTicketAccept;
    private TextView mDateStart, mDateEnd;
    private RecyclerView mRVTicketAccepted;
    private List<Datum> data = new ArrayList<>();
    private AdapterListTicketAccepted ticketAccepted;
    private LinearLayoutManager layoutManager;


    private int from = 0;
    private int take = 5;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_received_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        mFilterTicketAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterTicketAccepted(ConvertHelper.convertStringToTimestampMilisecond(mDateStart.getText().toString().trim()),
                        ConvertHelper.convertStringToTimestampMilisecond(mDateEnd.getText().toString().trim()),
                        from, take, cookie);
            }
        });

        ticketAccepted = new AdapterListTicketAccepted(data, getActivity(), new IClickListTicketAccepted() {
            @Override
            public void onClickView(int id) {
                Intent intent = new Intent(getActivity(), ActViewTicketUnAccept.class);
                intent.putExtra(Constans.ID_TICKET, id);
                startActivity(intent);

            }

            @Override
            public void onClickTranfer(int id, int position) {


            }
        });

        layoutManager = new LinearLayoutManager(getActivity());
        mRVTicketAccepted.setLayoutManager(layoutManager);
        mRVTicketAccepted.setAdapter(ticketAccepted);
        getDate(cookie);

        mDateStart.setOnClickListener(view15 -> {
            SelectDate.select(getContext(), mDateStart);
        });

        mDateEnd.setOnClickListener(view12 -> {
            SelectDate.select(getContext(), mDateEnd);
        });

    }

    private void initView(View view) {
        mRVTicketAccepted = view.findViewById(R.id.rvlistTicketAccepted);
        mFilterTicketAccept = view.findViewById(R.id.img_filter_ticketAccepted);
        mDateStart = view.findViewById(R.id.tv_dateStartticketAccepted);
        mDateEnd = view.findViewById(R.id.tv_dateEndticketAccepted);
    }

    private void getDate(String cookie) {

        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH);//+1
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        mDateStart.setText("1" + "/" + (month + 1) + "/" + year);
        mDateEnd.setText(day + "/" + (month + 1) + "/" + year);

        int realMonth = month + 1;

        if (realMonth == 1 || realMonth == 3 || realMonth == 5 || realMonth == 7 || realMonth == 8 || realMonth == 10 || realMonth == 12) {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "31/" + realMonth + "/" + year;
            getListTicketAccepted(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), from, take, cookie);
        } else {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "30/" + realMonth + "/" + year;
            getListTicketAccepted(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), from, take, cookie);
        }
    }

    private void getListTicketAccepted(long dateStart, long dateEnd, int from, int take, String cookie) {

        ApiClient.getInstance().getTicketAccepted(dateStart, dateEnd, "accepted_ticket", from, take, cookie).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.body() == null) return;
                data.clear();
                data.addAll(response.body().getData());
                ticketAccepted.updateData(response.body().getData());

                if (response.body().getData() != null) {
                    data.clear();
                    data.addAll(response.body().getData());
                    ticketAccepted.updateData(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

    private void filterTicketAccepted(long dateStart, long dateEnd, int from, int take, String cookie) {

        ApiClient.getInstance().getTicketAccepted(dateStart, dateEnd, "accepted_ticket", from, take, cookie).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() == null) return;
                data.clear();
                data.addAll(response.body().getData());
                ticketAccepted.updateData(response.body().getData());

                if (response.body().getData().size() == 0) {
                    if (response.body().getData() != null) {

                        data.clear();
                        data.addAll(response.body().getData());
                        ticketAccepted.updateData(response.body().getData());

                    } else if (response.body().getData().size() == 0) {
                        Toast.makeText(getContext(), "Không có ticket trong thời gian này", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }

}
