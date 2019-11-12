package com.example.anew.ui.call;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.R;

public class FragmentInfo extends Fragment {


    private TextView mTvFullName;
    private TextView mTvDiaChi;
    private TextView mTvEm;
    private TextView mTvSDT;
    private TextView mTvSky;
    private TextView mTvTaoNhacNho;


    public Fragment newInstance(String name, String address, String phone, String skye, String email, int idCus) {
        FragmentInfo fragmentInfo = new FragmentInfo();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("address", address);
        bundle.putString("phone", phone);
        bundle.putString("skye", skye);
        bundle.putString("email", email);
        bundle.putInt("idCus", idCus);
        fragmentInfo.setArguments(bundle);
        return fragmentInfo;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thong_tin_kh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String address = getArguments().getString("address");
            String email = getArguments().getString("email");
            String phone = getArguments().getString("phone");
            String sky = getArguments().getString("skye");
            int idcus = getArguments().getInt("idCus", 0);

            mTvDiaChi.setText(address.replace("null", ""));
            mTvSky.setText(sky.replace("null", ""));
            mTvFullName.setText(name);
            mTvEm.setText(email);
            mTvSDT.setText(phone);

            mTvTaoNhacNho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putInt("key", idcus);
                    DialogAddRemindInfo newFragment = new DialogAddRemindInfo();
                    newFragment.setArguments(args);
                    newFragment.show(getActivity().getSupportFragmentManager(), "TAG");
                }
            });
        }
    }

    private void initView(View view) {
        mTvFullName = view.findViewById(R.id.tvHoVaTen);
        mTvDiaChi = view.findViewById(R.id.tvDiaChi);
        mTvEm = view.findViewById(R.id.tvEm);
        mTvSDT = view.findViewById(R.id.tvSDT);
        mTvSky = view.findViewById(R.id.tvSky);
        mTvTaoNhacNho = view.findViewById(R.id.tvTaoNhacNho);
    }

}
