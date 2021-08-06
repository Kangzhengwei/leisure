package com.kzw.leisure.ui.activity;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseWebViewActivity;
import com.kzw.leisure.utils.BarHide;
import com.kzw.leisure.utils.ImmersionBar;
import com.kzw.leisure.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class BrowserActivity extends BaseWebViewActivity {

    @BindView(R.id.top_bar_left)
    AppCompatImageView topBarLeft;
    @BindView(R.id.edit_text)
    AppCompatEditText editText;
    @BindView(R.id.top_bar_right)
    AppCompatImageView topBarRight;
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    @BindView(R.id.top_bar)
    Toolbar topBar;
    @BindView(R.id.btn_left)
    AppCompatImageView btnLeft;
    @BindView(R.id.btn_right)
    AppCompatImageView btnRight;
    @BindView(R.id.btn_menu)
    AppCompatImageView btnMenu;
    @BindView(R.id.btn_home)
    AppCompatImageView btnHome;
    @BindView(R.id.bottom_bar)
    Toolbar bottomBar;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @Override
    protected int getInitView() {
        return R.layout.activity_browser;
    }

    @Override
    protected void initWidgets() {
        ImmersionBar.with(this).statusBarColor(R.color.colorPrimary).hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
        String url = getIntent().getStringExtra("url");
        initToolBar(url);
        loadUrl(url);
    }

    private void initToolBar(String url) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        params.topMargin = ImmersionBar.getStatusBarHeight(this);
        appBarLayout.setLayoutParams(params);
        editText.setText(url);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.setText("");
                btnCancel.setVisibility(View.VISIBLE);
                topBarRight.setVisibility(View.GONE);
            } else {
                btnCancel.setVisibility(View.GONE);
                topBarRight.setVisibility(View.VISIBLE);
            }
        });
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (StringUtils.isValid(v.getText().toString()))
                    loadUrl(v.getText().toString());
                return true;
            }
            return false;
        });
    }

    @OnClick({R.id.top_bar_left, R.id.top_bar_right, R.id.btn_cancel, R.id.btn_left, R.id.btn_right, R.id.btn_menu, R.id.btn_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_bar_left:
                String url = editText.getText().toString();
                if (StringUtils.isValid(url)) {
                    loadUrl(url);
                }
                break;
            case R.id.top_bar_right:
                loadUrl(webUrl);
                break;
            case R.id.btn_cancel:
                editText.setText(webTitle);
                btnCancel.setVisibility(View.GONE);
                topBarRight.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_left:
                if (webview.canGoBack()) {
                    webview.goBack();
                }
                break;
            case R.id.btn_right:
                if (webview.canGoForward()) {
                    webview.goForward();
                }
                break;
            case R.id.btn_menu:
                break;
            case R.id.btn_home:
                finish();
                break;
        }
    }

    @Override
    protected void loadFinish() {
        editText.setText(webTitle);
    }
}