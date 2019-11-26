package com.example.anew.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.Model.ModelAddNewTicket;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.Retrofit.ServiceRetrofit;
import com.example.anew.utills.Constans;
import com.example.anew.utills.ConvertHelper;
import com.example.anew.utills.SharePrefs;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private TextView mTvNameRsSearch;
    private TextView mTvEmailRsSearch;

    SpinnerDialog spinnerDialogSelectType;
    SpinnerDialog spinnerDialogSelectStatus;
    SpinnerDialog spinnerDialogSelectProduct;

    private String linkImage;
    private File file;

    ArrayList<Integer> user_id;

    private static final int IMAGE = 100;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_new_ticket);
        initView();
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);


        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mImgSearchKHInAddNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchCustomer(cookie);
            }
        });

        mImgSelectTypeInAddnewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectType();
            }
        });

        mImgSelectStatusInAddNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectStatus();
            }
        });

        mImgSelectProductinAddNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectProduct(cookie);
            }
        });

        mTvSelectImgInAddNewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvTypeInAddNewTicket.getText().toString().trim().equals("")
                        || mTvStatusInAddNewTicket.getText().toString().trim().equals("")
                        || mTvSelectProductInAddNewTicket.getText().toString().trim().equals("")
                        || mTvNoteNVInAddNewTicket.getText().toString().trim().equals("")
                        || mTvTieuDeInAddNewTicket.getText().toString().trim().equals("")
                        || mTvContentInAddNewTicket.getText().toString().trim().equals("")) {
                    Toast.makeText(ActAddNewTicket.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    int type = 0;
                    String getType = mTvTypeInAddNewTicket.getText().toString().trim();
                    switch (getType) {
                        case "Hướng dẫn":
                            type = 1;
                            break;
                        case "Hỗ trợ":
                            type = 2;
                            break;
                        case "Cấp bản quyền":
                            type = 3;
                            break;
                        case "Thông tin đăng nhập":
                            type = 4;
                            break;
                        case "Nội dung khác":
                            type = 5;
                            break;
                    }

                    int ticketCondition = 0;
                    String getTicketCondition = mTvStatusInAddNewTicket.getText().toString().trim();
                    switch (getTicketCondition) {
                        case "Bình thường":
                            ticketCondition = 1;
                            break;
                        case "Gấp":
                            ticketCondition = 2;
                            break;
                        case "Khẩn cấp":
                            ticketCondition = 3;
                            break;
                    }

                    int product = 0;
                    String getProduct = mTvSelectProductInAddNewTicket.getText().toString().trim();
                    switch (getProduct) {
                        case "Phần mềm Facebook Ninja":
                            product = 11;
                            break;
                        case "Phần mềm Ninja Care":
                            product = 12;
                            break;
                        case "Phần mềm Ninja Add Friend":
                            product = 13;
                            break;
                        case "Phần mềm Ninja Add Mem Group":
                            product = 14;
                            break;
                        case "Phần mềm Ninja Share Live Stream":
                            product = 15;
                            break;
                        case "Phần mềm Ninja Scan UID":
                            product = 16;
                            break;
                        case "Phần mềm Ninja Instagram":
                            product = 17;
                            break;
                        case "Phần mềm Ninja group":
                            product = 18;
                            break;
                        case "Phần mềm Ninja System":
                            product = 19;
                            break;
                        case "Phần mềm Ninja Comment":
                            product = 20;
                            break;
                        case "Phần mềm Ninja Auto Post":
                            product = 21;
                            break;
                        case "Phần mềm Ninja Shopee":
                            product = 22;
                            break;
                        case "Phần mềm Ninja Fanpage":
                            product = 23;
                            break;
                        case "Phần mềm Ninja Zalo":
                            product = 24;
                            break;
                        case "Phần mềm Quét UID Online":
                            product = 25;
                            break;
                    }

                    ApiClient.getInstance().addTicket(mTvTieuDeInAddNewTicket.getText().toString(), type, ticketCondition,
                            mTvContentInAddNewTicket.getText().toString().trim(),
                            mTvNoteNVInAddNewTicket.getText().toString().trim(), user_id.get(0), product,
                            "sendTicketEmployees", null, cookie)
                            .enqueue(new Callback<ModelAddNewTicket>() {
                                @Override
                                public void onResponse(Call<ModelAddNewTicket> call, Response<ModelAddNewTicket> response) {
                                    Log.e("GGG", "onResponse: " + response.code());
                                    Toast.makeText(ActAddNewTicket.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<ModelAddNewTicket> call, Throwable t) {

                                }
                            });

                }
            }
        });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE);
    }

    private String convertToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                mImgSelectedInAddNewTicket.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
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
        mTvNameRsSearch = findViewById(R.id.tvNameRsSearch);
        mTvEmailRsSearch = findViewById(R.id.tvEmailRsSearch);
    }

    private void SearchCustomer(String cookie) {
        String info = mTvTenKhInAddNewTicket.getText().toString().trim();
        ApiClient.getInstance().search(info, "search_customer", cookie).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                try {
                    mTvNameRsSearch.setText(response.body().getFullname());
                    mTvEmailRsSearch.setText("  (" + response.body().getEmail() + ")");
                    user_id = new ArrayList<>();
                    user_id.add(0, response.body().getId());
                } catch (Exception e) {
                    if (info.equals("")) {
                        Toast.makeText(ActAddNewTicket.this, "" + "Chưa nhập thông tin tìm kiếm", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActAddNewTicket.this, "" + "Thông tin nhập không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    private void SelectType() {
        ArrayList<String> typeList = new ArrayList<>();
        typeList.add(0, "Hướng dẫn");
        typeList.add(1, "Hỗ trợ");
        typeList.add(2, "Cấp bản quyền");
        typeList.add(3, "Thông tin đăng nhập");
        typeList.add(4, "Nội dung khác");
        spinnerDialogSelectType = new SpinnerDialog(ActAddNewTicket.this, typeList, "Select city");
        spinnerDialogSelectType.bindOnSpinerListener((item, position) -> mTvTypeInAddNewTicket.setText(item));
        spinnerDialogSelectType.showSpinerDialog();
    }

    private void SelectStatus() {
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add(0, "Bình thường");
        statusList.add(1, "Gấp");
        statusList.add(2, "Khẩn cấp");
        spinnerDialogSelectStatus = new SpinnerDialog(ActAddNewTicket.this, statusList, "Select city");
        spinnerDialogSelectStatus.bindOnSpinerListener((item, position) -> mTvStatusInAddNewTicket.setText(item));
        spinnerDialogSelectStatus.showSpinerDialog();
    }

    private void SelectProduct(String cookie) {
        ApiClient.getInstance().getAllProduct("get_allProduct", cookie).enqueue(new Callback<List<ModelLoadAllProduct>>() {
            @Override
            public void onResponse(Call<List<ModelLoadAllProduct>> call, Response<List<ModelLoadAllProduct>> response) {
                ArrayList<String> proDuctList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        proDuctList.add(response.body().get(i).getName());
                    }
                    spinnerDialogSelectProduct = new SpinnerDialog(ActAddNewTicket.this, proDuctList, "Select city");
                    spinnerDialogSelectProduct.bindOnSpinerListener((item, position) -> mTvSelectProductInAddNewTicket.setText(item));
                    spinnerDialogSelectProduct.showSpinerDialog();
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadAllProduct>> call, Throwable t) {

            }
        });
    }
}
