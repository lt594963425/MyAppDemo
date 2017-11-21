package com.example.administrator;

import android.app.Application;
import android.os.Process;

import com.example.administrator.okHttp.OkHttpUtils;
import com.example.administrator.okHttp.cookie.CookieJarImpl;
import com.example.administrator.okHttp.cookie.store.SPCookieStore;
import com.example.administrator.okHttp.https.HttpsUtils;
import com.example.administrator.okHttp.log.LoggerInterceptor;
import com.example.administrator.proxy.ThreadPoolProxyFactory;
import com.example.administrator.utils.ACache;
import com.example.administrator.utils.FileUtils;
import com.example.administrator.utils.LogUtils;
import com.squareup.leakcanary.LeakCanary;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by Fox on 2016/3/4.
 */
public class MyApplication extends Application {
    private static MyApplication context;
    private static ACache mCache;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(new CookieJarImpl(new SPCookieStore(this)))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
               .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) //https
                //     .cache(new Cache(context.getCacheDir(),10*1024*1024))  //安全的缓存，程序内部
                .cache(new Cache(new File(FileUtils.getCachePath(this), "okhttpCache"), 10 * 1024 * 1024))
                .build();
        OkHttpUtils.initClient(okHttpClient);
        ThreadPoolProxyFactory.getNormalThreadPoolProxy().execute(initThirdService);

    }

    public Runnable initThirdService = new Runnable() {
        @Override
        public void run() {
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            mCache = ACache.get(getApplicationContext());
            initLog();//log的初始化
        /*
         * 内存泄漏检测
         */
            if (LeakCanary.isInAnalyzerProcess(getApplicationContext())) {
                return;
            }
            LeakCanary.install((Application) getContext());
        }
    };

    public static MyApplication getContext() {
        return context;
    }

    /**
     * 获得缓存类
     */
    public static ACache getAcache() {
        return mCache;
    }

    public void initLog() {
        LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(LogUtils.V)// log文件过滤器，和logcat过滤器同理，默认Verbose
                .setStackDeep(1);// log栈深度，默认为1
        LogUtils.d(config.toString());
    }
}
