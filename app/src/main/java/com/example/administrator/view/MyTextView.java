package com.example.administrator.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.R;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/20/020
 */

public class MyTextView extends TextView {
    private static final String TAG = "MyTextView";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count ; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            Log.e(TAG,attrName+" ===="+attrValue);
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTest);
        String text = typedArray.getString(R.styleable.MyTest_text);
        Integer testattr = typedArray.getInteger(R.styleable.MyTest_testAttr, -1);
        Log.e(TAG, text + "::::" + testattr);
        typedArray.recycle();
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
