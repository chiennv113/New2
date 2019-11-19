package com.example.anew.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.Model.ModelViewTicketInDS.ModelViewTicketInDS;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActViewTicketUnAccept extends AppCompatActivity {

    private TextView mTvFullNameInViewTicket;
    private TextView mTvEmaiInViewTicket;
    private TextView mTvPhoneInViewTicket;
    private ImageView mImgSelectedInViewTicket;
    private Button mBtnSendInViewTicket;
    private EditText mEdtReply;
    private TextView mTvSelectImgInViewTicket;
    private ImageView mImgInViewTicket;
    private TextView mTvUserTaoTicketInViewTicket;
    private TextView mTvContentInViewTicket;
    private TextView mTvTittleInViewTicket;
    private TextView mTvCreateTimeInViewTicket;
    private TextView mTvNoteInViewTicket;
    private ImageView mImgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_view_ticket_un_accept);
        initView();
        Intent intent = getIntent();
        int idTicket = intent.getIntExtra(Constans.ID_TICKET, 0);
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ApiClient.getInstance().getView(idTicket, "view_unaccept_ticket", cookie).enqueue(new Callback<ModelViewTicketInDS>() {
            @Override
            public void onResponse(Call<ModelViewTicketInDS> call, Response<ModelViewTicketInDS> response) {
                mTvFullNameInViewTicket.setText(response.body().getData().getUsers().getFullname());
                mTvEmaiInViewTicket.setText(response.body().getData().getUsers().getEmail());
                mTvPhoneInViewTicket.setText(response.body().getData().getUsers().getPhone1());
                mTvTittleInViewTicket.setText(response.body().getData().getTitle());
                mTvUserTaoTicketInViewTicket.setText(response.body().getData().getUsers().getFullname() + " Đã tạo ticket " + "#" + idTicket);

                String content1 = (response.body().getData().getContents()).replace("<p>", "");
                String content = content1.replace("</p>", "");
                mTvContentInViewTicket.setText(content);

                String note1 = (response.body().getData().getNote()).replace("<p>", "");
                String note = note1.replace("</p>", "");
                mTvNoteInViewTicket.setText(note);

                Date d = new Date((long) response.body().getData().getCreateTime() * 1000);
                DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                mTvCreateTimeInViewTicket.setText(f.format(d));

                if (response.body().getData().getImages() != null) {
                    String imgLoad = response.body().getData().getImages();
                    String imgReal = imgLoad.replace("\\", "");
                    String imgReal1 = imgReal.replace("[\"", "");
                    String imgReal2 = imgReal1.replace("\"]", "");
                    String domain = "https://crm.phanmemninja.com/";
                    String imgReal3 = domain + imgReal2;
                    Picasso.get().load(imgReal3).into(mImgInViewTicket);
                    Log.e("GGG", "Kết quả: " + imgReal3);
                }
            }

            @Override
            public void onFailure(Call<ModelViewTicketInDS> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mTvFullNameInViewTicket = findViewById(R.id.tvFullNameInViewTicket);
        mTvEmaiInViewTicket = findViewById(R.id.tvEmaiInViewTicket);
        mTvPhoneInViewTicket = findViewById(R.id.tvPhoneInViewTicket);
        mImgSelectedInViewTicket = findViewById(R.id.imgSelectedInViewTicket);
        mBtnSendInViewTicket = findViewById(R.id.btnSendInViewTicket);
        mEdtReply = findViewById(R.id.edtReply);
        mTvSelectImgInViewTicket = findViewById(R.id.tvSelectImgInViewTicket);
        mImgInViewTicket = findViewById(R.id.imgInViewTicket);
        mTvUserTaoTicketInViewTicket = findViewById(R.id.tvUserTaoTicketInViewTicket);
        mTvContentInViewTicket = findViewById(R.id.tvContentInViewTicket);
        mTvTittleInViewTicket = findViewById(R.id.tvTittleInViewTicket);
        mTvCreateTimeInViewTicket = findViewById(R.id.tvCreateTimeInViewTicket);
        mTvNoteInViewTicket = findViewById(R.id.tvNoteInViewTicket);
        mImgBack = findViewById(R.id.imgBack);
    }
}
