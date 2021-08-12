package com.kzw.leisure.ui.activity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;

import butterknife.BindView;

public class DisclaimersActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getContentView() {
        return R.layout.activity_disclaimers;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setActionBar("免责声明", true, toolbar);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData() {

    }

}