package com.example.anew.ui.customer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterListCustomer;
import com.example.anew.Model.ModelListCustomer.ModelListCustomerV2;
import com.example.anew.Model.ModelListCustomer.User;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.ui.call.DialogSearchCall;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment {

    private AdapterListCustomer adapter_List_Customer;
    private RecyclerView cRv;
    private Spinner spCustomer;
    private ImageView btnSendC;
    private ImageButton mbtnSearch;
    private Context context;
    private EditText medtSearchC;

    private List<User> modelListCustomers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initView(View view) {
        cRv = view.findViewById(R.id.cRv);
        spCustomer = view.findViewById(R.id.spCustomer);
        btnSendC = view.findViewById(R.id.btnSendC);
        mbtnSearch = view.findViewById(R.id.btnSearch);
        medtSearchC = view.findViewById(R.id.edtSearchC);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        mbtnSearch.setOnClickListener(v -> {
            if (medtSearchC.getText().toString().trim().equals("")) {
                Toast.makeText(context, "Chưa nhập thông tin tìm kiếm", Toast.LENGTH_SHORT).show();
            } else {
                showDialogSearch(cookie);
            }

        });

        cRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_List_Customer = new AdapterListCustomer(getActivity(), modelListCustomers);
        cRv.setAdapter(adapter_List_Customer);
        ListAllCustomer(cookie, null);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tất cả");
        arrayList.add("Hoạt động ");
        arrayList.add("Khóa");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCustomer.setAdapter(arrayAdapter);

        btnSendC.setOnClickListener(viewl -> {
            if (spCustomer.getSelectedItem().toString().equals("Tất cả")) {
                ListAllCustomer(cookie, null);
            } else if (spCustomer.getSelectedItem().toString().equals("Hoạt động")) {
                ListAllCustomer(cookie, 1);
            } else if (spCustomer.getSelectedItem().toString().equals("Khóa")) {
                ListAllCustomer(cookie, 0);
            }


        });
    }

    private void ListAllCustomer(String cookie, Integer active) {
        ApiClient.getInstance().getListCustomer(active, 5, 0, "filter_customer", cookie).enqueue(new Callback<ModelListCustomerV2>() {
            @Override
            public void onResponse(Call<ModelListCustomerV2> call, Response<ModelListCustomerV2> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelListCustomers.addAll(response.body().getUsers());
                    adapter_List_Customer.updateData(response.body().getUsers());
                }
            }

            @Override
            public void onFailure(Call<ModelListCustomerV2> call, Throwable t) {

            }
        });

    }

    private void showDialogSearch(String cookie) {
        ApiClient.getInstance().search(medtSearchC.getText().toString().trim(), "search_customer", cookie).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.body().getFullname() == null && response.body().getPhone1() == null) {

                    Toast.makeText(context, "Thông tin nhập không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle args = new Bundle();
                    args.putString(Constans.NAME, response.body().getFullname());
                    args.putString(Constans.PHONE, response.body().getPhone1());
                    args.putString(Constans.SKYPE, String.valueOf(Objects.requireNonNull(response).body().getSkype()));
                    args.putString(Constans.EMAIL, response.body().getEmail());
                    args.putString(Constans.ADDRESS, String.valueOf(Objects.requireNonNull(response).body().getAddress()));
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


}
