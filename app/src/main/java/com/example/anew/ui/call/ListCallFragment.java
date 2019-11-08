package com.example.anew.ui.call;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anew.Activity.ItemListCallUserActivity;
import com.example.anew.Adapter.AdapterListCallPhone;
import com.example.anew.Model.ModelListPhoneCall.CallList;
import com.example.anew.Model.ModelListPhoneCall.ModelListPhoneCallV2;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.helper.ILoadMore;
import com.example.anew.helper.ItemClickRv;
import com.example.anew.utills.AppScrollListener;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.CustomTouchListener;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCallFragment extends Fragment {

    private RecyclerView mRv;
    private List<CallList> modelListPhoneCalls = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private AdapterListCallPhone adapter_list_call_phone_filter;
    private TextView mTvDateEnd;
    private TextView mTvDateStart;
    private ImageView mImgFilter;
    private Context context;
    private int OFFSET = 0; // Lấy từ vị trí
    private int TAKE = 50; // Lấy về số bản ghi

    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView mBtnSearch;
    private EditText mEdtSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        mBtnSearch.setOnClickListener(v -> {
            if (mEdtSearch.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Chưa nhập thông tin tìm kiếm", Toast.LENGTH_SHORT).show();
            } else {
                showDialogSearch(cookie);
            }
        });
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(linearLayoutManager);

        adapter_list_call_phone_filter = new AdapterListCallPhone(modelListPhoneCalls, getContext(), mRv, new ItemClickRv() {
            @Override
            public void onClickCall(int id, String phone) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                startActivity(callIntent);
            }

            @Override
            public void onItemClick(String phone) {
                ApiClient.getInstance().search(phone, "search_customer", cookie).enqueue(new Callback<Search>() {
                    @Override
                    public void onResponse(Call<Search> call, Response<Search> response) {
                        if (response.body() != null) {
                            Bundle data = new Bundle();
                            Intent intent = new Intent(getContext(), ItemListCallUserActivity.class);
                            data.putString(Constans.NAME, response.body().getFullname());
                            data.putString(Constans.ADDRESS, String.valueOf(response.body().getAddress()));
                            data.putString(Constans.EMAIL, response.body().getEmail());
                            data.putString(Constans.PHONE, response.body().getPhone1());
                            data.putString(Constans.SKYPE, String.valueOf(response.body().getSkype()));
                            intent.putExtras(data);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Search> call, Throwable t) {

                    }
                });
            }
        });
        mRv.setAdapter(adapter_list_call_phone_filter);

        getDateHienTai(cookie);

        adapter_list_call_phone_filter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if (modelListPhoneCalls.size() <= 1000) {
                    modelListPhoneCalls.add(null);
                    adapter_list_call_phone_filter.notifyItemInserted(modelListPhoneCalls.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            modelListPhoneCalls.remove(modelListPhoneCalls.size() - 1);
                            adapter_list_call_phone_filter.notifyItemRemoved(modelListPhoneCalls.size());
                            getListPhoneCall(ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString().trim()),
                                    ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString().trim()), cookie, TAKE, OFFSET);
                            adapter_list_call_phone_filter.notifyDataSetChanged();
                            adapter_list_call_phone_filter.setLoader();
                        }
                    }, 3000);
                }
            }
        });
        @ColorInt int down = getResources().getColor(R.color.white);
        @ColorInt int up = getResources().getColor(R.color.DarkOrange);
        mTvDateStart.setOnTouchListener(new CustomTouchListener(down, up));
        mTvDateEnd.setOnTouchListener(new CustomTouchListener(down, up));

        mTvDateStart.setOnClickListener(view15 -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view14, year, monthOfYear, dayOfMonth) -> mTvDateStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();

        });

        mTvDateEnd.setOnClickListener(view12 -> {
            Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view1, year, monthOfYear, dayOfMonth) -> mTvDateEnd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });


        mImgFilter.setOnClickListener(view13 -> {
            if (mTvDateEnd.getText().toString().equals("") ||
                    mTvDateStart.getText().toString().equals("") ||
                    (mTvDateStart.getText().toString().equals("") && mTvDateEnd.getText().toString().equals(""))) {
                Toast.makeText(getActivity(), "" + getResources().getString(R.string.no_data_entered), Toast.LENGTH_SHORT).show();
            } else {
                long date_start = ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString());
                long date_end = ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString());

                ApiClient.getInstance().getListPhoneCall("ListPhoneCall_v2", date_start, date_end, TAKE, OFFSET, cookie)
                        .enqueue(new Callback<ModelListPhoneCallV2>() {
                            @Override
                            public void onResponse(Call<ModelListPhoneCallV2> call, Response<ModelListPhoneCallV2> response) {
                                modelListPhoneCalls.clear();
                                modelListPhoneCalls.addAll(response.body().getCall());
                                adapter_list_call_phone_filter.notifyDataSetChanged();

                                if (response.body().getCall().size() == 0) {
                                    Toast.makeText(getContext(), "" + getResources().getString(R.string.no_data_in_this_time), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ModelListPhoneCallV2> call, Throwable t) {

                            }
                        });
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            long date_start = ConvertHelper.convertStringToTimestampMilisecond(mTvDateStart.getText().toString());
            long date_end = ConvertHelper.convertStringToTimestampMilisecond(mTvDateEnd.getText().toString());
            getListPhoneCall(date_start, date_end, cookie, TAKE, OFFSET);
            new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
        });

    }


    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mImgFilter = view.findViewById(R.id.img_filter);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        mBtnSearch = view.findViewById(R.id.btnSearch);
        mEdtSearch = view.findViewById(R.id.edtSearch);
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
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), cookie, TAKE, OFFSET);

        } else {
            String startDate = "1/" + realMonth + "/" + year;
            String endDate = "30/" + realMonth + "/" + year;
            getListPhoneCall(ConvertHelper.convertStringToTimestampMilisecond(startDate),
                    ConvertHelper.convertStringToTimestampMilisecond(endDate), cookie, TAKE, OFFSET);
        }
    }

    private void getListPhoneCall(long start, long end, String cookie, int take, int offset) {
        ApiClient.getInstance().getListPhoneCall("ListPhoneCall_v2", start, end, take, offset, cookie)
                .enqueue(new Callback<ModelListPhoneCallV2>() {
                    @Override
                    public void onResponse(Call<ModelListPhoneCallV2> call, Response<ModelListPhoneCallV2> response) {
                        if (response.body() == null) return;
                        modelListPhoneCalls.clear();
                        modelListPhoneCalls.addAll(response.body().getCall());
                        adapter_list_call_phone_filter.updateListCall(response.body().getCall());
                    }

                    @Override
                    public void onFailure(Call<ModelListPhoneCallV2> call, Throwable t) {

                    }
                });
    }

    private void showDialogSearch(String cookie) {
        ApiClient.getInstance().search(mEdtSearch.getText().toString().trim(), "search_customer", cookie).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.body().getFullname() == null && response.body().getPhone1() == null) {
                    Toast.makeText(context, "Thông tin nhập không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle args = new Bundle();
                    args.putString("name", response.body().getFullname());
                    args.putString("phone", response.body().getPhone1());
                    args.putInt("id", response.body().getId());
                    DialogSearchCall newFragment = new DialogSearchCall();
                    newFragment.setArguments(args);
                    newFragment.show(getActivity().getSupportFragmentManager(), "TAG");
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });


    }

//    public class CustomTouchListener implements View.OnTouchListener {
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            switch (motionEvent.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    ((TextView) view).setTextColor(0xFFFFFFFF); //white
//                    break;
//                case MotionEvent.ACTION_CANCEL:
//                case MotionEvent.ACTION_UP:
//                    ((TextView) view).setTextColor(getResources().getColor(R.color.DarkOrange)); //black
//                    break;
//            }
//            return false;
//        }
//    }
}
