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
import android.widget.TextView;

import com.example.administrator.R;
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


/**
 * Fragment2
 * Created by liu_tao on 16/5/23.
 */
public class Fragment2 extends BaseFragment {

    private static final String TAG = "---Fragment2";
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
        values[0] = (float) Math.toDegrees(values[0]);
        if (values[0] >= -5 && values[0] < 5) {
            azimuthAngle.setText("↑正北");
        } else if (values[0] >= 5 && values[0] < 85) {
            // Log.i(TAG, "东北");
            azimuthAngle.setText("↑东北");
        } else if (values[0] >= 85 && values[0] <= 95) {
            // Log.i(TAG, "正东");
            azimuthAngle.setText("↑正东");
        } else if (values[0] >= 95 && values[0] < 175) {
            // Log.i(TAG, "东南");
            azimuthAngle.setText("↑东南");
        } else if ((values[0] >= 175 && values[0] <= 180)
                || (values[0]) >= -180 && values[0] < -175) {
            // Log.i(TAG, "正南");
            azimuthAngle.setText("↑正南");
        } else if (values[0] >= -175 && values[0] < -95) {
            // Log.i(TAG, "西南");
            azimuthAngle.setText("↑西南");
        } else if (values[0] >= -95 && values[0] < -85) {
            // Log.i(TAG, "正西");
            azimuthAngle.setText("↑正西");
        } else if (values[0] >= -85 && values[0] < -5) {
            // Log.i(TAG, "西北");
            azimuthAngle.setText("↑西北");
        }
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