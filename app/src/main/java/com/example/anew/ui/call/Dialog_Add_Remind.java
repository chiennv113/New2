package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.anew.Adapter.Adapter_List_Call_Remind;
import com.example.anew.Model.ModelAddRemind;
import com.example.anew.Model.ModelListPhoneCallRemind.ModelListPhoneCallRemind;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dialog_Add_Remind extends DialogFragment {
    private TextInputEditText mEdtSearchCu;
    private TextView mTvName;
    private TextView mTvEmail;
    private TextInputEditText mEdtContent;
    private TextView mTvChangeDate;
    private TextView mTvSearch;
    private Button mBtnOk;
    private Button mBtnCancel;
    private Context context;
    private List<ModelListPhoneCallRemind> modelListPhoneCallReminds;


    private Adapter_List_Call_Remind adapter_list_call_remind;

    private ArrayList<Integer> arrayList = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_remind, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        SharedPreferences prefs = getActivity().getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String cookie = prefs.getString("cookie_name", "No name defined");
        mTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = mEdtSearchCu.getText().toString().trim();
                ApiClient.getInstance().search(info, "search_customer", cookie, "application/x-www-form-urlencoded").enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        int id = response.body().getId();
                        arrayList.add(id);
                        mTvName.setText(response.body().getFullname());
                        mTvEmail.setText("  (" + response.body().getEmail() + ")");
                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {

                    }
                });
            }
        });

        mTvChangeDate.setOnClickListener(new View.OnClickListener() {
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
                                mTvChangeDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mEdtContent.getText().toString().trim();
                long time = convertStringToTimestampMilisecond(mTvChangeDate.getText().toString());
                ApiClient.getInstance().addRemind("add_phone_remind", content, time, arrayList.get(0), cookie).enqueue(new Callback<ModelAddRemind>() {
                    @Override
                    public void onResponse(Call<ModelAddRemind> call, Response<ModelAddRemind> response) {
                        try {
                            Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                            adapter_list_call_remind.notifyDataSetChanged();
//                            getData(cookie);
                            dismiss();
                        } catch (Exception e) {
                            Toast.makeText(context, "" + response.body().getError(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelAddRemind> call, Throwable t) {

                    }
                });
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
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
        mTvChangeDate = view.findViewById(R.id.tv_change_date);
        mTvSearch = view.findViewById(R.id.tv_search);
        mBtnOk = view.findViewById(R.id.btn_ok);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
    }

    public long convertStringToTimestampMilisecond(String string) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime() / 1000;
        return time;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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
