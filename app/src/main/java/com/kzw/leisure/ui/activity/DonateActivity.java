package com.kzw.leisure.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;

import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.OnClick;

public class DonateActivity extends BaseActivity {

//FKX00764DNFNQTZFDFJSAA

    String alipayCode = "https://raw.githubusercontent.com/Kangzhengwei/Privacypolicy/main/alipay_code.jpg";
    String wechatCode = "https://raw.githubusercontent.com/Kangzhengwei/Privacypolicy/main/wechat_code.jpg";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.alipay_qrcode)
    Button alipayQrcode;
    @BindView(R.id.alipay_pay)
    Button alipayPay;
    @BindView(R.id.wechat)
    Button wechat;
    @BindView(R.id.btn_layout)
    LinearLayout btnLayout;

    @Override
    protected int getContentView() {
        return R.layout.activity_donate;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setActionBar("捐赠", true, toolbar);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.alipay_qrcode, R.id.alipay_pay, R.id.wechat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alipay_qrcode:
                openBrowser(alipayCode);
                break;
            case R.id.alipay_pay:
                aliDonate();
                break;
            case R.id.wechat:
                openBrowser(wechatCode);
                break;
        }
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
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void aliDonate() {
        try {
            String qrCode = URLEncoder.encode("FKX00764DNFNQTZFDFJSAA", "utf-8");
            final String aliPayQr = "alipayqr://platformapi/startapp?saId=10000007&qrcode=https://qr.alipay.com/" + qrCode;
            openUri(aliPayQr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送一个intent
     */
    private void openUri(String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(intent);
    }

    private void openBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }
}
