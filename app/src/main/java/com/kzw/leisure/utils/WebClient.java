package com.kzw.leisure.utils;

import android.text.TextUtils;

import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Android on 2019/2/26.
 */

public class WebClient extends WebViewClient {

    private requestListener listener;
    private final String TAG = "WebClient";

    public WebClient() {
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (listener != null) {
            listener.requestResult(view.getTitle(), url);
        }
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
        LogUtils.d(TAG, s);
        saveToken(s);
        return super.shouldInterceptRequest(webView, s);
    }

    public interface requestListener {
        void requestResult(String title, String url);
    }

    public void setRequestListener(requestListener listener) {
        this.listener = listener;
    }

    private void saveToken(String s) {
        if (s.contains(Constant.KEY_URL)) {
            int a = s.indexOf("=") + 1;
            int b = s.indexOf("&");
            if (a > 0 && b >= 0) {
                String token = SPUtils.getInstance().getString("token");
                if (TextUtils.isEmpty(token) || !token.equals(s.substring(a, b))) {
                    SPUtils.getInstance().putString("token", s.substring(a, b));
                    LogUtils.d(TAG, "token==" + s.substring(a, b));
                }
            }
        }
    }

}
