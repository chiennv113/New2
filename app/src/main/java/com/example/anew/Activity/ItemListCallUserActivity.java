package com.example.anew.Activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.anew.R;
import com.example.anew.ui.call.FragmentInfo;
import com.example.anew.ui.call.PagerAdapterInfo;
import com.example.anew.utills.Constans;
import com.google.android.material.tabs.TabLayout;

public class ItemListCallUserActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager pager;
    private PagerAdapterInfo adapter;
    private ImageView mImgBack;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_call_user);
        initView();
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString(Constans.NAME);
        String address = bundle.getString(Constans.ADDRESS);
        String skype = bundle.getString(Constans.SKYPE);
        String email = bundle.getString(Constans.EMAIL);
        String phone = bundle.getString(Constans.PHONE);

        adapter = new PagerAdapterInfo(this.getSupportFragmentManager());
        FragmentInfo fragmentInfo = new FragmentInfo();
        adapter.addFragment(fragmentInfo, name, phone, address, skype, email);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);//deprecated
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }

    private void initView() {
        tabLayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.view_pager);
        mImgBack = findViewById(R.id.imgBack);
    }
}
