package com.example.administrator.ui.fragment2.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.base.BaseActivity;
import com.example.administrator.proxy.ThreadPoolProxyFactory;
import com.example.administrator.utils.PhotoUtil;
import com.example.administrator.utils.ProgressDialogUtils;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/17/017
 */

public class TestORCActivity extends BaseActivity {
    private static final String TAG = "TestORCActivity";
    @BindView(R.id.orc_clic)
    Button mOrcClic;
    @BindView(R.id.orc_text)
    TextView mOrcText;
    @BindView(R.id.orc_img)
    ImageView mOrcImg;
    @BindView(R.id.orc_img2)
    ImageView mOrcImg2;

    @BindView(R.id.seek_one)
    SeekBar mSeekOne;
    @BindView(R.id.seek_two)
    SeekBar mSeekTwo;
    @BindView(R.id.seek_three)
    SeekBar mSeekThree;
    private TessBaseAPI mTess;
    private String mResult;
    private Handler mHandler;
    private Dialog mDialog;


    int saturation;
    int hue;
    int lum;
    private Bitmap mNewBitmap;
    private Bitmap mSBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_orc);
        ButterKnife.bind(this);
        mHandler = new Handler();
        mDialog = ProgressDialogUtils.createLoadingDialog(this, "正在初始化需要的资源....");
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().submit(init);
        mSeekOne.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e(TAG, "saturation:" + progress);
                saturation = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekTwo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e(TAG, "hue:" + progress);
                hue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mSeekThree.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e(TAG, "lum:" + progress);
                lum = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().remove(LoadRunable);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().remove(init);
        ProgressDialogUtils.closeDialog(mDialog);
        super.onDestroy();
    }

    private Runnable init = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "init:" + Thread.currentThread().getName());
            mTess = new TessBaseAPI(); //这里拷贝到手机/android/tesseract/tessdata/目录
            //存放tessdata的文件路径 就是chi_sim.traineddata文件的位置chi_sim.traineddata
            String datapath = Environment.getExternalStorageDirectory() + "/tesseract/";
            //选择语言 chi_sim 简体中文  eng 英文
            String language = "chi_sim";
            File dir = new File(datapath + "tessdata/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            boolean result = mTess.init(datapath, language);
            if (result) {
                ProgressDialogUtils.closeDialog(mDialog);
            }
        }
    };

    //图片处理1
    @OnClick(R.id.orc_clic)
    public void onViewClicked() {
        Bitmap bitmap = BitmapFactory.decodeResource(TestORCActivity.this.getResources(), R.drawable.card);
        mNewBitmap = PhotoUtil.handleImage(bitmap, saturation, hue, lum);
        mOrcImg.setImageBitmap(mNewBitmap);

    }

    //图片处理2
    public void clear(View view) {
        if (mNewBitmap != null) {
            mSBitmap = PhotoUtil.binarization(mNewBitmap);
            mOrcImg2.setImageBitmap(mSBitmap);
        }
    }

    public void see(View view) {
        if (mSBitmap != null) {
            getOrcResult(mSBitmap);
        }

    }

    private Runnable LoadRunable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, Thread.currentThread().getName());
            //获取识别的文字（这里会等一段时间，这里的代码是在主线程的，建议将这部分代码放到子线程）
            mResult = mTess.getUTF8Text();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "run: " + mResult);
                    mOrcText.setText("识别结果为:" + mResult);
                }
            });
        }
    };


    //识别
    public void getOrcResult(Bitmap newBitmap) {
        mTess.setImage(newBitmap);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().submit(LoadRunable);


    }


}
