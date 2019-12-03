package com.kzw.leisure.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public class RetrofitHelper {

    private static volatile RetrofitHelper sInstance;

    public static RetrofitHelper getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (sInstance == null) {
                    sInstance = new RetrofitHelper();
                }
            }
        }
        return sInstance;
    }


    public Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(EncodeConverter.create())
                .build();
    }


    public Flowable<String> getResponse(String baseUrl, String path) {
        return createRetrofit(new Retrofit.Builder(), OkHttpHelper.getInstance().getOkHttpClient(), baseUrl)
                .create(ApiService.class)
                .get(path);
    }

    public Flowable<String> postResponse(String baseUrl, String path, Map<String, String> map) {
        return createRetrofit(new Retrofit.Builder(), OkHttpHelper.getInstance().getOkHttpClient(), baseUrl)
                .create(ApiService.class)
                .post(path, map);
    }


}
