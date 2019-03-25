package com.jpc.anr;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

public class ANRTestAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            Thread.sleep(20*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        SystemClock.sleep(20*1000);
    }
}
