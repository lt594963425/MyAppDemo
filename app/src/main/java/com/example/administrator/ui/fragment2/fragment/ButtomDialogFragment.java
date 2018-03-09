package com.example.administrator.ui.fragment2.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.R;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/12/1/001
 */

public class ButtomDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        //getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.FPVDialog);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_login_dialog, null);
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        int mWindowWidth;
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        mWindowWidth = displayMetrics.widthPixels;
        dialog.setContentView(inflate, new ViewGroup.MarginLayoutParams(mWindowWidth,
                ViewGroup.MarginLayoutParams.MATCH_PARENT));
        dialog.show();//显示对话框
        return dialog;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "--------------ButtomDialogFragment-----onResume------------------");

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "-------------ButtomDialogFragment------onPause------------------");

    }

    @Override
    public void onDestroy() {
        Log.e("TAG", "-------------ButtomDialogFragment------onDestroy------------------");
        super.onDestroy();
    }

    @Override
    public void onStart() {
        Log.e("TAG", "-------------ButtomDialogFragment------onStart------------------");
        super.onStart();
       // setBackgroundTranst();

    }

    private void setBackgroundTranst() {
        //背景设为透明色
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    @Override
    public void onStop() {
        Log.e("TAG", "-------------ButtomDialogFragment------onStop------------------");
        super.onStop();
    }
}
