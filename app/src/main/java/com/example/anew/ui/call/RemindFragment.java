package com.example.anew.ui.call;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.Adapter_List_Call_Remind;
import com.example.anew.Model.ModelAddRemind;
import com.example.anew.Model.ModelDeleteRemind;
import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.IDialogClick;
import com.example.anew.helper.IRemoveRemid;
import com.example.anew.helper.ItemClickRv;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemindFragment extends Fragment implements IRemoveRemid {
    private RecyclerView mRv;
    private Button mBtnAdd;
    private String cookie;

    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds = new ArrayList<>();
    private Adapter_List_Call_Remind adapter_list_call_remind;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_remind, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        initView(view);
        requestLoadPhoneRemid();
        initAdapter();

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogRemind();
            }
        });
    }

    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
        mBtnAdd = view.findViewById(R.id.btn_add);
    }

    private void initAdapter() {
        adapter_list_call_remind = new Adapter_List_Call_Remind(modelListPhoneCallReminds, getActivity(), this);
        mRv.setAdapter(adapter_list_call_remind);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void showDialogRemind() {
        final Dialog_Add_Remind dialog_add_remind = new Dialog_Add_Remind();
        dialog_add_remind.setOnClickPositive(new IDialogClick() {
            @Override
            public void clickPositive(String content, String timeConvert, int id) {
                long time = ConvertHelper.convertStringToTimestampMilisecond(timeConvert);
                ApiClient.getInstance().addRemind("add_phone_remind", content, time, id, cookie).enqueue(new Callback<ModelAddRemind>() {
                    @Override
                    public void onResponse(Call<ModelAddRemind> call, Response<ModelAddRemind> response) {
                        requestLoadPhoneRemid();
                        dialog_add_remind.dismiss();
                    }

                    @Override
                    public void onFailure(Call<ModelAddRemind> call, Throwable t) {

                    }
                });
            }
        });
        dialog_add_remind.show(getChildFragmentManager(), null);
    }

    private void requestLoadPhoneRemid() {
        ApiClient.getInstance().getListRemind("load_phone_remind", cookie).enqueue(new Callback<List<ModelListPhoneCallRemind>>() {
            @Override
            public void onResponse(Call<List<ModelListPhoneCallRemind>> call, Response<List<ModelListPhoneCallRemind>> response) {
                if (response.code() == Constans.SERVER_SUCCESS && response.body() != null) {
                    modelListPhoneCallReminds.clear();
                    modelListPhoneCallReminds.addAll(response.body());
                    adapter_list_call_remind.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelListPhoneCallRemind>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(int position, int id) {
        ApiClient.getInstance().deleteRemind("delete_phone_remind", id, cookie).enqueue(new Callback<ModelDeleteRemind>() {
            @Override
            public void onResponse(Call<ModelDeleteRemind> call, Response<ModelDeleteRemind> response) {
                Log.e("GGG", "onResponse: " + response.code());
                if (response.code() == Constans.SERVER_SUCCESS) {
                    requestLoadPhoneRemid();
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("GGG", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<ModelDeleteRemind> call, Throwable t) {

            }
        });
    }
}
