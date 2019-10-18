package com.example.anew.ui.call;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.anew.Model.ModelAdd;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result_Info_Dialog extends DialogFragment {
    private TextView mTvRsName;
    private TextView mTvRsEmail;
    private Button mDialogBtnOk;
    private Button mDialogBtnNo;
    private Context context;

    public static Result_Info_Dialog newInstance(String name, String email, String content, int cus_id) {
        Result_Info_Dialog dialog = new Result_Info_Dialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("email", email);
        args.putString("content", content);
        args.putInt("cus_id", cus_id);
        dialog.setArguments(args);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_result_search, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String email = getArguments().getString("email");
            mTvRsName.setText(name);
            mTvRsEmail.setText(email);
        }

        mDialogBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCustomerFeel("get_PhoneCallFeel");
                getDialog().dismiss();
            }
        });

        mDialogBtnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void initView(View view) {
        mTvRsName = view.findViewById(R.id.tv_Rs_name);
        mTvRsEmail = view.findViewById(R.id.tv_Rs_email);
        mDialogBtnOk = view.findViewById(R.id.dialog_btn_ok);
        mDialogBtnNo = view.findViewById(R.id.dialog_btn_no);
    }

    private void getCustomerFeel(final String option) {
        if (getArguments() != null) {
            SharedPreferences prefs = getActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE);
            final String cookie = prefs.getString("cookie_name", "No name defined");
            final String content = getArguments().getString("content");
            final int cus_id = getArguments().getInt("cus_id", 0);
            ApiClient.getInstance().getFeel(option).enqueue(new Callback<List<ModelCustomeFeelNew>>() {
                @Override
                public void onResponse(Call<List<ModelCustomeFeelNew>> call, Response<List<ModelCustomeFeelNew>> response) {
                    Log.e("size", "onResponse: " + response.body().size());
                    if (response.isSuccessful() && response.body() != null) {
                        for (int i = 0; i < response.body().size(); i++) {
                            Log.e("feel", "onResponse: " + response.body().get(i).getName());
                        }
                        int positonRandom = (int) Math.floor(Math.random() * response.body().size());
                        add("add_phone_call", cus_id, content, response.body().get(positonRandom).getName(),
                                cookie, "application/x-www-form-urlencoded");
                    }
                }

                @Override
                public void onFailure(Call<List<ModelCustomeFeelNew>> call, Throwable t) {
                }
            });
        }


    }

    private void add(String option, int id, String content, String cus_feel, String cookie, String type) {
        ApiClient.getInstance().add(option, id, content, cus_feel, cookie, type).enqueue(new Callback<ModelAdd>() {
            @Override
            public void onResponse(Call<ModelAdd> call, Response<ModelAdd> response) {
                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelAdd> call, Throwable t) {
            }
        });
    }

    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
