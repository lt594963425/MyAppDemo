package com.example.administrator.ui.fragment2.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/10/23/023
 */

public class XuanZhuanView extends View {

    private Paint mPaint;

    public XuanZhuanView(Context context) {
        super(context);
        initView();
    }

    public XuanZhuanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public XuanZhuanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth()/2;
        int y = getHeight()/2;
        canvas.translate(x,y);
        canvas.drawRect(0,-400,400,0,mPaint);
    }
}
