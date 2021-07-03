package com.kzw.leisure.utils;

import android.os.Bundle;

import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Android on 2019/2/26.
 */

public class WebClient extends WebViewClient {

    private requestListener listener;

    public WebClient() {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String s) {
        LogUtils.d("url===",s);
        return super.shouldOverrideUrlLoading(webView, s);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        LogUtils.d("url===",webResourceRequest.getUrl().toString());
        return super.shouldOverrideUrlLoading(webView, webResourceRequest);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        LogUtils.d("url===",url);
        super.onPageFinished(view, url);
        if (listener != null) {
            listener.requestResult(view.getTitle(), url);
        }
    }

    @Override
    public void onLoadResource(WebView webView, String s) {
        LogUtils.d("url1===",s);
        super.onLoadResource(webView, s);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
        LogUtils.d("url2===",s);
        if(s.contains(Constant.KWYURL)){
            String str=s.substring(s.indexOf("=")+1,s.indexOf("&"));
            LogUtils.d("url5===",str);

            SPUtils.getInstance().putString("token",str);
        }
        return super.shouldInterceptRequest(webView, s);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        LogUtils.d("url3===",webResourceRequest.getUrl());
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest, Bundle bundle) {
        LogUtils.d("url4===",webResourceRequest.getUrl());

        return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
    }

    public interface requestListener {
        void requestResult(String title, String url);
    }

    public void setRequestListener(requestListener listener) {
        this.listener = listener;
    }

}
