package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.anew.Activity.ItemListCallUserActivity;
import com.example.anew.Adapter.AdapterListCallPhone;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCall;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.ItemClickRv;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCallFragment extends Fragment {

    private RecyclerView mRv;
    private List<ModelListPhoneCall> modelListPhoneCalls = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private AdapterListCallPhone adapter_list_call_phone_filter;
    private TextView mTvDateEnd;
    private TextView mTvDateStart;
    private ImageView mImgFilter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listcall, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        adapter_list_call_phone_filter = new AdapterListCallPhone(modelListPhoneCalls, getContext(), new ItemClickRv() {
            @Override
            public void onClickCall(int id, String phone) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }

            @Override
            public void onItemClick(String phone) {
                Intent intent = new Intent(getContext(), ItemListCallUserActivity.class);
                intent.putExtra(Constans.PASS_PHONE, phone);
                getContext().startActivity(intent);
                ListCallFragment.class.getName();
            }
        });
        mRv.setAdapter(adapter_list_call_phone_filter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(linearLayoutManager);

        getDateHienTai(cookie);
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


        mImgFilter.setOnClickListener(new View.OnClickListener() {
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
        mRv = view.findViewById(R.id.rv);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mImgFilter = view.findViewById(R.id.img_filter);
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
            getListPhoneCall(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), cookie);
        } else {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "30/" + realMonth + "/" + year;
            getListPhoneCall(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), cookie);
        }


    }

    private void getListPhoneCall(long start, long end, String cookie) {
        ApiClient.getInstance().getListPhoneCall("ListPhoneCall", start, end, cookie).enqueue(new Callback<List<ModelListPhoneCall>>() {
            @Override
            public void onResponse(Call<List<ModelListPhoneCall>> call, Response<List<ModelListPhoneCall>> response) {
                modelListPhoneCalls.clear();
                modelListPhoneCalls.addAll(response.body());
                adapter_list_call_phone_filter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelListPhoneCall>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(context, "123456", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(context, "abcde", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
