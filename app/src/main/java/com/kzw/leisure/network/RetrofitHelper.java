package com.kzw.leisure.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kzw.leisure.bean.Query;

import java.util.Map;

import io.reactivex.Flowable;
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


    public Retrofit createRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(OkHttpHelper.getInstance().getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(EncodeConverter.create())
                .build();
    }


    public Flowable<String> getResponse(String baseUrl, String path) {
        return createRetrofit(baseUrl)
                .create(ApiService.class)
                .get(path);
    }


    public Flowable<String> getResponse(Query query) {
        switch (query.getUrlMode()) {
            case POST:
                return createRetrofit(query.getHost())
                        .create(ApiService.class)
                        .post(query.getPath(), query.getQueryMap(), query.getHeaderMap());
            case GET:
                return createRetrofit(query.getHost())
                        .create(ApiService.class)
                        .get(query.getPath(), query.getQueryMap(), query.getHeaderMap());
            default:
                return createRetrofit(query.getHost())
                        .create(ApiService.class)
                        .get(query.getPath(), query.getHeaderMap());
        }
    }


}
