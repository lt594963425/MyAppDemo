package com.example.administrator.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.administrator.MainActivity;
import com.example.administrator.MyApplication;
import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.okHttp.OkHttpUtils;
import com.example.administrator.okHttp.callback.StringCallback;
import com.example.administrator.proxy.ThreadPoolProxyFactory;
import com.example.administrator.utils.Md5Utils;
import com.example.administrator.utils.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Fragment1
 * Created by liu_tao on 16/5/23.
 */
public class Fragment1 extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "Fragment1";
    String[] imagess = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg",
            "http://pic29.photophoto.cn/20131125/0022005500418920_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558030884&di=b10f693abcebd09dfb309d89702672e5&imgtype=0&src=http%3A%2F%2Fpic29.nipic.com%2F20130511%2F12011435_141504339147_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558204252&di=8a6ce8463360d42b7518665a469391fc&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F04%2F37%2F04658PICQHc.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg"};

    private final OkHttpClient client = new OkHttpClient();
    private EditText urlEt;
    private TextView urlLog, time;
    private Button urlBtn, RxBtn, md5Btn;
    private ImageView ivDelete;
    protected static final int SUCCESS = 1;
    protected static final int ERROR_CITY = 2;
    protected static final int ERROR = 3;//
    protected static final int TIMEOK = 4;
    protected static final int MD5 = 5;
    //天气预报
    String url = "http://wthrcdn.etouch.cn/weather_mini?city=%E6%B7%B1%E5%9C%B3";

    private Banner banner;
    String[] textStr = {"双11回馈活动产品利率增长0.05%", "国家大数据发展纲要", "郑重公告", "某某网站会员须知", "网站维护公告"};
    private Handler handler;
    private TextSwitcher textSwitcher;
    private int index = 0;
    private TextView textLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        banner = view.findViewById(R.id.banner);
        urlEt = view.findViewById(R.id.url_et);
        urlLog = view.findViewById(R.id.log_tv);
        urlBtn = view.findViewById(R.id.url_btn);
        md5Btn = view.findViewById(R.id.md5_btn);
        ivDelete = view.findViewById(R.id.iv_delete);
        time = view.findViewById(R.id.time);
        textLayout = view.findViewById(R.id.text_layout);
        //svg图标
        VectorDrawableCompat drawable = VectorDrawableCompat.create(getResources(), R.drawable.ic_delete, null);
        ivDelete.setImageDrawable(drawable);
        RxBtn = view.findViewById(R.id.btn_rx);
        RxBtn.setOnClickListener(this);
        urlBtn.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        md5Btn.setOnClickListener(this);
        //MyTextWatcher.setPhoneEdit(getContext(), urlEt);
        MyTextWatchers myTextWatcher = new MyTextWatchers();
        urlEt.addTextChangedListener(myTextWatcher);
        setBanner();
        handler = new MyHandler((MainActivity) getContext());
        textSwitcher = view.findViewById(R.id.tv_message);
        initLooperTextView();
        view.findViewById(R.id.cacheTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWeatherResult();

            }
        });
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(LoopperTextView);
        return view;
    }

    public void getWeatherResult() {
        String city = urlEt.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            ToastUtils.showToast("请输入要查询的城市");
            return;
        }
        try {
            OkHttpUtils.get()
                    .url("http://wthrcdn.etouch.cn/weather_mini?city=" + URLEncoder.encode(city, "utf-8"))
                    .addCacheHeader(100)//100秒
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            textLayout.setText(response);
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {

    }


    private void setBanner() {
        List<Integer> mList = new ArrayList(Arrays.asList(imagess));

        banner.setOffscreenPageLimit(3);
        banner.setImages(new ArrayList<>(mList))
                .setImageLoader(new GlideImageLoader()).start();
        banner.setBannerAnimation(Transformer.ScaleInOut);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).centerCrop().into(imageView);
        }

    }

    // rxjava 实现  内存泄露？？
    private Runnable LoopperTextView = new Runnable() {
        @Override
        public void run() {
            while (true) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textSwitcher.setText(textStr[index]);
                        index++;
                        if (index == textStr.length) {
                            index = 0;
                        }
                        Log.e(TAG, textStr[index]);
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    private void initLooperTextView() {
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MyApplication.getContext());
                textView.setSingleLine();
                textView.setTextSize(22);//字号
                textView.setTextColor(Color.parseColor("#ff3333"));//红色
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });

        textSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        ToastUtils.showToast(textStr[0]);
                        break;
                    case 1:
                        ToastUtils.showToast(textStr[1]);
                        break;
                    case 2:
                        ToastUtils.showToast(textStr[2]);
                        break;
                    case 3:
                        ToastUtils.showToast(textStr[3]);
                        break;
                    case 4:
                        ToastUtils.showToast(textStr[4]);
                        break;
                    default:
                        ToastUtils.showToast("期待");
                        break;
                }
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        banner.isAutoPlay(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().remove(LoopperTextView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url_btn:
                long time1 = System.currentTimeMillis();
                String s = String.valueOf(time1);
                Message msg = Message.obtain();
                msg.obj = s;
                msg.what = TIMEOK;
                handler.sendMessage(msg);
                String newurl = urlEt.getText().toString().trim();
                if (TextUtils.isEmpty(newurl)) {
                    ToastUtils.showToast("请输入要测试的url地址");
                    return;
                }
                loginUUrl(newurl);
                break;
            case R.id.iv_delete:
                urlEt.setText("");
                break;
            case R.id.btn_rx:
                RXjavaTest();
                break;
            case R.id.md5_btn:
                String s1 = urlEt.getText().toString().trim();
                if (TextUtils.isEmpty(s1)) {
                    return;
                }
                String md5pwd = Md5Utils.encryptpwd(s1);
                Message msg1 = Message.obtain();
                msg1.obj = md5pwd;
                msg1.what = MD5;
                handler.sendMessage(msg1);
                break;
            default:
                break;
        }
    }


    public void loginUUrl(String url) {
        try {
            Request requst = new Request.Builder()
                    .url(url)
                    .get()//http://192.169.6.119/login/login/login/tel/15974255013/pwd/123456/code/wrty
                    .build();
            client.newCall(requst).enqueue(new Callback() {
                @Override
                public void onFailure(okhttp3.Call call, IOException e) {
                    ToastUtils.showToast("网络不佳，登录失败");
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    handler.sendMessage(msg);
                }

                @Override
                public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    String s = response.body().string().trim();
                    try {
                        JSONObject object = new JSONObject(s);
                        InputStream is = response.body().byteStream();//字节流
                        Message msg = Message.obtain();
                        msg.obj = s;
                        msg.what = SUCCESS;
                        handler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Message msg = Message.obtain();
                        msg.what = ERROR_CITY;
                        msg.obj = s;
                        handler.sendMessage(msg);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();


        }
    }


    public void RXjavaTest() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    for (int i = 0; i < 1000; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer%2 == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        ToastUtils.showToast(integer + "");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

   /*      Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                //执行一些其他操作
//                String s = OKHttpNet.OkHttpNetGet(url);
//                if (s.isEmpty()) return;
//                e.onNext(s);
                for (int i = 0; i < 1000; i++) {
                    e.onNext(i);
                    Thread.sleep(2000);
                }

            }
        }).subscribeOn(Schedulers.io())
                //过滤器
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer i) throws Exception {
                        return i % 2 == 0;
                    }
                })// .sample(2, TimeUnit.SECONDS)  //sample取样
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer s) throws Exception {
                        ToastUtils.showToast(s + "");
                    }
                });*/
    }

    /**
     * 监听手机号码的长度
     */
    CharSequence temp = "";


    public class MyTextWatchers implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() > 0 && !urlEt.getText().toString().isEmpty()) {
                ivDelete.setVisibility(View.VISIBLE);
                temp = "";
            } else if (urlEt.getText().toString().length() > 0) {
                ivDelete.setVisibility(View.VISIBLE);
            } else {
                ivDelete.setVisibility(View.INVISIBLE);

            }
        }
    }


    private class MyHandler extends Handler {
        //弱引用防止内存泄漏
        WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(android.os.Message msg) {
            MainActivity activity = mActivity.get();
            switch (msg.what) {
                case ERROR:
                    Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
                    break;

                case ERROR_CITY:
                    String str = (String) msg.obj;
                    urlLog.setText(str);
                    Toast.makeText(activity, "检查url是否正确", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    String strw = (String) msg.obj;
                    urlLog.setText(strw);
                    break;
                case TIMEOK:
                    String strTime = (String) msg.obj;
                    time.setText(strTime);
                    break;
                case MD5:
                    String md = (String) msg.obj;
                    urlLog.setText(md);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

