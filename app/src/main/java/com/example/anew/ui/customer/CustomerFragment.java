package com.example.anew.ui.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterListCustomer;
import com.example.anew.Model.ModelListCustomer.ModelListCustomer;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {

    private AdapterListCustomer adapter_List_Customer;
    private RecyclerView cRv;


    private List<ModelListCustomer> modelListCustomers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer, container, false);
        return view;
    }

    private void initView(View view){
        cRv = view.findViewById(R.id.cRv);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
