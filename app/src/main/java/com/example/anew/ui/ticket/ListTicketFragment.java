package com.example.anew.ui.ticket;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.R;
import com.example.anew.utills.SelectDate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ListTicketFragment extends Fragment {

    private FloatingActionButton fab;
    private ImageView mImgFilter;
    private TextView mTvDateStart;
    private TextView mTvDateEnd;
    private RecyclerView mRv;
    private FloatingActionButton mBtnAddTicket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        mTvDateStart.setOnClickListener(view15 -> {
            SelectDate.select(getContext(), mTvDateStart);
        });

        mTvDateEnd.setOnClickListener(view12 -> {
            SelectDate.select(getContext(), mTvDateEnd);
        });
    }

    private void initView(View view) {
        mImgFilter = view.findViewById(R.id.imgFilter);
        mTvDateStart = view.findViewById(R.id.tvDateStart);
        mTvDateEnd = view.findViewById(R.id.tvDateEnd);
        mRv = view.findViewById(R.id.rv);
        mBtnAddTicket = view.findViewById(R.id.btn_add_ticket);
    }
}
