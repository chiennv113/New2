package com.example.anew.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anew.Model.ModelAdd;
import com.example.anew.Model.ModelAddCallAndCustomerNew;
import com.example.anew.Model.ModelCustomeFeelNew;
import com.example.anew.Model.ModelLoadAllProduct;
import com.example.anew.Model.ModelLoadCity;
import com.example.anew.Model.ModelLoadCustomerType;
import com.example.anew.Model.ModelLoadObjCustomer;
import com.example.anew.Model.ModelLoadSourceCustomer;
import com.example.anew.Model.ModelSearchCu.Search;
import com.example.anew.R;
import com.example.anew.Retrofit.ApiClient;
import com.example.anew.utills.Constans;
import com.example.anew.utills.SharePrefs;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCallActivity extends AppCompatActivity {


    private TextView mTvRsEmail;
    private TextView mTvRsName;
    private ImageView mTvSearchCu;
    private ImageView mBtnCancel;
    private TextView mBtnSave;
    private TextInputLayout mLayoutPhone;
    private TextInputEditText mEdtPhone;
    private TextInputLayout mLayoutName;
    private TextInputEditText mEdtName;
    private TextInputLayout mLayoutEmail;
    private TextInputEditText mEdtEmail;
    private TextInputEditText mEdtZalo;
    private TextInputEditText mEdtSkype;
    private ImageView mSpChoseCity;
    private TextInputEditText mEdtAddress;
    private TextView mEdtDateOfBirth;
    private Spinner mTvChoseDoiTuongKH;
    private Spinner mSpChosePhanMem;
    private Spinner mTvChoseNguonKH;
    private Spinner mTvChoseLoaiKH;
    private TextInputLayout mLayoutContent;
    private TextInputEditText mEdtContent;
    private Spinner mTvChoseTTKH;
    private TextInputLayout mLayoutNote;
    private TextInputEditText mEdtNote;
    SpinnerDialog spinnerDialog;

    private DatePickerDialog.OnDateSetListener mDateOfBirth;
    private TextView mTvDateOfBirth;

    ArrayList<String> cityList = new ArrayList<>();
    private TextView mTextviewCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        initView();
        final String cookie = SharePrefs.getInstance().get(Constans.COOKIE, String.class);

        mBtnCancel.setOnClickListener(view -> finish());

        mTvSearchCu.setOnClickListener(view -> SearchCu(mEdtPhone.getText().toString().trim(), cookie));

        LoadCity(cookie);
        mSpChoseCity.setOnClickListener(v -> {
            spinnerDialog.showSpinerDialog();
            Log.e("GGG", "onClick: " + "GGG");
        });


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

        mEdtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddCallActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateOfBirth, mYear, mMonth, mDay);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDateOfBirth = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            mTvDateOfBirth.setText(date);
        };


        mBtnSave.setOnClickListener(view -> {
            try {
                String phone = mEdtPhone.getText().toString().trim();
                String address = mEdtAddress.getText().toString().trim();
                String birthday = mEdtDateOfBirth.getText().toString().trim();
                final String content = mEdtContent.getText().toString().trim();
                String email = mEdtEmail.getText().toString().trim();
                String fullname = mEdtName.getText().toString().trim();
                String note = mEdtNote.getText().toString().trim();
                String skype = mEdtSkype.getText().toString().trim();
                String zalo = mEdtZalo.getText().toString().trim();
                String city = mTextviewCity.getText().toString().trim();
                String product = mSpChosePhanMem.getSelectedItem().toString();
                String cus_type = mTvChoseLoaiKH.getSelectedItem().toString();
                String obj_cus = mTvChoseDoiTuongKH.getSelectedItem().toString();
                String source_cus = mTvChoseNguonKH.getSelectedItem().toString();
                final String cus_feel = mTvChoseTTKH.getSelectedItem().toString();

                if (phone.equals("") || content.equals("") || fullname.equals("")) {
                    Toast.makeText(AddCallActivity.this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
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
                                        int id = SharePrefs.getInstance().get(Constans.ID_CUSAFTERSEARCH, Integer.class);
                                        ApiClient.getInstance().add("add_phone_call", id, content,
                                                cus_feel, cookie).enqueue(new Callback<ModelAdd>() {
                                            @Override
                                            public void onResponse(Call<ModelAdd> call, Response<ModelAdd> response) {
                                                Toast.makeText(AddCallActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            @Override
                                            public void onFailure(Call<ModelAdd> call, Throwable t) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onFailure(Call<ModelAddCallAndCustomerNew> call, Throwable t) {

                                }
                            });

                    finish();
                }
            } catch (Exception e) {
                Log.e("GGG", "onClick: " + e.getMessage());
            }


        });
    }


    private void initView() {

        mTvRsEmail = findViewById(R.id.tvRsEmail);
        mTvRsName = findViewById(R.id.tvRsName);
        mTvSearchCu = findViewById(R.id.tvSearchCu);
        mBtnCancel = findViewById(R.id.btnCancel);
        mBtnSave = findViewById(R.id.btnSave);
        mLayoutPhone = findViewById(R.id.layoutPhone);
        mEdtPhone = findViewById(R.id.edt_phone);
        mLayoutName = findViewById(R.id.layoutName);
        mEdtName = findViewById(R.id.edt_name);
        mLayoutEmail = findViewById(R.id.layoutEmail);
        mEdtEmail = findViewById(R.id.edt_email);
        mEdtZalo = findViewById(R.id.edt_zalo);
        mEdtSkype = findViewById(R.id.edt_skype);
        mSpChoseCity = findViewById(R.id.spChoseCity);
        mEdtAddress = findViewById(R.id.edt_address);
        mEdtDateOfBirth = findViewById(R.id.edt_date_of_birth);
        mTvChoseDoiTuongKH = findViewById(R.id.tvChoseDoiTuongKH);
        mSpChosePhanMem = findViewById(R.id.spChosePhanMem);
        mTvChoseNguonKH = findViewById(R.id.tvChoseNguonKH);
        mTvChoseLoaiKH = findViewById(R.id.tvChoseLoaiKH);
        mLayoutContent = findViewById(R.id.layoutContent);
        mEdtContent = findViewById(R.id.edt_content);
        mTvChoseTTKH = findViewById(R.id.tvChoseTTKH);
        mLayoutNote = findViewById(R.id.layoutNote);
        mEdtNote = findViewById(R.id.edt_note);
        mTvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        mTextviewCity = findViewById(R.id.textviewCity);
    }

    private void LoadCity(String cookie) {
        ApiClient.getInstance().getCity("load_city", cookie).enqueue(new Callback<List<ModelLoadCity>>() {
            @Override
            public void onResponse(Call<List<ModelLoadCity>> call, Response<List<ModelLoadCity>> response) {
                cityList = new ArrayList<>();
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        cityList.add(response.body().get(i).getName());
                        spinnerDialog = new SpinnerDialog(AddCallActivity.this, cityList, "Select city");
                        spinnerDialog.bindOnSpinerListener((item, position) -> mTextviewCity.setText(item));
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
                        mTvChoseLoaiKH.setAdapter(arrayAdapter);
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
                        mTvChoseDoiTuongKH.setAdapter(arrayAdapter);
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
                        mTvChoseNguonKH.setAdapter(arrayAdapter);
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
                        mSpChosePhanMem.setAdapter(arrayAdapter);
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
                        mTvChoseTTKH.setAdapter(arrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelCustomeFeelNew>> call, Throwable t) {

            }
        });
    }

    private void SearchCu(String info, String cookie) {
        ApiClient.getInstance().search(info, "search_customer", cookie).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (response.code() == Constans.SERVER_SUCCESS && response.body() != null) {
                    mTvRsName.setText(response.body().getFullname());
                    mTvRsEmail.setText("(" + response.body().getEmail() + ")");
                    mEdtZalo.setText(mEdtPhone.getText().toString().trim());
                    mEdtName.setText(mTvRsName.getText().toString().trim());
                    mEdtEmail.setText(response.body().getEmail());

                    SharePrefs.getInstance().put(Constans.ID_CUSAFTERSEARCH, response.body().getId());
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }
}
