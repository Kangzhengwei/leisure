package com.kzw.leisure.network;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kzw.leisure.base.BaseApplication;
import com.kzw.leisure.utils.FileUtils;
import com.kzw.leisure.utils.NetworkUtils;
import com.kzw.leisure.utils.SSLSocketClient;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author kzw
 * @date 创建时间：2019/4/16 10:23
 * 描述:全局统一使用的OkHttpClient工具
 */

public class OkHttpHelper {

    private static final long DEFAULT_READ_TIMEOUT_MILLIS = 7 * 1000;  //读取时间
    private static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 7 * 1000;  //写入时间
    private static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 7 * 1000;  //超时时间
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 20 * 1024 * 1024;//设置20M
    private static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;   //长缓存有效期为7天
    private static volatile OkHttpHelper sInstance;
    private OkHttpClient mOkHttpClient;
    private Context mContext = BaseApplication.getInstance();

    private OkHttpHelper() {
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .cache(getCache(mContext)) //设置缓存
                .retryOnConnectionFailure(true)// 失败重发
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.createTrustAllManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)   //设置缓存
                .addInterceptor(mRewriteCacheControlInterceptor)
                .addNetworkInterceptor(new StethoInterceptor()) //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))   //http数据log，日志中打印出HTTP请求&响应数据
                .addInterceptor(new UserAgentInterceptor())    //便于查看json
                .build();
    }

    public static OkHttpHelper getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpHelper.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpHelper();
                }
            }
        }
        return sInstance;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    /**
     * 设置缓存路径
     *
     * @param context 上下文
     */
    public void setCache(Context context) {
        final File baseDir = context.getApplicationContext().getCacheDir();
        if (baseDir != null) {
            final File cacheDir = new File(baseDir, "CopyCache");
            mOkHttpClient.newBuilder().cache((new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE)));
        }
    }


    /**
     * 获取缓存路径
     *
     * @return cache
     */
    private Cache getCache(Context context) {
        Cache cache = null;
        String path = FileUtils.createRootPath(context);
        final File baseDir = new File(path);
        final File cacheDir = new File(baseDir, "CopyCache");
        cache = new Cache(cacheDir, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
        return cache;
    }


    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = chain -> {
        Request request = chain.request();
        // Logger.d(NetworkUtils.isAvailable(mContext));
        if (!NetworkUtils.isConnected(mContext)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtils.isConnected(mContext)) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            String cacheControl = request.cacheControl().toString();
            return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                    .removeHeader("Pragma").build();
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                    .removeHeader("Pragma").build();
        }
    };

    /**
     * 添加UA拦截器
     */
    private static class UserAgentInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .addHeader("connection", "Keep-Alive")
                    .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }

}
