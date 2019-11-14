package com.example.anew.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.anew.Activity.LoginActivity;
import com.example.anew.R;
import com.example.anew.utills.SharePrefs;

public class logout extends Fragment {

    private Button mBtnLogOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mBtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                SharePrefs.getInstance().clearCookie();
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        mBtnLogOut = view.findViewById(R.id.btnLogOut);
    }
}
