package com.example.anew.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.anew.Model.ModelLogin.Login;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 200;
    private Button mBtnLogin;
    private EditText mEdtUser;
    private EditText mEdtPass;
    private List<Login> logins;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        logins = new ArrayList<>();

        mProgress = new ProgressDialog(LoginActivity.this);
        mProgress.setTitle("Đang đăng nhập...");
        mProgress.setMessage("Vui lòng đợi...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mBtnLogin.setOnClickListener(view -> {

            mProgress.show();

            ApiClient.getInstance().createUser(mEdtUser.getText().toString().trim(),
                    mEdtPass.getText().toString().trim(),
                    "login").enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.code() == Constans.SERVER_SUCCESS && response.body() != null) {
                        SharePrefs.getInstance().put(Constans.COOKIE, response.headers().get("Set-Cookie"));
                        if (response.body().getMessage().equals("Đăng nhập thành công")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("name", response.body().getData().getName());
                            bundle.putString("email", response.body().getData().getEmail());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgress.dismiss();
                        } else {
                            Toast.makeText(LoginActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgress.dismiss();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        });

        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.CALL_PHONE)) {
            } else {
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mEdtUser = findViewById(R.id.edtUser);
        mEdtPass = findViewById(R.id.edtPass);
    }
}
