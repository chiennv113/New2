package com.example.anew.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.R;
import com.google.android.material.textfield.TextInputEditText;

public class ActAddNewTicket extends AppCompatActivity {

    private ImageView mBtnCancel;
    private TextView mBtnSave;
    private ImageView mImgSelectProductinAddNewTicket;
    private TextView mTvTypeInAddNewTicket;
    private TextView mTvStatusInAddNewTicket;
    private TextView mTvSelectProductInAddNewTicket;
    private ImageView mImgSelectedInAddNewTicket;
    private TextView mTvSelectImgInAddNewTicket;
    private TextInputEditText mTvNoteNVInAddNewTicket;
    private ImageView mImgSelectTypeInAddnewTicket;
    private ImageView mImgSelectStatusInAddNewTicket;
    private TextInputEditText mTvTieuDeInAddNewTicket;
    private ImageView mImgSearchKHInAddNewTicket;
    private TextInputEditText mTvTenKhInAddNewTicket;
    private TextInputEditText mTvContentInAddNewTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_new_ticket);
        initView();

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mBtnCancel = findViewById(R.id.btnCancel);
        mBtnSave = findViewById(R.id.btnSave);
        mImgSelectProductinAddNewTicket = findViewById(R.id.imgSelectProductinAddNewTicket);
        mTvTypeInAddNewTicket = findViewById(R.id.tvTypeInAddNewTicket);
        mTvStatusInAddNewTicket = findViewById(R.id.tvStatusInAddNewTicket);
        mTvSelectProductInAddNewTicket = findViewById(R.id.tvSelectProductInAddNewTicket);
        mImgSelectedInAddNewTicket = findViewById(R.id.imgSelectedInAddNewTicket);
        mTvSelectImgInAddNewTicket = findViewById(R.id.tvSelectImgInAddNewTicket);
        mTvNoteNVInAddNewTicket = findViewById(R.id.tvNoteNVInAddNewTicket);
        mImgSelectTypeInAddnewTicket = findViewById(R.id.imgSelectTypeInAddnewTicket);
        mImgSelectStatusInAddNewTicket = findViewById(R.id.imgSelectStatusInAddNewTicket);
        mTvTieuDeInAddNewTicket = findViewById(R.id.tvTieuDeInAddNewTicket);
        mImgSearchKHInAddNewTicket = findViewById(R.id.imgSearchKHInAddNewTicket);
        mTvTenKhInAddNewTicket = findViewById(R.id.tvTenKhInAddNewTicket);
        mTvContentInAddNewTicket = findViewById(R.id.tvContentInAddNewTicket);
    }
}
