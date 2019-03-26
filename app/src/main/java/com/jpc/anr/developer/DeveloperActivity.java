package com.jpc.anr.developer;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jpc.anr.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DeveloperActivity extends AppCompatActivity {
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_10;
    Activity mActivity;
    MyBroadCastReceiver mMyBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_developer);
        btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(mClickListener);
        btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(mClickListener);

        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(mClickListener);
        btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(mClickListener);

        btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(mClickListener);
        btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(mClickListener);

        btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(mClickListener);
        btn_8 = findViewById(R.id.btn_8);
        btn_8.setOnClickListener(mClickListener);
        btn_9 = findViewById(R.id.btn_9);
        btn_9.setOnClickListener(mClickListener);
        btn_10 = findViewById(R.id.btn_10);
        btn_10.setOnClickListener(mClickListener);

        //实例化IntentFilter对象
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.jpc.anr.MyBroadCastReceiver");
        mMyBroadCastReceiver = new MyBroadCastReceiver();
        //注册广播接收
        registerReceiver(mMyBroadCastReceiver, filter);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_1:
                    Debug.startMethodTracing("anrjpc");
                    BubbleSort.sort(1.05f);
                    Debug.stopMethodTracing();
                    break;
                case R.id.btn_2:
                    Debug.startMethodTracing("anrjpcAsync");
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            BubbleSort.sort(2);
                            return null;
                        }
                    }.execute();
                    Debug.stopMethodTracing();
                    break;

                case R.id.btn_3:
                    ReadFile.getFromAssets(mActivity, "123.txt");
                    break;
                case R.id.btn_4:

                    new Thread("readFile") {
                        @Override
                        public void run() {
                            ReadFile.getFromAssets(mActivity, "123.txt");
                        }
                    }.start();
                    break;
                case R.id.btn_5:
                    // The worker thread holds a lock on lockedResource
                    new LockTask().execute();
                    try {
                        Thread.sleep(10); //保证异步任务已经开始
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockedResource) {
                        // The main thread requires lockedResource here
                        // but it has to wait until LockTask finishes using it.
                        Toast.makeText(mActivity, "哈哈", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_6:

                    WaitTask waitTask = new WaitTask();
                    synchronized (waitTask) {
                        try {
                            waitTask.execute();
                            // Wait for this worker thread’s notification
                            waitTask.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(mActivity, "哈哈11", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_7: {
                    Intent broadCast1 = new Intent();
                    broadCast1.setAction("com.jpc.anr.MyBroadCastReceiver");
                    broadCast1.putExtra("type", 1);
                    mActivity.sendBroadcast(broadCast1);
                }
                break;
                case R.id.btn_8: {
                    Intent broadCast1 = new Intent();
                    broadCast1.setAction("com.jpc.anr.MyBroadCastReceiver");
                    broadCast1.putExtra("type", 2);
                    mActivity.sendBroadcast(broadCast1);
                }
                break;
                case R.id.btn_9: {
                    Intent broadCast1 = new Intent();
                    broadCast1.setAction("com.jpc.anr.MyBroadCastReceiver");
                    broadCast1.putExtra("type", 3);
                    mActivity.sendBroadcast(broadCast1);
                }
                break;
                case R.id.btn_10: {
                    Intent broadCast1 = new Intent();
                    broadCast1.setAction("com.jpc.anr.MyBroadCastReceiver");
                    broadCast1.putExtra("type", 4);
                    mActivity.sendBroadcast(broadCast1);
                }
                break;
            }
        }
    };

    private Object lockedResource = new Object();

    public class LockTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            synchronized (lockedResource) {
                // This is a long-running operation, which makes
                // the lock last for a long time
                BubbleSort.sort(20);
                return null;
            }
        }
    }

    class WaitTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            synchronized (this) {
                BubbleSort.sort(20);
                // Finished, notify the main thread
                notify();
                return null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadCastReceiver);
    }
}
