package com.example.anew.ui.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterListCustomer;
import com.example.anew.Adapter.AdapterListProduct;
import com.example.anew.Model.ModelListCustomer.ModelListCustomer;
import com.example.anew.Model.ModelListCustomer.User;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment {

    private AdapterListCustomer adapter_List_Customer;
    private RecyclerView cRv;


    private List<User> modelListCustomers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    private void initView(View view) {
        cRv = view.findViewById(R.id.cRv);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        adapter_List_Customer = new AdapterListCustomer(getActivity(), modelListCustomers);
        cRv.setAdapter(adapter_List_Customer);
        cRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListAllCustomer(cookie);
        adapter_List_Customer.notifyDataSetChanged();


    }

    private void ListAllCustomer(String cookie) {

        ApiClient.getInstance().getListCustomer(1, 5, 0, "filter_customer", cookie).enqueue(new Callback<ModelListCustomer>() {
            @Override
            public void onResponse(Call<ModelListCustomer> call, Response<ModelListCustomer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelListCustomers.addAll(response.body().getUsers());
                    adapter_List_Customer.updateData(response.body().getUsers());
                }
            }

            @Override
            public void onFailure(Call<ModelListCustomer> call, Throwable t) {

            }
        });

    }


}
