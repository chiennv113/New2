package com.example.anew.ui.customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterListCustomer;
import com.example.anew.Model.ModelListCustomer.ModelListCustomerV2;
import com.example.anew.Model.ModelListCustomer.User;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment {

    private AdapterListCustomer adapter_List_Customer;
    private RecyclerView cRv;
    private Spinner spCustomer;
    private ImageView btnSendC;

    private List<User> modelListCustomers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    private void initView(View view) {
        cRv = view.findViewById(R.id.cRv);
        spCustomer = view.findViewById(R.id.spCustomer);
        btnSendC = view.findViewById(R.id.btnSendC);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        cRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter_List_Customer = new AdapterListCustomer(getActivity(), modelListCustomers);
        cRv.setAdapter(adapter_List_Customer);
        ListAllCustomer(cookie,null);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tất cả");
        arrayList.add("Hoạt động ");
        arrayList.add("Khóa");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCustomer.setAdapter(arrayAdapter);

        btnSendC.setOnClickListener(viewl -> {
            if (spCustomer.getSelectedItem().toString().equals("Tất cả")) {
                ListAllCustomer(cookie,null);
            } else if (spCustomer.getSelectedItem().toString().equals("Hoạt động")){
                ListAllCustomer(cookie,1);
            } else if (spCustomer.getSelectedItem().toString().equals("Khóa")){
                ListAllCustomer(cookie,0);
            }


        });
    }

    private void ListAllCustomer(String cookie,Integer active) {
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


}
