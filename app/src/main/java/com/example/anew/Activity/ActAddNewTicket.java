package com.example.anew.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_add_new_ticket);
        initView();
        String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("GGG", "onActivityResult: " + file);
            }
        });

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
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    Log.e("GGG", "onActivityResult uri:  " + selectedImage);
                    linkImage = String.valueOf(selectedImage);
                    Log.e("GGG", "onActivityResult link : " + linkImage);
                    mImgSelectedInAddNewTicket.setImageURI(selectedImage);
                    file = new File(getPath(selectedImage));
                    Log.e("GGG", "onActivityResult File: " + file);

//                  content://storage/emulated/0/DCIM/Camera/IMG_20191120_093806.jpg
                }
                break;
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
