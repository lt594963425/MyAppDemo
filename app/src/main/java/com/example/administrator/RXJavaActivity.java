package com.example.administrator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.utils.LogUtils;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import rx.functions.Action1;

import static com.example.administrator.net.NetContants.JSON;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/29/029
 */

public class RXJavaActivity extends AppCompatActivity {
    private static final String TAG = "RXJavaActivity";
    @BindView(R.id.rxjava_one_btn)
    Button mRxjavaOneBtn;
    @BindView(R.id.update_version)
    Button mUpdateVersion;
    @BindView(R.id.rx_text)
    TextView mRxText;
    private JSONObject mJsonObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        ButterKnife.bind(this);
        RxView.clicks(mRxjavaOneBtn)
                .throttleFirst(30, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        countTime(30);
                        LogUtils.e("开始计时");
                    }
                });
        mUpdateVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"12345");
                mJsonObject = new JSONObject();
                try {
                    mJsonObject.put("version_code", "3");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OkHttpUtils.postString()
                        .url("http://192.168.6.46/improve/version_manager/version_update")
                        .content(String.valueOf(mJsonObject))
                        .mediaType(JSON)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG,e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG,response);
                                RxTextView.text(mRxText).call(response);
                            }
                        });
            }
        });
     /*   RxView.clicks(mUpdateVersion)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {



                    }
                });*/
    }

    private void countTime(int time) {
        countdown(time)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "获取验证码");

                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        RxTextView.text(mRxjavaOneBtn).call("剩余" + (integer) + "秒");
                        Log.e(TAG, "当前计时：" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        RxTextView.text(mRxjavaOneBtn).call("重新获取验证码");
                        Log.e(TAG, "计时完成");
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
                    public Integer apply(Long aLong) {
                        return countTime - aLong.intValue();
                    }
                })
                .take(countTime + 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * take              取前n个数据
     * takeLast          取后n个数据
     * first             只发送第一个数据
     * last              只发送最后一个数据
     * skip()            跳过前n个数据发送后面的数据
     * <p>
     * debounce          一段时间内没有变化，就会发送一个数据。
     * doOnSubscribe     使用场景： 可以在事件发出之前做一些初始化的工作，比如弹出进度条等等
     *
     * @return
     */
    public List addData() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(6);
        return list;
    }

    /*********************************************************************************************
     * create,基本操作符
     */
    public void createOperable() {


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("sss");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        //相应操作
                        Log.e(TAG, "缓存");
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("create:" + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /*****************************************************************************************
     * map,在其中做某些转换
     */
    public void mapOperable() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(0);
                e.onNext(1);
                e.onNext(2);
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer * 10;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                LogUtils.e("map:" + integer);
            }
        });
    }


    public void flatmapOprable() {


    }

    /**
     * zip , 组合发射
     */
    public void zipOperable() {
        Observable.zip(Observable.just(1, 2, 3),
                Observable.just("A", "B", "C"),
                new BiFunction<Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s) throws Exception {
                        return integer + s;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.e("zip:" + s);
                    }
                });
    }

    /**
     * concat,  一起连接发射出去
     * 组合多个被观察者（≤4个）一起发送数据
     * <p>
     * concatArray 组合多个被观察者一起发送数据（可＞4个）
     */
    public void concatOperable() {
        Observable.concat(Observable.just(1, 2, 3),
                Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "concat : " + integer + "\n");
                    }
                });


    }

    /**
     * merge（）/mergeArray（）：组合多个被观察者（＜4个）一起发送数据
     * 注：合并后按照时间线并行执行
     */

    public void mergeOp() {
        Observable.merge(
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                Observable.intervalRange(2, 3, 1, 1, TimeUnit.SECONDS)) // 从2开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }

    /**
     * combineLatest
     * 当两个Observables中的任何一个发送了数据后，将先发送了数据的Observables 的最新（最后）一个数据
     * 与另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
     */
    public void combineLatestOp() {
        Observable.combineLatest(
                Observable.just(1L, 2L, 3L), // 第1个发送数据事件的Observable
                Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS), // 第2个发送数据事件的Observable：从0开始发送、共发送3个数据、第1次事件延迟发送时间 = 1s、间隔时间 = 1s
                new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long o1, Long o2) throws Exception {
                        // o1 = 第1个Observable发送的最新（最后）1个数据
                        // o2 = 第2个Observable发送的每1个数据
                        Log.e(TAG, "合并的数据是： " + o1 + " " + o2);
                        return o1 + o2;
                        // 合并的逻辑 = 相加
                        // 即第1个Observable发送的最后1个数据 与 第2个Observable发送的每1个数据进行相加
                    }
                }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                Log.e(TAG, "合并的结果是： " + s);
            }
        });

    }

    /**
     * reduce
     * <p>
     * 聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，然后与后1个数据继续进行聚合，依次类推
     * 1*2*3*4*5
     */

    public void reduceOp() {
        Observable.just(1, 2, 3, 4, 5)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s1, Integer s2) throws Exception {
                        Log.e(TAG, "本次计算的数据是： " + s1 + " 乘 " + s2);
                        return s1 * s2;
                        // 本次聚合的逻辑是：全部数据相乘起来
                        // 原理：第1次取前2个数据相乘，之后每次获取到的数据 = 返回的数据x原始下1个数据每
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "最终计算的结果是： " + integer);
                    }
                });


    }

    /**
     * collect
     * 对发送者的数据进行收集
     */
    public void collectOP() {
        Observable.just(1, 2, 3, 4, 5, 6)
                .collect(
                        // 1. 创建数据结构（容器），用于收集被观察者发送的数据
                        new Callable<ArrayList<Integer>>() {
                            @Override
                            public ArrayList<Integer> call() throws Exception {
                                return new ArrayList<>();
                            }
                            // 2. 对发送的数据进行收集
                        }, new BiConsumer<ArrayList<Integer>, Integer>() {
                            @Override
                            public void accept(ArrayList<Integer> list, Integer integer)
                                    throws Exception {
                                // 参数说明：list = 容器，integer = 后者数据
                                list.add(integer);
                                // 对发送的数据进行收集
                            }
                        }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(@NonNull ArrayList<Integer> s) throws Exception {
                Log.e(TAG, "本次发送的数据是： " + s);

            }
        });


    }

    /**
     * startWith（） / startWithArray（）
     * 发送前追加数据
     * <-- 在一个被观察者发送事件前，追加发送一些数据 -->
     * 注：追加数据顺序 = 后调用先追加
     */
    public void startOp() {
        Observable.just(4, 5, 6)
                .startWith(0)  // 追加单个数据 = startWith()
                .startWithArray(1, 2, 3) // 追加多个数据 = startWithArray()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

        // 注：追加数据顺序 = 后调用先追加
        Observable.just(4, 5, 6)
                .startWith(Observable.just(1, 2, 3))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });

    }

    /**
     * count
     * 统计发送的事件数量
     */
    public void countOp() {
        // 注：返回结果 = Long类型
        Observable.just(1, 2, 3, 4)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "发送的事件数量 =  " + aLong);

                    }
                });

    }


    /**
     * fromArray  传数组 ，一个一个发射出去
     * 传list，整体发射出去
     * <p>
     * distinct                过滤重复的数据
     * distinctUntilChanged()  过滤连续重复的数据
     */
    String[] textStr = {"双11回馈活动产品利率增长0.05%", "国家大数据发展纲要", "郑重公告", "某某网站会员须知", "网站维护公告"};

    public void fromArrayOperable() {
        Observable.fromArray(textStr)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        LogUtils.e("fromArray:" + s);
                    }
                });
    }

    /**
     * fromIterable   集合遍历
     * <p>
     * flatmap flatmap能够链式地完成数据类型的转换和加工。
     *
     * @param list
     */
    public void fromIterableOpreable1(final List list) {
        Observable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        LogUtils.e("fromIterable:" + integer);
                    }
                });

    }

