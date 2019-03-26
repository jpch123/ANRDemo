package com.jpc.anr;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
import com.jpc.anr.anrwatchdog.ANRWatchDog;
import com.jpc.anr.blockcanary.MyBlockCanaryContext;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
//        new ANRWatchDog().start();
        super.onCreate();
        sContext = this;
        // 在主进程初始化调用哈
        BlockCanary.install(this, new MyBlockCanaryContext()).start();
    }

    public static Context getAppContext() {
        return sContext;
    }
}