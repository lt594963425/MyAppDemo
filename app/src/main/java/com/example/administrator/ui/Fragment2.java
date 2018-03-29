package com.example.administrator.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.RXJavaActivity;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.ui.fragment2.activity.ActivityBaiDuMap;
import com.example.administrator.ui.fragment2.activity.ActivityGoTo;
import com.example.administrator.ui.fragment2.activity.ActivityOpenGles;
import com.example.administrator.ui.fragment2.activity.ActivityOpenGlestwo;
import com.example.administrator.ui.fragment2.activity.ArcCicleActivity;
import com.example.administrator.ui.fragment2.activity.EventBusActivity;
import com.example.administrator.ui.fragment2.activity.LoginDialogActivity;
import com.example.administrator.ui.fragment2.activity.RecyclerViewActivity;
import com.example.administrator.ui.fragment2.activity.TestORCActivity;
import com.example.administrator.ui.fragment2.activity.XuanZhuanActivity;
import com.example.administrator.ui.fragment2.activity.photo.PhotoOrcActivity;
import com.example.administrator.ui.testactivityresult.ActivityFirst;
import com.example.administrator.utils.ToastUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;


/**
 * Fragment2
 * Created by liu_tao on 16/5/23.
 */
public class Fragment2 extends BaseFragment {

    private static final String TAG = "---Fragment2";
    @BindView(R.id.rx_java_btn)
    Button mRxJavaBtn;
    Unbinder unbinder;
    @BindView(R.id.tv_show_s)
    TextView mTvShowS;
    @BindView(R.id.random)
    Button mRandom;
    private SensorManager mSensorManager;
    private Sensor accelerometer; // 加速度传感器
    private Sensor magnetic; // 地磁场传感器
    private TextView azimuthAngle;
    private float[] accelerometerValues = new float[3];
    private float[] magneticFieldValues = new float[3];
    private View view;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this, view);
        RxView.clicks(mRxJavaBtn).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        openActivity(RXJavaActivity.class);
                    }
                });
        view.findViewById(R.id.btn_baidumap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ActivityBaiDuMap.class);
            }
        });
        view.findViewById(R.id.btn_retrofit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ActivityGoTo.class);
            }
        });
        view.findViewById(R.id.btn_rxandroid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ActivityOpenGles.class);
            }
        });
        view.findViewById(R.id.btn_OpenGLES).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ActivityOpenGlestwo.class);
            }
        });
        view.findViewById(R.id.btn_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ArcCicleActivity.class);
            }
        });
        view.findViewById(R.id.btn_xuanzh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(XuanZhuanActivity.class);
            }
        });
        view.findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(RecyclerViewActivity.class);
            }
        });
        view.findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(PhotoOrcActivity.class);
            }
        });
        view.findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(TestORCActivity.class);
            }
        });
        view.findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(EventBusActivity.class);
            }
        });
        view.findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(LoginDialogActivity.class);
            }
        });
        view.findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(ActivityFirst.class);
            }
        });
        mRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random randomGenerator = new Random(System.currentTimeMillis());
                int s = randomGenerator.nextInt() % 180;
                ToastUtils.showToast("角度：" + s);
            }
        });
        initSensor();
        return view;
    }

    private void initSensor() {
        // 实例化传感器管理者
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        // 初始化加速度传感器
        accelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // 初始化地磁场传感器
        magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        azimuthAngle = (TextView) view.findViewById(R.id.azimuth_angle_value);
        calculateOrientation();
    }

    @Override
    protected void initData() {


    }

    @Override
    public void onResume() {

        // 注册监听
        mSensorManager.registerListener(new MySensorEventListener(),
                accelerometer, Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new MySensorEventListener(), magnetic,
                Sensor.TYPE_MAGNETIC_FIELD);
        super.onResume();
    }

    @Override
    public void onStop() {
        // unregister listener
        mSensorManager.unregisterListener(new MySensorEventListener());
        super.onStop();
    }


    // 计算方向
    private void calculateOrientation() {
        float[] values = new float[3];
        float[] R = new float[9];
        SensorManager.getRotationMatrix(R, null, accelerometerValues,
                magneticFieldValues);
        SensorManager.getOrientation(R, values);
        float value = (float) Math.toDegrees(values[0]);

        if (value >= -5 && value < 5) {
            azimuthAngle.setText("↑正北");
        } else if (value >= 5 && value < 85) {
            // Log.i(TAG, "东北");
            azimuthAngle.setText("↑东北");
        } else if (value >= 85 && value <= 95) {
            // Log.i(TAG, "正东");
            azimuthAngle.setText("↑正东");
        } else if (value >= 95 && value < 175) {
            // Log.i(TAG, "东南");
            azimuthAngle.setText("↑东南");
        } else if ((value >= 175 && value <= 180)
                || (value) >= -180 && value < -175) {
            // Log.i(TAG, "正南");
            azimuthAngle.setText("↑正南");
        } else if (value >= -175 && value < -95) {
            // Log.i(TAG, "西南");
            azimuthAngle.setText("↑西南");
        } else if (value >= -95 && value < -85) {
            // Log.i(TAG, "正西");
            azimuthAngle.setText("↑正西");
        } else if (value >= -85 && value < -5) {
            // Log.i(TAG, "西北");
            azimuthAngle.setText("↑西北");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                accelerometerValues = event.values;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                magneticFieldValues = event.values;
            }
            calculateOrientation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

    }
}