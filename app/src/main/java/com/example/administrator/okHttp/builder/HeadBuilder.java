package com.example.administrator.okHttp.builder;


import com.example.administrator.okHttp.OkHttpUtils;
import com.example.administrator.okHttp.request.OtherRequest;
import com.example.administrator.okHttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
