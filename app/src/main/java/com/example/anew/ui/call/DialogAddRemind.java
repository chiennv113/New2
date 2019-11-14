package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.IDialogClick;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogAddRemind extends DialogFragment {
    private TextInputEditText mEdtSearchCu;
    private TextView mTvName;
    private TextView mTvEmail;
    private TextInputEditText mEdtContent;
    private ImageButton mTvSearch;
    private Button mBtnOk;
    private Button mBtnCancel;
    private Context context;

    private ArrayList<Integer> arrayList = new ArrayList();

    private IDialogClick iDialogClick;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvChangeDate;
    private TextView mTvChangeTime;

    void setOnClickPositive(IDialogClick iDialogClick) {
        this.iDialogClick = iDialogClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_remind, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        mBtnOk.setOnClickListener(view15 -> {

            String date = mTvChangeDate.getText().toString().trim();
            String time = mTvChangeTime.getText().toString().trim();
            String result1 = date.concat(" ");
            String result2 = result1.concat(time);
            String content = mEdtContent.getText().toString().trim();

            if (content.equals("") || mTvName.getText().toString().trim().equals("") || mTvEmail.getText().toString().trim().equals("") ||
                    mTvChangeDate.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Chưa nhập đủ", Toast.LENGTH_SHORT).show();
            } else {
                int id = arrayList.get(0);
                iDialogClick.clickPositive(content, result2, id);
                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });

        mTvSearch.setOnClickListener(view14 -> {
            final String info = mEdtSearchCu.getText().toString().trim();
            ApiClient.getInstance().search(info, "search_customer", cookie).enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    try {
                        int id = response.body().getId();
                        arrayList.add(id);
                        mTvName.setText(response.body().getFullname());
                        mTvEmail.setText("  (" + response.body().getEmail() + ")");

                    } catch (Exception e) {
                        if (info.equals("")) {
                            Toast.makeText(context, "" + "Chưa nhập thông tin tìm kiếm", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "" + "Thông tin nhập không khớp", Toast.LENGTH_SHORT).show();

                        }
                    }

                }

                @Override
                public void onFailure(Call<Search> call, Throwable t) {

                }
            });
        });

        mTvDate.setOnClickListener(view12 -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view1, year, monthOfYear, dayOfMonth) -> mTvChangeDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        mTvTime.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                    (view16, hourOfDay, minute) -> mTvChangeTime.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
            timePickerDialog.show();
        });

        mBtnCancel.setOnClickListener(view13 -> dismiss());
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

    private void initView(View view) {
        mEdtSearchCu = view.findViewById(R.id.edt_search_cu);
        mTvName = view.findViewById(R.id.tv_name);
        mTvEmail = view.findViewById(R.id.tv_email);
        mEdtContent = view.findViewById(R.id.edt_content);
        mTvSearch = view.findViewById(R.id.tv_search);
        mBtnOk = view.findViewById(R.id.btn_ok);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mTvDate = view.findViewById(R.id.tvDateC);
        mTvTime = view.findViewById(R.id.tvTime);
        mTvChangeDate = view.findViewById(R.id.tvChangeDate);
        mTvChangeTime = view.findViewById(R.id.tvChangeTime);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
