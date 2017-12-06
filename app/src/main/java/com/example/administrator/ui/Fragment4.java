package com.example.administrator.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.design.adapter.AdapterTestActivity;
import com.example.administrator.design.decorator.DecortorTestActivity;
import com.example.administrator.design.factory.FactorySendActivity;
import com.example.administrator.greendao.ActivityGreenDao;
import com.example.administrator.loginMvp.ui.LoginActivity;
import com.example.administrator.net.ActivityRetrofit;
import com.example.administrator.ui.fragment4.activity.ActivityImageLoader;
import com.example.administrator.ui.fragment4.activity.ActivityListViewRefresh;
import com.example.administrator.ui.fragment4.activity.ActivityOne;
import com.example.administrator.ui.fragment4.activity.ActivityTextInputLayout;
import com.example.administrator.ui.fragment4.activity.ActivityTwo;
import com.example.administrator.ui.fragment4.activity.ClickActivity;
import com.example.administrator.ui.fragment4.activity.NetworkActivity;
import com.example.administrator.ui.fragment4.activity.OKHttpActivity;
import com.example.administrator.ui.fragment4.activity.ShowImageActivity;
import com.example.administrator.ui.fragment4.activity.SimpleActivity;
import com.example.administrator.view.GlideCircleTransform;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Fragment4
 * Created by liu_tao on 16/5/23.
 */
public class Fragment4 extends BaseFragment implements View.OnClickListener {



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);
        CircleImageView circleimage =  view.findViewById(R.id.profile_image);
        ImageView proFileImage = view.findViewById(R.id.circle_image);

        view.findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ShowImageActivity.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), v, "head_image").toBundle());
                getActivity().overridePendingTransition(0, 0);
            }
        });

        //加载网络图片
        String path1 = "http://img3.imgtn.bdimg.com/it/u=3242709860,2221903223&fm=214&gp=0.jpg";
        String path = "http://p3.so.qhmsg.com/bdr/326__/t018da60b972e086a1d.jpg";
        Glide.with(getContext())
                .load(path)
                .placeholder(R.drawable.head_1)
                .error(R.drawable.hed)
                .crossFade()
                .into(circleimage);
        circleimage.setImageURI(Uri.parse(path));

        Glide.with(getContext())
                .load(path1)
                .transform(new GlideCircleTransform(getContext()))
                .placeholder(R.drawable.head_1)
                .error(R.drawable.hed)
                .into(proFileImage);
         /*
         File file = new File(Environment.getExternalStorageDirectory(), "xiayu.png");
         Glide.with(this).load(file).into(iv);
        */
        view.findViewById(R.id.btn1).setOnClickListener(this);
        view.findViewById(R.id.btn2).setOnClickListener(this);
        view.findViewById(R.id.btn3).setOnClickListener(this);
        view.findViewById(R.id.btn4).setOnClickListener(this);
        view.findViewById(R.id.btn5).setOnClickListener(this);
        view.findViewById(R.id.btn6).setOnClickListener(this);
        view.findViewById(R.id.btn7).setOnClickListener(this);
        view.findViewById(R.id.btn8).setOnClickListener(this);
        view.findViewById(R.id.btn9).setOnClickListener(this);
        view.findViewById(R.id.btn10).setOnClickListener(this);
        view.findViewById(R.id.btn11).setOnClickListener(this);
        view.findViewById(R.id.btn12).setOnClickListener(this);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SimpleActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClickActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NetworkActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



    @Override
    public void setTitle(String title) {
        setTitle("大学");
        super.setTitle(title);
    }

    @Override
    protected void initData() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                openActivity(ActivityOne.class);
                break;
            case R.id.btn2:
                openActivity(ActivityTwo.class);
                break;
            case R.id.btn3:
                openActivity(ActivityListViewRefresh.class);
                break;
            case R.id.btn4:
                openActivity(LoginActivity.class);
                break;
            case R.id.btn5:
                openActivity(ActivityImageLoader.class);
                break;
            case R.id.btn6:
                openActivity(ActivityTextInputLayout.class);
                break;
            case R.id.btn7:
                openActivity(OKHttpActivity.class);
                break;
            case R.id.btn8:
                openActivity(ActivityGreenDao.class);
                break;
            case R.id.btn9:
                openActivity(ActivityRetrofit.class);
                break;
            case R.id.btn10:
                openActivity(FactorySendActivity.class);
                break;
            case R.id.btn11:
                openActivity(AdapterTestActivity.class);
                break;
            case R.id.btn12:
                openActivity(DecortorTestActivity.class);
                break;
            default:
                break;
        }
    }

    /***
     * 在onDestroy中注销传感器的监听事件
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
