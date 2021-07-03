package com.kzw.leisure.network;


import com.kzw.leisure.bean.VideoSearchBean;
import com.kzw.leisure.bean.VideoSearchResultBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public interface ApiService {

    @GET
    Flowable<String> get(@Url String url);

    @GET
    Flowable<String> get(@Url String url, @HeaderMap Map<String, String> headers);

    @GET
    Flowable<String> get(@Url String url, @QueryMap(encoded = true) Map<String, String> queryMap, @HeaderMap Map<String, String> headers);


    @FormUrlEncoded
    @POST
    Flowable<String> post(@Url String url, @FieldMap(encoded = true) Map<String, String> fieldMap);


    @FormUrlEncoded
    @POST
    Flowable<String> post(@Url String url, @FieldMap(encoded = true) Map<String, String> fieldMap, @HeaderMap Map<String, String> headers);


}
