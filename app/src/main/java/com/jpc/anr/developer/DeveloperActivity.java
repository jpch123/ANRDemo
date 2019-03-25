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

import com.jpc.anr.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DeveloperActivity extends AppCompatActivity {
    Button btn_1;
    Button btn_2;
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
