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


    String email;
    String password;
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mEdtPass.setText("123456");
        mEdtUser.setText("app@ninjateam.vn");
        logins = new ArrayList<>();

        mProgress = new ProgressDialog(LoginActivity.this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        mBtnLogin.setOnClickListener(view -> {

            mProgress.show();

            if (checkValidation()) {
                Log.e("user", "onClick: " + email);
                if (CommonMethod.isNetworkAvailable(LoginActivity.this)) {
                    loginRetrofit2Api(email, password, "login");
                } else {
                    CommonMethod.showAlert("Internet Connectivity Failure", LoginActivity.this);
                }

            }
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

    private boolean checkValidation() {
        email = mEdtUser.getText().toString();
        password = mEdtPass.getText().toString();

        if (mEdtUser.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("UserId Cannot be left blank", LoginActivity.this);
            return false;
        } else if (mEdtPass.getText().toString().trim().equals("")) {
            CommonMethod.showAlert("password Cannot be left blank", LoginActivity.this);
            return false;
        }

        return true;
    }

    private void loginRetrofit2Api(final String email, final String password, String option) {
        ApiClient.getInstance().createUser(new Login(email, password, option)).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.code() == Constans.SERVER_SUCCESS && response.body() != null) {
                    logins.add(response.body());
                    String name = response.body().getData().getName();
                    String email = response.body().getData().getEmail();

                    SharePrefs.getInstance().put(Constans.COOKIE, response.headers().get("Set-Cookie"));

                    if (response.body().getMessage().equals("Đăng nhập thành công")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("email", email);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    String aaa = logins.get(0).getMessage();
                    Toast.makeText(LoginActivity.this, "" + aaa, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mEdtUser = findViewById(R.id.edtUser);
        mEdtPass = findViewById(R.id.edtPass);
    }
}
