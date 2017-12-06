package com.example.administrator.ui.fragment2.View;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.administrator.R;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/12/1/001
 */

public class ButtomDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
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
        return  dialog;
    }
}
