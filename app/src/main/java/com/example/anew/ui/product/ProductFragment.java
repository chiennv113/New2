package com.example.anew.ui.product;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.anew.Activity.AddCallActivity;
import com.example.anew.Adapter.AdapterListCallRemind;
import com.example.anew.Adapter.AdapterListProduct;
import com.example.anew.Model.ModelAddRemind;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment {

    private AdapterListProduct adapter_list_product;
    private RecyclerView pRv;


    private List<ModelLoadAllProduct> modelLoadAllProducts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

    private void initView(View view) {
        pRv = view.findViewById(R.id.pRv);
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        adapter_list_product = new AdapterListProduct(getActivity(), modelLoadAllProducts);
        pRv.setAdapter(adapter_list_product);
        pRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        LoadAllProduct(cookie);
        adapter_list_product.notifyDataSetChanged();


    }


    private void LoadAllProduct(String cookie) {
        ApiClient.getInstance().getAllProduct("get_allProduct", cookie).enqueue(new Callback<List<ModelLoadAllProduct>>() {
            @Override
            public void onResponse(Call<List<ModelLoadAllProduct>> call, Response<List<ModelLoadAllProduct>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    modelLoadAllProducts.addAll(response.body());
                    adapter_list_product.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadAllProduct>> call, Throwable t) {

            }
        });
    }
}
