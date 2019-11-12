package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.anew.Adapter.AdapterListCallRemind;
import com.example.anew.Model.ModelAddRemind;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAddRemindInfo extends DialogFragment {
    private TextInputEditText mEdtND;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvCancel;
    private TextView mTvSave;
    private TextView mTvSelectTime;
    private TextView mTvSelectDate;

    private Context context;

    AdapterListCallRemind adapterListCallRemind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_remind_info, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        Bundle mArgs = getArguments();
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        if (mArgs != null) {
            int idCus = mArgs.getInt("key");
            mTvSelectDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            (view1, year, monthOfYear, dayOfMonth) -> mTvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });
            mTvSelectTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar c = Calendar.getInstance();
                    int mHour = c.get(Calendar.HOUR_OF_DAY);
                    int mMinute = c.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                            (view16, hourOfDay, minute) -> mTvTime.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
                    timePickerDialog.show();
                }
            });

            mTvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            mTvSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String date = mTvDate.getText().toString().trim();
                    String time = mTvTime.getText().toString().trim();
                    String result1 = date.concat(" ");
                    String result2 = result1.concat(time);

                    if (mEdtND.getText().toString().trim().equals("") ||
                            mTvTime.getText().toString().trim().equals("") ||
                            mTvDate.getText().toString().trim().equals("")) {
                        Toast.makeText(context, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    } else {
                        ApiClient.getInstance().addRemind("add_phone_remind", mEdtND.getText().toString().trim(),
                                ConvertHelper.convertStringToTimestampDateAndTime(result2), idCus, cookie)
                                .enqueue(new Callback<ModelAddRemind>() {
                                    @Override
                                    public void onResponse(Call<ModelAddRemind> call, Response<ModelAddRemind> response) {
                                        if (response.body() != null) {
                                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ModelAddRemind> call, Throwable t) {

                                    }
                                });
                    }
                }
            });
        }
    }

    private void initView(View view) {
        mEdtND = view.findViewById(R.id.edtND);
        mTvDate = view.findViewById(R.id.tvDate);
        mTvTime = view.findViewById(R.id.tvTime);
        mTvCancel = view.findViewById(R.id.tvCancel);
        mTvSave = view.findViewById(R.id.tvSave);
        mTvSelectTime = view.findViewById(R.id.tvSelectTime);
        mTvSelectDate = view.findViewById(R.id.tvSelectDate);
    }

    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.85), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
