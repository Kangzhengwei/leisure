package com.kzw.leisure.ui.activity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.kzw.leisure.R;
import com.kzw.leisure.base.BaseActivity;
import com.kzw.leisure.utils.Constant;
import com.kzw.leisure.utils.DocumentHelper;
import com.kzw.leisure.utils.FileUtils;
import com.kzw.leisure.widgets.X5WebView;
import com.tencent.smtt.sdk.URLUtil;

import butterknife.BindView;

public class DownloadFrontActivity extends BaseActivity {


    @BindView(R.id.webview)
    X5WebView webview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    String fontPath = FileUtils.getSdCardPath() + "/Fonts";

    @Override
    protected int getContentView() {
        return R.layout.activity_download_front;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setActionBar("字体下载", true, toolbar);
        webview.loadUrl(Constant.FRONT_DOWNLOAD_URL);
        webview.setDownloadListener((s, s1, s2, s3, l) -> download(s, s2, s3));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
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

    private void download(String url, String contentDisposition, String mimeType) {
        // 指定下载地址
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        // 允许媒体扫描，根据下载的文件类型被加入相册、音乐等媒体库
        request.allowScanningByMediaScanner();
        // 设置通知的显示类型，下载进行时和完成后显示通知
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 设置通知栏的标题，如果不设置，默认使用文件名
        request.setTitle("字体下载");
        // 设置通知栏的描述
        //request.setDescription("This is description");
        // 允许在计费流量下下载
        request.setAllowedOverMetered(false);
        // 允许该记录在下载管理界面可见
        request.setVisibleInDownloadsUi(false);
        // 允许漫游时下载
        request.setAllowedOverRoaming(true);
        // 允许下载的网路类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        // 设置下载文件保存的路径和文件名
        String fileName = URLUtil.guessFileName(url, contentDisposition, mimeType);
        DocumentHelper.createDirIfNotExist(fontPath);
        request.setDestinationInExternalPublicDir("Fonts", fileName);
        //另外可选一下方法，自定义下载路径
        //request.setDestinationUri()
        //request.setDestinationInExternalFilesDir()
        final DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        // 添加一个下载任务
        long downloadId = downloadManager.enqueue(request);
    }



}
