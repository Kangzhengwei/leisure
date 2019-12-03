package com.kzw.leisure.network;


import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * author: kang4
 * Date: 2019/11/22
 * Description:
 */
public interface ApiService {

    @GET
    Flowable<String> get(@Url String url);


    @FormUrlEncoded
    @POST
    Flowable<String> post(@Url String url, @FieldMap(encoded = true) Map<String, String> fieldMap);

}
