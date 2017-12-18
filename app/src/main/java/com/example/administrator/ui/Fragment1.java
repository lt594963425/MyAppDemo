package com.example.administrator.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.example.administrator.MainActivity;
import com.example.administrator.MyApplication;
import com.example.administrator.R;
import com.example.administrator.base.BaseFragment;
import com.example.administrator.utils.AndroidBug5497Workaround;
import com.example.administrator.utils.LogUtils;
import com.example.administrator.utils.Md5Utils;
import com.example.administrator.utils.ToastUtils;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Fragment1
 * Created by liu_tao on 16/5/23.
 */
public class Fragment1 extends BaseFragment {
    @BindView(R.id.sv)
    ScrollView mSv;
    Unbinder unbinder;
    @BindView(R.id.notify)
    Button mNotify;
    private OkHttpClient mOkHttpClient;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.url_et)
    EditText mUrlEt;
    @BindView(R.id.iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.url_btn)
    Button mUrlBtn;
    @BindView(R.id.md5_btn)
    Button mMd5Btn;
    @BindView(R.id.log_tv)
    TextView mLogTv;
    @BindView(R.id.tv_message)
    TextSwitcher mTvMessage;
    @BindView(R.id.btn_rx)
    Button mBtnRx;
    @BindView(R.id.cacheTest)
    Button mCacheTest;
    @BindView(R.id.text_layout)
    TextView mTextLayout;
    private static final String TAG = "Fragment1";
    String[] mImages = new String[]{
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg",
            "http://pic29.photophoto.cn/20131125/0022005500418920_b.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558030884&di=b10f693abcebd09dfb309d89702672e5&imgtype=0&src=http%3A%2F%2Fpic29.nipic.com%2F20130511%2F12011435_141504339147_2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558204252&di=8a6ce8463360d42b7518665a469391fc&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F04%2F37%2F04658PICQHc.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498558224830&di=b546d2811f9fa910decc55b981f8df8c&imgtype=0&src=http%3A%2F%2Fpic2.ooopic.com%2F11%2F77%2F47%2F63bOOOPIC74_1024.jpg"};

    protected static final int SUCCESS = 1;
    protected static final int ERROR_CITY = 2;
    protected static final int ERROR = 3;//
    protected static final int TIMEOK = 4;
    protected static final int MD5 = 5;
    String[] textStr = {"双11回馈活动产品利率增长0.05%", "国家大数据发展纲要", "郑重公告", "某某网站会员须知", "网站维护公告"};
    private Handler handler;
    private int index = 0;
    private Unbinder mUnbinder;

    //String url = "http://wthrcdn.etouch.cn/weather_mini?city=%E6%B7%B1%E5%9C%B3";
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        AndroidBug5497Workaround.assistActivity(view.findViewById(android.R.id.content));
        mUnbinder = ButterKnife.bind(this, view);
        mOkHttpClient = new OkHttpClient();
        setBanner();
        timerOperable();
        handler = new MyHandler((MainActivity) getContext());
        initLooperTextView();
        // ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(looppertextview);
