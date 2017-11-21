package com.example.administrator.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author LiuTao
 */
public class ProgressDialogUtils {

    public static Dialog createLoadingDialog(Context context, String msg) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(msg);    //设置内容
        progressDialog.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        progressDialog.show();
        return progressDialog;
    }

    /**
     * 关闭dialog
     *
     * http://blog.csdn.net/qq_21376985
     *
     * @param dialog
     */
    public static void closeDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}