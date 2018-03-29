package com.example.administrator.ui.fragment2.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.R;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/21/021
 */

public class LoginDialogFragment extends DialogFragment {
    private EditText mUsername;
    private EditText mPassword;
    private AlertDialog mDialog;

    public interface LoginInputListener {
        void onLoginInputComplete(String username, String password);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", mUsername.getText().toString());
        outState.putString("password", mPassword.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //内部透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.half_transparent)));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FPVDialog);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_login_dialog, null);
        mUsername = (EditText) view.findViewById(R.id.id_txt_username);
        mPassword = (EditText) view.findViewById(R.id.id_txt_password);
        ImageView finish = (ImageView) view.findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        if (savedInstanceState != null) {
            mUsername.setText(savedInstanceState.getString("name"));
            mPassword.setText(savedInstanceState.getString("password"));
        }

        builder.setView(view).setPositiveButton("Sign in",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        LoginInputListener listener = (LoginInputListener) getActivity();
                        listener.onLoginInputComplete(
                                mUsername.getText().toString(),
                                mPassword.getText().toString());
                    }
                }).setNegativeButton("Cancel", null);
        mDialog = builder.create();
        // mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return mDialog;
    }

    @Override
    public void onStart() {
        Log.e("TAG", "-------------ButtomDialogFragment------onStart------------------");
        super.onStart();
        setBackgroundTranst();


    }

    private void setBackgroundTranst() {
        //public static final int FILL_PARENT = -1;
        //public static final int MATCH_PARENT = -1;
        //public static final int WRAP_CONTENT = -2;
        //外部背景设为透明色
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
        //window.setLayout(-2, -2);
    }

}