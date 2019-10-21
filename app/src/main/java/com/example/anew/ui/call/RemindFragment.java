package com.example.anew.ui.call;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.Adapter_List_Call_Remind;
import com.example.anew.Model.ModelDeleteRemind;
import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.ItemClickRv;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemindFragment extends Fragment {


    private RecyclerView mRv;
    private Button mBtnAdd;

    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Adapter_List_Call_Remind adapter_list_call_remind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_remind, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        SharedPreferences prefs = getActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String cookie = prefs.getString("cookie_name", "No name defined");
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Dialog_Add_Remind dialog_add_remind = new Dialog_Add_Remind();
                dialog_add_remind.show(fm, null);

            }
        });

        getData(cookie);
        adapter_list_call_remind = new Adapter_List_Call_Remind(modelListPhoneCallReminds, getActivity(), new ItemClickRv() {
            @Override
            public void onItemClick(int position, final int id, View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApiClient.getInstance().deleteRemind("delete_phone_remind", id, cookie).enqueue(new Callback<ModelDeleteRemind>() {
                            @Override
                            public void onResponse(Call<ModelDeleteRemind> call, Response<ModelDeleteRemind> response) {
                                try {
                                    Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    adapter_list_call_remind.notifyDataSetChanged();
                                    getData(cookie);
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), "" + response.body().getError(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ModelDeleteRemind> call, Throwable t) {
                            }
                        });
                    }
                });
            }
        });
        mRv.setAdapter(adapter_list_call_remind);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(linearLayoutManager);
        adapter_list_call_remind.notifyDataSetChanged();


    }

    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
        mBtnAdd = view.findViewById(R.id.btn_add);
    }

    private void getData(String cookie) {
        ApiClient.getInstance().getListRemind("load_phone_remind", cookie).enqueue(new Callback<List<ModelListPhoneCallRemind>>() {
            @Override
            public void onResponse(Call<List<ModelListPhoneCallRemind>> call, Response<List<ModelListPhoneCallRemind>> response) {
                modelListPhoneCallReminds.addAll(response.body());
                adapter_list_call_remind.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelListPhoneCallRemind>> call, Throwable t) {
            }
        });
    }
}