//    Observable.fromIterable(new School().getClasses())
//
//            //输入是Class类型，输出是ObservableSource<Student>类型
//            .flatMap(new Function<Class, ObservableSource<Student>>() {
//
//        //输入是Class类型，输出是ObservableSource<Student>类型
//        @Override
//        public ObservableSource<Student> apply(Class aClass) throws Exception {
//            Log.d(TAG, "apply: " + aClass.toString());
//            return Observable.fromIterable(aClass.getStudents());
//        }
//    }).subscribe(
//            new Observer<Student>() {
//        @Override
//        public void onSubscribe(Disposable d) {
//            Log.d(TAG, "onSubscribe: ");
//        }
//
//        @Override
//        public void onNext(Student value) {
//            Log.d(TAG, "onNext: " + value.toString());
//        }
//
//        @Override
//        public void onError(Throwable e) {
//
//        }
//
//        @Override
//        public void onComplete() {
//
//        }
//    });
//}


    /*************************************** 延迟发射 ***********************************************
     *
     *               需求场景
     *                           1、 定时操作：在经过了x秒后，需要自动执行y操作
     *                           2、 周期性操作：每隔x秒后，需要自动执行y操作
     *                                                                                         ***/

    /**
     * timer   延时多少秒后发送 定时器
     * <p>
     * delay 、timer 总结：
     * 相同点：delay 、 timer 都是延时操作符。
     * 不同点：delay  延时一次，延时完成后，可以连续发射多个数据。timer延时一次，延时完成后，只发射一次数据。
     */
    public void timerOp() {

        Observable.timer(2, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        LogUtils.e("fromIterable:" + aLong);
                    }
                });

    }

    /**
     * interval   循环发送
     */
    public void intervalOp() {
        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Observable.interval(3, 1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {

                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return aLong / 4;
                    }
                })
                // 该例子发送的事件序列特点：延迟3s后发送事件，每隔1秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    // 默认最先调用复写的 onSubscribe（）
                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

    }

    /**
     * intervalRange
     * <p>
     * 每隔指定事件发送事件
     */
    public void intervalRangeOp() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS)
                // 该例子发送的事件序列特点：
                // 1. 从3开始，一共发送10个事件；
                // 2. 第1次延迟2s发送，之后每隔2秒产生1个数字（从0开始递增1，无限个）
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    /**
     * range()
     * Observable.rangeLong()  支持 long型
     * a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
     * b. 作用类似于intervalRange（），但区别在于：无延迟发送事件
     */
    public void rangeOp() {
        // 参数说明：
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常

        Observable.range(1, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.e("range:" + integer);
                    }
                });
    }

}
