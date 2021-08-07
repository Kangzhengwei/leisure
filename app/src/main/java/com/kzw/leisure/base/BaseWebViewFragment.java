package com.kzw.leisure.base;


import static android.content.Context.AUDIO_SERVICE;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.kzw.leisure.R;
import com.kzw.leisure.interfaces.WebViewJavaScriptFunction;
import com.kzw.leisure.utils.WebClient;
import com.kzw.leisure.widgets.X5WebView;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

import butterknife.BindView;

/**
 * author: kang4
 * Date: 2019/11/20
 * Description:
 */
public abstract class BaseWebViewFragment extends BaseFragment implements WebClient.requestListener {


    @BindView(R.id.webview)
    public X5WebView webview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public View customView;
    private FrameLayout fullscreenContainer;
    private IX5WebChromeClient.CustomViewCallback customViewCallback;
    private AudioManager mAudioManager;
    protected static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    public String webTitle;
    public String webUrl;



    @Override
    public void initWidget() {
        super.initWidget();
        initWebView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        mAudioManager = (AudioManager) mActivity.getSystemService(AUDIO_SERVICE);
        return super.onCreateView(inflater, container, state);
    }

    private void initWebView() {
        WebClient webClient = new WebClient();
        webClient.setRequestListener(this);
        webview.setWebViewClient(webClient);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setVerticalScrollBarEnabled(false);
        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onJsConfirm(WebView arg0, String arg1, String arg2, JsResult arg3) {
                return super.onJsConfirm(arg0, arg1, arg2, arg3);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }

            /**
             * 全屏播放配置
             */
            @Override
            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                showCustomView(view, customViewCallback);
            }

            @Override
            public void onHideCustomView() {
                hideCustomView(webview);
            }

            @Override
            public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3) {
                return super.onJsAlert(null, arg1, arg2, arg3);
            }
        });

        //全屏播放的相关方法
        webview.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {
                // TODO Auto-generated method stub
            }

            @JavascriptInterface
            public void onX5ButtonClicked() {
                enableX5FullscreenFunc(webview);
            }

            @JavascriptInterface
            public void onCustomButtonClicked() {
                disableX5FullscreenFunc(webview);
            }

            @JavascriptInterface
            public void onLiteWndButtonClicked() {
                enableLiteWndFunc(webview);
            }

            @JavascriptInterface
            public void onPageVideoClicked() {
                enablePageVideoFunc(webview);
            }
        }, "Android");
    }

    public void loadUrl(String url) {
        webview.loadUrl(url);
    }

    /**
     * 视频播放全屏
     **/
    public void showCustomView(View view, IX5WebChromeClient.CustomViewCallback callback) {
        // if a view already exists then immediately terminate the new one
        if (customView != null) {
            callback.onCustomViewHidden();
            return;
        }
        setFullScreen();
        mActivity.getWindow().getDecorView();
        FrameLayout decor = (FrameLayout) mActivity.getWindow().getDecorView();
        fullscreenContainer = new FullscreenHolder(mActivity);
        fullscreenContainer.addView(view, COVER_SCREEN_PARAMS);
        decor.addView(fullscreenContainer, COVER_SCREEN_PARAMS);
        customView = view;
        setStatusBarVisibility(false);
        customViewCallback = callback;
    }

    /**
     * 设置全屏
     */
    private void setFullScreen() {
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * 隐藏视频全屏
     */
    public void hideCustomView(X5WebView view) {
        if (customView == null) {
            return;
        }
        WindowManager.LayoutParams attr = mActivity.getWindow().getAttributes();
        attr.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivity.getWindow().setAttributes(attr);
        mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusBarVisibility(true);
        FrameLayout decor = (FrameLayout) mActivity.getWindow().getDecorView();
        decor.removeView(fullscreenContainer);
        fullscreenContainer = null;
        customView = null;
        customViewCallback.onCustomViewHidden();
        view.setVisibility(View.VISIBLE);
    }


    /**
     * 全屏容器界面
     */
    static class FullscreenHolder extends FrameLayout {

        public FullscreenHolder(Context ctx) {
            super(ctx);
            setBackgroundColor(ctx.getResources().getColor(android.R.color.black));
        }

        @Override
        public boolean onTouchEvent(MotionEvent evt) {
            return true;
        }
    }

    private void setStatusBarVisibility(boolean visible) {
        int flag = visible ? 0 : WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mActivity.getWindow().setFlags(flag, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    //音频控件获取焦点
    private boolean requestAudioFocus() {
        int result = mAudioManager.requestAudioFocus(audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = focusChange -> { };

    @Override
    public void onResume() {
        super.onResume();
        requestAudioFocus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAudioManager.abandonAudioFocus(audioFocusChangeListener);
    }


    public void enableX5FullscreenFunc(X5WebView view) {
        if (view.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            view.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    public void disableX5FullscreenFunc(X5WebView view) {
        if (view.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", true);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            view.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    public void enableLiteWndFunc(X5WebView view) {
        if (view.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", true);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            view.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    public void enablePageVideoFunc(X5WebView view) {
        if (view.getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，会调起onShowCustomView()，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 1);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            view.getX5WebViewExtension().invokeMiscMethod("setVideoParams",
                    data);
        }
    }

    @Override
    public void requestResult(String title, String url) {
        webTitle = title;
        webUrl = url;
    }

    @Override
    public void onDestroyView() {
        if (webview != null) {//解决Receiver not registered: android.widget.ZoomButtonsController
            webview.getSettings().setBuiltInZoomControls(true);
            webview.setVisibility(View.GONE);// 把destroy()延后
            ((ViewGroup) webview.getParent()).removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroyView();
    }
}
