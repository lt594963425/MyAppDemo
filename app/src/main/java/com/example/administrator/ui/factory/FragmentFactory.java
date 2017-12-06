package com.example.administrator.ui.factory;

import android.util.Pair;
import android.util.SparseArray;

import com.example.administrator.base.BaseFragment;
import com.example.administrator.ui.Fragment1;
import com.example.administrator.ui.Fragment2;
import com.example.administrator.ui.Fragment3;
import com.example.administrator.ui.Fragment4;
import com.example.administrator.ui.Fragmenta;

import java.util.ArrayList;
import java.util.List;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/16/016
 */

public class FragmentFactory {
    public static final int TAB_RECOMMEND = 0;

    public static final int TAB_CATEGORY = 1;

    public static final int TAB_TOP = 2;

    public static final int TAB_APPMANAGER = 3;
    private List<Pair<String, Fragmenta>> fragments =new ArrayList<Pair<String, Fragmenta>>();
    //存在内存泄露
    private static SparseArray< BaseFragment> mFragmentMap = new SparseArray<>();

    /**
     * 统一通过一个方法创建Fragment，避免重复创建
     *
     * @param index
     * @return
     */
    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragmentMap.get(index);
        if (fragment == null) {
            //如果Fragment不存在就创建相应的Fragment
            switch (index) {
                case TAB_RECOMMEND:
                    fragment = new Fragment1();
                    break;
                case TAB_CATEGORY:
                    fragment = new Fragment2();
                    break;
                case TAB_TOP:
                    fragment = new Fragment3();
                    break;
                case TAB_APPMANAGER:
                    fragment = new Fragment4();
                    break;
                default:
                    break;
            }
            mFragmentMap.put(index, fragment);
        }
        return fragment;
    }

}
