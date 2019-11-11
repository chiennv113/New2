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
import com.example.anew.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentTongHopCuocGoi extends Fragment {

    private AdapterCacCuocGoiVoiKH adapterCacCuocGoiVoiKH;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private RecyclerView mRv;

    private List<String> abc = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_call_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        for (int i = 0; i < 30; i++) {
            abc.add(i, "abc: " + i);
        }

        adapterCacCuocGoiVoiKH = new AdapterCacCuocGoiVoiKH(getActivity(), abc);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setAdapter(adapterCacCuocGoiVoiKH);
        adapterCacCuocGoiVoiKH.notifyDataSetChanged();
    }

    private void initView(View view) {
        mRv = view.findViewById(R.id.rv);
    }
}
