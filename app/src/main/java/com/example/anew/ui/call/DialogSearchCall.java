package com.example.anew.ui.call;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.anew.Activity.ItemListCallUserActivity;
import com.example.anew.Model.ModelAdd;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.CustomTouchListener;
import com.example.anew.utills.SharePrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSearchCall extends DialogFragment {
    private TextView mTvSđt;
    private TextView mTvName;
    private TextView mTvAddCall;
    private TextView mTvCancel;
    private ConstraintLayout mItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_result_search, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


        Bundle mArgs = getArguments();
        String name = mArgs.getString(Constans.NAME);
        String phone = mArgs.getString(Constans.PHONE);
        final String skype = mArgs.getString(Constans.SKYPE);
        final String address = mArgs.getString(Constans.ADDRESS);
        final String email = mArgs.getString(Constans.EMAIL);
        int id = mArgs.getInt("id");
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        mTvSđt.setText(phone);
        mTvName.setText(name);
        mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemListCallUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constans.NAME, mTvName.getText().toString().trim());
                bundle.putString(Constans.PHONE, mTvSđt.getText().toString().trim());
                bundle.putString(Constans.SKYPE, skype);
                bundle.putString(Constans.ADDRESS, address);
                bundle.putString(Constans.EMAIL, email);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        @ColorInt int down = getResources().getColor(R.color.white);
        @ColorInt int up = getResources().getColor(R.color.DarkOrange);
        mTvCancel.setOnTouchListener(new CustomTouchListener(down, up));
        mTvAddCall.setOnTouchListener(new CustomTouchListener(down, up));

        mTvCancel.setOnClickListener(v -> dismiss());

        mTvAddCall.setOnClickListener(v -> ApiClient.getInstance().add("add_phone_call", id, "khách cần mua", "Rất hài lòng", cookie).enqueue(new Callback<ModelAdd>() {
            @Override
            public void onResponse(Call<ModelAdd> call, Response<ModelAdd> response) {
                Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                dismiss();
            }

            @Override
            public void onFailure(Call<ModelAdd> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));

    }

    private void initView(View view) {
        mTvSđt = view.findViewById(R.id.tvSđt);
        mTvName = view.findViewById(R.id.tvNameC);
        mTvAddCall = view.findViewById(R.id.tvAddCall);
        mTvCancel = view.findViewById(R.id.tvCancel);
        mItem = view.findViewById(R.id.item);
    }

    public void onResume() {
        Window window = getDialog().getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        window.setLayout((int) (size.x * 0.85), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        super.onResume();

    }
}
