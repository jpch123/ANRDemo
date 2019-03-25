package com.jpc.anr.developer;

import android.app.Activity;
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
    Activity mActivity;

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
}
