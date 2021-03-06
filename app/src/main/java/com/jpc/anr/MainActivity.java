package com.jpc.anr;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jpc.anr.developer.DeveloperActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button openAnrTestActivity;
    Button openDeveloperActivity;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
        openAnrTestActivity = findViewById(R.id.openAnrTestActivity);
        openAnrTestActivity.setOnClickListener(mClickListener);
        openDeveloperActivity = findViewById(R.id.openDeveloperActivity);
        openDeveloperActivity.setOnClickListener(mClickListener);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.openAnrTestActivity:
                    print();
//                    SystemClock.sleep(20*1000);
//                    Intent intent = new Intent(mActivity, ANRTestAct.class);
//                    startActivity(intent);
                    break;
                case R.id.openDeveloperActivity:
                    Intent intent2 = new Intent(mActivity, DeveloperActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };


    public void print() {
        BufferedReader bufferedReader = null;
        String tmp = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(Environment.getExternalStorageDirectory() + "/123.txt")));
            while ((tmp = bufferedReader.readLine()) != null) {
                Log.i("wangchen", tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
