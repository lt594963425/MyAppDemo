package com.example.administrator.mvpbase.login;

import org.json.JSONObject;

/**
 * $activityName
 *
 * @author LiuTao
 * @date 2018/8/11/011
 */


public interface OkHttpListener {
    void onSuccess(JSONObject jsonSuccess);

    void onFaild(JSONObject jsonFaild);

    void onError(String jsonError);
    void onExceptError(String jsonError);
}
