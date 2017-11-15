
package com.example.administrator.ui.fragment4.activity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.utils.Chinese;
import com.example.administrator.utils.DragLayout;
import com.example.administrator.utils.MyLinnerLayout;
import com.example.administrator.utils.ToastUtils;


public class ActivityOne extends BaseActivity {
    private ListView lv_main;
    private ListView lv_menu;
    private ImageView iv_header;
    private DragLayout dl;
    private MyLinnerLayout ller_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_menu = (ListView) findViewById(R.id.lv_leftmenu);
        dl = (DragLayout) findViewById(R.id.dl);
        lv_main.setAdapter(new ArrayAdapter<String>(ActivityOne.this,R.layout.item_tv, Chinese.NAMES){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                ((TextView)view).setTextColor(Color.BLACK);

                return view;
            }
        });
        lv_menu.setAdapter(new ArrayAdapter<String>(ActivityOne.this, R.layout.item_tv,Chinese.sCheeseStrings));
        iv_header = (ImageView) findViewById(R.id.iv_header);
        iv_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.open();
            }
        });

        dl.setDragChangedListener(new DragLayout.onDragChangedListener() {
            @Override
            public void onOpen() {
                ToastUtils.showToast("打开");

            }

            @Override
            public void onClose() {
                ToastUtils.showToast("关闭");
                ObjectAnimator animator = ObjectAnimator.ofFloat(iv_header, "translationX", 15f);
                animator.setInterpolator(new CycleInterpolator(4));
                animator.setDuration(500);
                animator.start();
            }

            @Override
            public void onGraging(float percent) {
            }
        });
        ller_main = (MyLinnerLayout) findViewById(R.id.ller_main);
        ller_main.setDragLayout(dl);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        onDestroy();
        super.onBackPressed();
    }
}
