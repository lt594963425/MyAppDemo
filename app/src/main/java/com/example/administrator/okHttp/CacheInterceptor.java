package com.example.administrator.okHttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * $name
 * Created by ${LiuTao} on 2017/10/18/018.
 */

public class CacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originResponse = chain.proceed(chain.request());

        //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
        return originResponse.newBuilder().removeHeader("pragma")
                .header("Cache-Control","max-age=60").build();
    }
}
