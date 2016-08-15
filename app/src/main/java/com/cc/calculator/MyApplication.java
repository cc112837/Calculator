package com.cc.calculator;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.StrictMode;

import com.cc.calculator.constant.Constants;
import com.cc.calculator.utils.ImgConfig;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * 项目名称：Calculator
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/8/10 10:14
 * 修改人：Administrator
 * 修改时间：2016/8/10 10:14
 * 修改备注：
 */

public class MyApplication extends Application implements
        Thread.UncaughtExceptionHandler {
    private static MyApplication instance;
    public static SharedPreferences sharedPreferences;

    public static boolean debug = true;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        ImgConfig.initImageLoader();


        initImageLoader(instance);
        if (MyApplication.debug) {
            openStrictMode();
        }

    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void openStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // or
                        // .detectAll()
                        // for
                        // all
                        // detectable
                        // problems
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog()
                        // .penaltyDeath()
                .build());
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2)
                // .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}


