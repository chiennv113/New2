package com.example.anew.ui.call;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterCacCuocGoiVoiKH;
import com.example.anew.Model.ModelSearchCu.Phonecall;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentTongHopCuocGoi extends Fragment {

    private AdapterCacCuocGoiVoiKH adapterCacCuocGoiVoiKH;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private RecyclerView mRv;

    private List<Phonecall> phonecalls = new ArrayList<>();

    public Fragment newInstance1(String phone) {
        FragmentTongHopCuocGoi fragmentTongHopCuocGoi = new FragmentTongHopCuocGoi();
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        fragmentTongHopCuocGoi.setArguments(bundle);
        return fragmentTongHopCuocGoi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tong_hop_cuoc_goi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapterCacCuocGoiVoiKH = new AdapterCacCuocGoiVoiKH(getActivity(), phonecalls);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(adapterCacCuocGoiVoiKH);
        if (getArguments() != null) {
            ApiClient.getInstance().search(getArguments().getString("phone"), "search_customer",
                    SharePrefs.getInstance().get(Constans.COOKIE, String.class)).enqueue(new Callback<Search>() {
                @Override
                public void onResponse(Call<Search> call, Response<Search> response) {
                    if (response.body() != null) {
                        phonecalls.addAll(response.body().getPhonecall());
                        adapterCacCuocGoiVoiKH.updateData(response.body().getPhonecall());
                    }
                }

                @Override
                public void onFailure(Call<Search> call, Throwable t) {

                }
            });
        }


    }

    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
    }


}
