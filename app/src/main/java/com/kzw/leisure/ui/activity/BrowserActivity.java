package com.kzw.leisure.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseWebViewActivity;
import com.kzw.leisure.realm.CollectDataBean;
import com.kzw.leisure.realm.CollectDataList;
import com.kzw.leisure.realm.HistoryDataBean;
import com.kzw.leisure.realm.HistoryDataList;
import com.kzw.leisure.utils.BarHide;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.ImmersionBar;
import com.kzw.leisure.utils.IntentUtils;
import com.kzw.leisure.utils.RealmHelper;
import com.kzw.leisure.utils.StringUtils;

import java.util.List;

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
    @BindView(R.id.btn_collect)
    AppCompatImageView btnCollect;


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
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) appBarLayout.getLayoutParams();
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

    @OnClick({R.id.top_bar_left, R.id.top_bar_right, R.id.btn_cancel, R.id.btn_left, R.id.btn_right, R.id.btn_menu, R.id.btn_home, R.id.btn_collect})
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
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.btn_right:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.btn_menu:
                IntentUtils.intentToCollectAndHistoryActivity(this);
                break;
            case R.id.btn_home:
                finish();
                break;
            case R.id.btn_collect:
                if (TextUtils.isEmpty(webUrl)) {
                    return;
                }
                collect();
                break;
        }
    }

    private void collect() {
        RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
            boolean ishas = false;
            List<CollectDataBean> list = realm.where(CollectDataBean.class).findAll();
            if (list != null && list.size() > 0) {
                for (CollectDataBean bean : list) {
                    if (!TextUtils.isEmpty(bean.getUrl()) && bean.getUrl().equals(webUrl)) {
                        ishas = true;
                        showSnackBar("该链接已收藏", bottomBar);
                        break;
                    }
                }
            }
            if (!ishas) {
                CollectDataBean data = realm.createObject(CollectDataBean.class);
                data.setUrl(webUrl);
                data.setUrlTitle(webTitle);
                CollectDataList dataList = realm.where(CollectDataList.class).findFirst();
                if (dataList != null) {
                    dataList.getCollectList().add(data);
                } else {
                    CollectDataList newList = realm.createObject(CollectDataList.class);
                    newList.getCollectList().add(data);
                }
                showSnackBar("收藏成功", bottomBar);
            }
        });
    }

    @Override
    protected void loadFinish() {
        editText.setText(webTitle);
        addHistory();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.ACTIVITY_REQUEST_CODE && resultCode == Constant.ACTIVITY_RESULT_CODE) {
            String url = data.getStringExtra("url");
            loadUrl(url);
        }
    }

    private void addHistory() {
        RealmHelper.getInstance().getRealm().executeTransaction(realm -> {
            boolean ishas = false;
            List<HistoryDataBean> list = realm.where(HistoryDataBean.class).findAll();
            if (list != null && list.size() > 0) {
                for (HistoryDataBean bean : list) {
                    if (!TextUtils.isEmpty(bean.getUrl()) && bean.getUrl().equals(webUrl)) {
                        ishas = true;
                        break;
                    }
                }
            }
            if (!ishas) {
                HistoryDataBean data = realm.createObject(HistoryDataBean.class);
                data.setUrl(webUrl);
                data.setUrlTitle(webTitle);
                HistoryDataList dataList = realm.where(HistoryDataList.class).findFirst();
                if (dataList != null) {
                    if (dataList.getHistoryDataBeanRealmList().size() >= 100) {
                        dataList.getHistoryDataBeanRealmList().remove(0);
                    }
                    dataList.getHistoryDataBeanRealmList().add(data);
                } else {
                    HistoryDataList newList = realm.createObject(HistoryDataList.class);
                    newList.getHistoryDataBeanRealmList().add(data);
                }
            }
        });
    }


}