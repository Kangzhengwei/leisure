package com.kzw.leisure.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class X5WebView extends WebView {
    private OnScrollChangeListener listener;


    public X5WebView(Context arg0) {
        super(arg0);
        initMethod();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
        initMethod();
    }

    public X5WebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initMethod();
    }

    public void initMethod() {
        initWebViewSettings();
        this.setVerticalScrollBarEnabled(false);
    }

    private void initWebViewSettings() {
        WebSettings webSetting = getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(getContext().getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(getContext().getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(getContext().getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.d("webview", "l=" + l + "t=" + t + "oldl=" + oldl + "oldt=" + oldt);
        float webcontent = getContentHeight() * getScale();

        Log.d("webview", "webcontent" + webcontent);
        // 当前webview的高度
        float webnow = getHeight() + getScrollY();

        Log.d("webview", "webnow" + webnow);
        if (listener != null) {
            Log.e("webview", "webcontent - webnow" + (webcontent - webnow));
            listener.onScrollChanged();
            if (t - oldt < -5) {
                listener.onScrollUp();
            }
            if (t - oldt > 5) {
                listener.onScrollDown();
            }
            if (Math.abs(webcontent - webnow) < t + 5) {
                //处于底端
                listener.onPageEnd(l, t, oldl, oldt);
            } else if (getScrollY() == 0) {
                //处于顶端
                listener.onPageTop(l, t, oldl, oldt);
            }
        }
    }

    public void setOnScrollChangeListener(OnScrollChangeListener listener) {
        this.listener = listener;
    }

    public interface OnScrollChangeListener {
        void onScrollChanged();

        void onScrollDown();

        void onScrollUp();

        void onPageEnd(int l, int t, int oldl, int oldt);

        void onPageTop(int l, int t, int oldl, int oldt);
    }

}
