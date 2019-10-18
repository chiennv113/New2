package com.example.anew.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.Model.ModelAddCallAndCustomerNew;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.Model.ModelLoadCity;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.Model.ModelLoadObjCustomer;
import com.example.anew.Model.ModelLoadSourceCustomer;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCallActivity extends AppCompatActivity {

    private TextInputEditText mEdtPhone;
    private TextInputEditText mEdtName;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtZalo;
    private TextInputEditText mEdtSkype;
    private Spinner mLayoutCity;
    private TextInputEditText mEdtAddress;
    private TextInputEditText mEdtDateOfBirth;
    private Spinner mLayoutSoftWareCare;
    private Spinner mLayoutObjCustome;
    private Spinner mLayoutSourceCustomer;
    private Spinner mLayoutCustomerType;
    private TextInputEditText mEdtContent;
    private Spinner mLayoutCustomerStatus;
    private TextInputEditText mEdtNote;
    private Button mBtnSave;
    private TextInputLayout mLayoutPhone;
    private TextInputLayout mLayoutContent;
    private TextInputLayout mLayoutName;
    private TextInputLayout mLayoutEmail;
    private TextInputLayout mLayoutNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        initView();

        SharedPreferences prefs = getSharedPreferences("cookie", Context.MODE_PRIVATE);
        final String cookie = prefs.getString("cookie_name", "No name defined");

        LoadCity(cookie);
        LoadAllProduct(cookie);
        LoadCustomerType(cookie);
        LoadObjCustomer(cookie);
        LoadSourceCustomer(cookie);
        LoadCustomerFeel();

        mEdtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mLayoutPhone.setError(getResources().getString(R.string.act_add_layout_do_not_leave_blank));
                } else {
                    mLayoutPhone.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mLayoutContent.setError(getResources().getString(R.string.act_add_layout_do_not_leave_blank));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mEdtNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mLayoutNote.setError(getResources().getString(R.string.act_add_layout_do_not_leave_blank));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mLayoutName.setError(getResources().getString(R.string.act_add_layout_do_not_leave_blank));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mEdtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mLayoutEmail.setError(getResources().getString(R.string.act_add_layout_do_not_leave_blank));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = mEdtPhone.getText().toString().trim();
                String address = mEdtAddress.getText().toString().trim();
                String birthday = mEdtDateOfBirth.getText().toString().trim();
                String content = mEdtContent.getText().toString().trim();
                String email = mEdtEmail.getText().toString().trim();
                String fullname = mEdtName.getText().toString().trim();
                String note = mEdtNote.getText().toString().trim();
                String skype = mEdtSkype.getText().toString().trim();
                String zalo = mEdtZalo.getText().toString().trim();

                Log.e("TAG", "onClick: " + phone);

                String city = mLayoutCity.getSelectedItem().toString();
                Log.e("TAG", "onClick: " + phone);
                String product = mLayoutSoftWareCare.getSelectedItem().toString();
                Log.e("TAG", "onClick: " + product);
                String cus_type = mLayoutCustomerType.getSelectedItem().toString();
                String obj_cus = mLayoutObjCustome.getSelectedItem().toString();
                String source_cus = mLayoutSourceCustomer.getSelectedItem().toString();
                String cus_feel = mLayoutCustomerStatus.getSelectedItem().toString();
                ApiClient.getInstance().addCallAndCus("add_register_phone_call",
                        phone,
                        address,
                        birthday,
                        city,
                        content,
                        source_cus,
                        cus_feel,
                        obj_cus,
                        email,
                        fullname,
                        note,
                        skype,
                        product,
                        cus_type,
                        zalo,
                        cookie).
                        enqueue(new Callback<ModelAddCallAndCustomerNew>() {
                            @Override
                            public void onResponse(Call<ModelAddCallAndCustomerNew> call, Response<ModelAddCallAndCustomerNew> response) {
                                if (response.body().getMessage() != null && response.body().getMessage().equals("Đã thêm thành công")) {
                                    Toast.makeText(AddCallActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(AddCallActivity.this, "" + response.body().getError(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ModelAddCallAndCustomerNew> call, Throwable t) {

                            }
                        });


            }
        });
    }


    private void initView() {

        mEdtPhone = findViewById(R.id.edt_phone);
        mEdtName = findViewById(R.id.edt_name);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtZalo = findViewById(R.id.edt_zalo);
        mEdtSkype = findViewById(R.id.edt_skype);
        mLayoutCity = findViewById(R.id.layoutCity);
        mEdtAddress = findViewById(R.id.edt_address);
        mEdtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        mLayoutSoftWareCare = findViewById(R.id.layoutSoftWareCare);
        mLayoutObjCustome = findViewById(R.id.layoutObjCustome);
        mLayoutSourceCustomer = findViewById(R.id.layoutSourceCustomer);
        mLayoutCustomerType = findViewById(R.id.layoutCustomerType);
        mEdtContent = findViewById(R.id.edt_content);
        mLayoutCustomerStatus = findViewById(R.id.layoutCustomerStatus);
        mEdtNote = findViewById(R.id.edt_note);
        mBtnSave = findViewById(R.id.btnSave);
        mLayoutPhone = findViewById(R.id.layoutPhone);
        mLayoutContent = findViewById(R.id.layoutContent);
        mLayoutName = findViewById(R.id.layoutName);
        mLayoutEmail = findViewById(R.id.layoutEmail);
        mLayoutNote = findViewById(R.id.layoutNote);
    }

    private void LoadCity(String cookie) {
        ApiClient.getInstance().getCity("load_city", cookie).enqueue(new Callback<List<ModelLoadCity>>() {
            @Override
            public void onResponse(Call<List<ModelLoadCity>> call, Response<List<ModelLoadCity>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutCity.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadCity>> call, Throwable t) {

            }
        });
    }

    private void LoadCustomerType(String cookie) {
        ApiClient.getInstance().getCustomerType("get_CustomerType", cookie).enqueue(new Callback<List<ModelLoadCustomerType>>() {
            @Override
            public void onResponse(Call<List<ModelLoadCustomerType>> call, Response<List<ModelLoadCustomerType>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutCustomerType.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadCustomerType>> call, Throwable t) {

            }
        });
    }

    private void LoadObjCustomer(String cookie) {
        ApiClient.getInstance().getObjCustomer("get_customer_type", cookie).enqueue(new Callback<List<ModelLoadObjCustomer>>() {
            @Override
            public void onResponse(Call<List<ModelLoadObjCustomer>> call, Response<List<ModelLoadObjCustomer>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutObjCustome.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadObjCustomer>> call, Throwable t) {

            }
        });
    }

    private void LoadSourceCustomer(String cookie) {
        ApiClient.getInstance().getSourceCustomer("get_customer_base", cookie).enqueue(new Callback<List<ModelLoadSourceCustomer>>() {
            @Override
            public void onResponse(Call<List<ModelLoadSourceCustomer>> call, Response<List<ModelLoadSourceCustomer>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutSourceCustomer.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadSourceCustomer>> call, Throwable t) {

            }
        });
    }

    private void LoadAllProduct(String cookie) {
        ApiClient.getInstance().getAllProduct("get_allProduct", cookie).enqueue(new Callback<List<ModelLoadAllProduct>>() {
            @Override
            public void onResponse(Call<List<ModelLoadAllProduct>> call, Response<List<ModelLoadAllProduct>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutSoftWareCare.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelLoadAllProduct>> call, Throwable t) {

            }
        });
    }

    private void LoadCustomerFeel() {
        ApiClient.getInstance().getFeel("get_PhoneCallFeel").enqueue(new Callback<List<ModelCustomeFeelNew>>() {
            @Override
            public void onResponse(Call<List<ModelCustomeFeelNew>> call, Response<List<ModelCustomeFeelNew>> response) {
                ArrayList<String> arrayList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        arrayList.add(response.body().get(i).getName());
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddCallActivity.this, android.R.layout.simple_spinner_item, arrayList);
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mLayoutCustomerStatus.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelCustomeFeelNew>> call, Throwable t) {

            }
        });
    }
}
