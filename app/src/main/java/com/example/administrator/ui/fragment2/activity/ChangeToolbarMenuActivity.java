package com.example.administrator.ui.fragment2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * $activityName
 *
 * @author ${LiuTao}
 * @date 2018/3/29/029
 */

public class ChangeToolbarMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  se
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //这里修改 Toolbar 菜单 menu的显示和隐藏
        //    Log.e("mMenuGone", mMenuGone + "");
//        if (mMenuGone) {
//            menu.findItem(R.id.action).setVisible(true);
//            menu.findItem(R.id.setting).setVisible(false);
//        } else {
//            menu.findItem(R.id.setting).setVisible(true);
//            menu.findItem(R.id.action).setVisible(false);
//        }
        return super.onPrepareOptionsMenu(menu);
    }
}
