package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.Adapter_List_Call_Phone_Filter;
import com.example.anew.Model.ModelDeleteCall;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.ItemClickRv;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

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

public class ListCallFragment extends Fragment {

    private EditText mEdtInfoSearch;
    private ImageView mBtnSearch;
    private TextView mTvDateStart;
    private TextView mTvDateEnd;
    private RecyclerView mRv;
    private Button mBtnFiler;

    private List<ModelListPhoneCall> modelListPhoneCalls = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Adapter_List_Call_Phone_Filter adapter_list_call_phone_filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listcall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        adapter_list_call_phone_filter = new Adapter_List_Call_Phone_Filter(modelListPhoneCalls, getContext(), new ItemClickRv() {
            @Override
            public void onItemClick(final int position) {
                ApiClient.getInstance().del("DeleteCall", modelListPhoneCalls.get(position).getId(), cookie).enqueue(new Callback<ModelDeleteCall>() {
                    @Override
                    public void onResponse(Call<ModelDeleteCall> call, Response<ModelDeleteCall> response) {
                        Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        modelListPhoneCalls.remove(position);
                        adapter_list_call_phone_filter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ModelDeleteCall> call, Throwable t) {

                    }
                });

            }
        });
        mRv.setAdapter(adapter_list_call_phone_filter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(linearLayoutManager);

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


        mBtnFiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTvDateEnd.getText().toString().equals("") ||
                        mTvDateStart.getText().toString().equals("") ||
                        (mTvDateStart.getText().toString().equals("") && mTvDateEnd.getText().toString().equals(""))) {
                    Toast.makeText(getActivity(), "" + getResources().getString(R.string.no_data_entered), Toast.LENGTH_SHORT).show();
                } else {
                    long date_start = ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString());
                    long date_end = ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString());

                    ApiClient.getInstance().getListPhoneCall("ListPhoneCall", date_start, date_end, cookie).enqueue(new Callback<List<ModelListPhoneCall>>() {
                        @Override
                        public void onResponse(Call<List<ModelListPhoneCall>> call, Response<List<ModelListPhoneCall>> response) {
                            modelListPhoneCalls.clear();
                            modelListPhoneCalls.addAll(response.body());
                            adapter_list_call_phone_filter.notifyDataSetChanged();

                            if (response.body().size() == 0) {
                                Toast.makeText(getContext(), "" + getResources().getString(R.string.no_data_in_this_time), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ModelListPhoneCall>> call, Throwable t) {
                        }
                    });
                }
            }
        });

    }

    private void initView(View view) {
        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mRv = view.findViewById(R.id.rv);
        mBtnFiler = view.findViewById(R.id.btn_filer);
    }


}
