package com.jpc.anr;

import android.app.Application;

import com.jpc.anr.anrwatchdog.ANRWatchDog;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        new ANRWatchDog().start();
        super.onCreate();
    }
}