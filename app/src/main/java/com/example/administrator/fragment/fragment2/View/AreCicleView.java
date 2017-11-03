package com.example.administrator.fragment.fragment2.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/10/21/021
 */

public class AreCicleView extends View {

    private Paint paint1; //画笔


    public AreCicleView(Context context) {
        super(context);
        initView();
    }

    public AreCicleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AreCicleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /**
         * 这是一个居中的View,计算位置
         */
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;
        RectF rectf = new RectF( x, y,
                getWidth() - x, getHeight() - y);
        paint1.setColor(Color.RED);
        canvas.drawArc(rectf,0,120,true,paint1);
        paint1.setColor(Color.BLUE);
        canvas.drawArc(rectf,120,130,true,paint1);
        paint1.setColor(Color.YELLOW);
        canvas.drawArc(rectf,250,60,true,paint1);
        paint1.setColor(Color.BLACK);
        canvas.drawArc(rectf,310,50,true,paint1);
    }
}
