package com.jpc.anr.anrwatchdog;

import android.os.Looper;

public class ANRException extends RuntimeException {
    public ANRException() {
        super("应用程序无响应，快来改BUG啊！！");
        Thread mainThread = Looper.getMainLooper().getThread();
        setStackTrace(mainThread.getStackTrace());
    }
}
