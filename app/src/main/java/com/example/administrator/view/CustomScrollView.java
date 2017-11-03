package com.example.administrator.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * $name
 * Created by ${LiuTao} on 2017/10/11/011.
 */

class CustomScrollView extends ScrollView {
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    public CustomScrollView(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev)
                && mGestureDetector.onTouchEvent(ev);
    }


    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
