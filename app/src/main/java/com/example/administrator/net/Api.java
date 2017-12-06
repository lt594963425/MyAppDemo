package com.example.administrator.net;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by caoyu on 2017/11/30/030.
 */

public interface Api {

    /**
     * @param
     * @return
     */

    @POST("/improve/Auth/login")
    //方法名
    Call<ResponseBody> login(@Body ResponseBody jsonbody);


    @GET("/improve/version_manager/version_update")
    Call<ResponseBody> version_up(@QueryMap Map<String,String> map);


    static RequestBody getJson(JSONObject jsonObject) {

        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), String.valueOf(jsonObject));

   }
}
