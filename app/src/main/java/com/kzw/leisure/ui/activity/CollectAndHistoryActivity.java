package com.kzw.leisure.ui.activity;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kzw.leisure.R;
import com.kzw.leisure.adapter.ViewPagerAdapter;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.ui.fragment.HistoryFragment;
import com.kzw.leisure.ui.fragment.WebCollectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectAndHistoryActivity extends BaseActivity {


    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.tb_home)
    TabLayout tbHome;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_collect_and_history;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titles.add("收藏");
        titles.add("历史");
        fragments.add(WebCollectFragment.newInstance());
        fragments.add(HistoryFragment.newInstance());
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles, fragments));
        tbHome.setupWithViewPager(viewPager);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }
}