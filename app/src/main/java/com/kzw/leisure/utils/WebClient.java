package com.kzw.leisure.utils;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by Android on 2019/2/26.
 */

public class WebClient extends WebViewClient {

    private requestListener listener;

    public WebClient() { }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (listener != null) {
            listener.requestResult(view.getTitle(), url);
        }
    }

    public interface requestListener {
        void requestResult(String title, String url);
    }

    public void setRequestListener(requestListener listener) {
        this.listener = listener;
    }
}