//        RxTextView.textChanges(mUrlEt)
//                .subscribe(new Action1<CharSequence>() {
//                    @Override
//                    public void call(CharSequence charSequence) {
//                        if (!mUrlEt.getText().toString().isEmpty()) {
//                            mIvDelete.setVisibility(View.VISIBLE);
//                        } else if (mUrlEt.getText().toString().length() > 0) {
//                            mIvDelete.setVisibility(View.VISIBLE);
//                        } else {
//                            mIvDelete.setVisibility(View.INVISIBLE);
//
//                        }
//                    }
//                });
        RxTextView.textChanges(mUrlEt)
                .throttleFirst(1,TimeUnit.SECONDS)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        if (!mUrlEt.getText().toString().isEmpty()) {
                            mIvDelete.setVisibility(View.VISIBLE);
                        } else if (mUrlEt.getText().toString().length() > 0) {
                            mIvDelete.setVisibility(View.VISIBLE);
                        } else {
                            mIvDelete.setVisibility(View.INVISIBLE);
                        }
                    }
                });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    //缓存
    public void getWeatherResult() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account","admin");
            jsonObject.put("pwd","123456");
            jsonObject.put("client",2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        final String city = mUrlEt.getText().toString().trim();
//        if (TextUtils.isEmpty(city)) {
//            ToastUtils.showToast("请输入要查询的城市");
//            return;
//        }

    }

    @Override
    protected void initData() {

    }


    private void setBanner() {
        List<Integer> mList = new ArrayList(Arrays.asList(mImages));
        mBanner.setOffscreenPageLimit(3);
        mBanner.setImages(new ArrayList<>(mList))
                .setImageLoader(new GlideImageLoader()).start();
        mBanner.setBannerAnimation(Transformer.ScaleInOut);
    }

    @OnClick({R.id.url_et, R.id.iv_delete, R.id.url_btn, R.id.md5_btn, R.id.btn_rx, R.id.cacheTest, R.id.notify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.url_et:
                break;
            case R.id.iv_delete:
                mUrlEt.setText("");
                break;
            case R.id.url_btn:
                getUrlBtn();
                break;
            case R.id.md5_btn:
                getMd5Btn();
                break;
            case R.id.btn_rx:
                countTime();
                RXjavaTest();
                break;
            case R.id.cacheTest:
                getWeatherResult();
                break;
            case R.id.notify:
                getnotify();
                break;
            default:
                break;
        }
    }

    private void getnotify() {
        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        Notification notification = builder
                .setContentTitle("这是通知标题")
                .setContentText("这是通知内容")
                .setWhen(System.currentTimeMillis())
                .setColor(Color.parseColor("#EAA935"))
                .setSmallIcon(R.mipmap.ic_launcher) //小图
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)) //大图
                .build();
        manager.notify(1, notification);
    }

    private void getMd5Btn() {
        String s1 = mUrlEt.getText().toString().trim();
        if (TextUtils.isEmpty(s1)) {
            return;
        }
        String md5pwd = Md5Utils.encryptpwd(s1);
        Message msg1 = Message.obtain();
        msg1.obj = md5pwd;
        msg1.what = MD5;
        handler.sendMessage(msg1);
    }

    private void getUrlBtn() {
        long time1 = System.currentTimeMillis();
        String s = String.valueOf(time1);
        Message msg = Message.obtain();
        msg.obj = s;
        msg.what = TIMEOK;
        handler.sendMessage(msg);
        String newurl = mUrlEt.getText().toString().trim();
        if (TextUtils.isEmpty(newurl)) {
            ToastUtils.showToast("请输入要测试的url地址");
            return;
        }
        loginUUrl(newurl);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).centerCrop().into(imageView);
        }

    }

    private Runnable looppertextview = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, textStr[index]);
                            String s = textStr[index];
                            mTvMessage.setText(s);
                            index++;
                            if (index == textStr.length) {
                                index = 0;
                            }

                        }
                    });
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    };

    public void timerOperable() {
        Observable.interval(1, 2, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong % (long) textStr.length;
                    }
                })
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .compose(this.<Long>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        index = (int) (long) aLong;
                        LogUtils.e("timerOperable:" + textStr[index]);
                        mTvMessage.setText(textStr[index]);
                    }
                });


    }

    //联合检测 RXJava2
    public void contacCheck() {

        Observable.combineLatest(Observable.just(1), Observable.just(2), Observable.just(2),
                new Function3<Integer, Integer, Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer, Integer integer2, Integer integer3) throws Exception {
                        return true;
                    }
                }).compose(this.<Boolean>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        LogUtils.e("contacCheck:" + aBoolean);
                    }
                });
    }

    private void initLooperTextView() {
        mTvMessage.setFactory(new ViewSwitcher.ViewFactory() {
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

        mTvMessage.setOnClickListener(new View.OnClickListener() {
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
        mBanner.isAutoPlay(true);
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    public void loginUUrl(String url) {
        try {
            Request requst = new Request.Builder()
                    .url(url)
                    .get()//http://192.169.6.119/login/login/login/tel/15974255013/pwd/123456/code/wrty
                    .build();
            mOkHttpClient.newCall(requst).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.showToast("网络不佳，登录失败");
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    handler.sendMessage(msg);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
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
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    e.onNext(i);
                    Thread.sleep(2000);
                }
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .filter(new Predicate<Integer>() { // 过滤的作用
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .compose(this.<Integer>bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToastUtils.showToast("" + integer);
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


    private class MyHandler extends Handler {
        //弱引用防止内存泄漏
        WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            switch (msg.what) {
                case ERROR:
                    Toast.makeText(activity, "网络异常", Toast.LENGTH_SHORT).show();
                    break;

                case ERROR_CITY:
                    String str = (String) msg.obj;
                    mLogTv.setText(str);
                    Toast.makeText(activity, "检查url是否正确", Toast.LENGTH_SHORT).show();
                    break;
                case SUCCESS:
                    String strw = (String) msg.obj;
                    mLogTv.setText(strw);
                    break;
                case TIMEOK:
                    String strTime = (String) msg.obj;
                    mTime.setText(strTime);
                    break;
                case MD5:
                    String md = (String) msg.obj;
                    mLogTv.setText(md);
                    break;
                default:
                    break;
            }
        }

    }

    private void countTime() {
       countdown(10)

               .doOnSubscribe(new Consumer<Disposable>() {
                   @Override
                   public void accept(Disposable disposable) throws Exception {
                       LogUtils.e("开始计时");
                   }
               })

               .subscribe(new Observer<Integer>() {
                   @Override
                   public void onSubscribe(Disposable d) {

                   }

                   @Override
                   public void onNext(Integer integer) {
                       LogUtils.e("当前计时：" + integer);
                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onComplete() {
                       LogUtils.e("计时完成");
                   }
               });



    }

    /***
     * 倒计时
     */

   public Observable<Integer> countdown(int time) {
        if (time < 0) {
            time = 0;
        }
        final int countTime = time;
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong){
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime + 1);

    }


}

