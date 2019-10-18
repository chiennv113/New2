package com.example.anew.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.Model.ModelLoadCity;
import com.example.anew.Model.ModelLogin.Login;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mBtnLogin;
    private TextInputEditText mEdtUser;
    private TextInputEditText mEdtPass;
    private List<Login> logins;

    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mEdtPass.setText("123456");
        mEdtUser.setText("app@ninjateam.vn");
        logins = new ArrayList<>();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidation()) {
                    Log.e("user", "onClick: " + email);
                    if (CommonMethod.isNetworkAvailable(LoginActivity.this)) {
                        loginRetrofit2Api(email, password, "login");
                    } else {
                        CommonMethod.showAlert("Internet Connectivity Failure", LoginActivity.this);
                    }

                }
            }
        });

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
                Log.e("abc", "onResponse: " + response.body());
                logins.add(response.body());

                String name = response.body().getData().getName();
                String email = response.body().getData().getEmail();

                String cookie = response.headers().get("Set-Cookie");
                SharedPreferences.Editor editor = getSharedPreferences("cookie", MODE_PRIVATE).edit();
                editor.putString("cookie_name", cookie);
                editor.apply();

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

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }


    public void ForgotPassword(View view) {
    }

    public void SignUpbtn(View view) {
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mEdtUser = findViewById(R.id.edtUser);
        mEdtPass = findViewById(R.id.edtPass);
    }
}
