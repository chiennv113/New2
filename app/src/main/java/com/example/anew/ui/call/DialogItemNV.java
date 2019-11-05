package com.example.anew.ui.call;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anew.Adapter.AdapterItemNV;
import com.example.anew.Model.ModelTKTheoNV.ModelThongKeTheoNVAdmin;
import com.example.anew.R;
import com.example.anew.helper.IClickShowDialogNV;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.*;

public class DialogItemNV extends DialogFragment {

    private Context context;
    private RecyclerView mRvDialog;

    IClickShowDialogNV iClickShowDialogNV;
    private LinearLayoutManager linearLayoutManager;
    AdapterItemNV adapterItemNV;
    private List<ModelThongKeTheoNVAdmin> modelThongKeTheoNVAdmin = new ArrayList<>();

    void setOnClickPositive(IClickShowDialogNV iDialogClick) {
        this.iClickShowDialogNV = iDialogClick;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_item_nv, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        linearLayoutManager = new LinearLayoutManager(getContext());
        mRvDialog.setLayoutManager(linearLayoutManager);
        adapterItemNV = new AdapterItemNV(modelThongKeTheoNVAdmin, getDialog().getContext());
        mRvDialog.setAdapter(adapterItemNV);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void initView(View view) {
        mRvDialog = view.findViewById(R.id.rvDialog);
    }

    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(CENTER);
        super.onResume();

    }
}
