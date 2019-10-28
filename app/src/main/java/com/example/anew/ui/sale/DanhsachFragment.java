package com.example.anew.ui.sale;


import android.content.DialogInterface;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.anew.R;

public class DanhsachFragment extends Fragment {

    private ImageView img_filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_danhsach, container, false);
        initView(view);

        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.dialog_filter_danhsach, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setTitle("Filter");
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alert.setPositiveButton("Apply", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // code for matching password

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });


        return view;
    }

    private void initView(View view) {
        img_filter = view.findViewById(R.id.img_filter);

    }


}
