package com.jpc.anr.developer;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReadFile {
    private static String TAG = "ReadFile";

    public static String getFromAssets(Activity activity, String fileName) {
        Log.d(TAG, "1 " + Thread.currentThread().getName());
        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufReader.readLine()) != null) {
//                sb.append(line);
            }
//            Log.d(TAG, "2 " + Thread.currentThread().getName());
//            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "3 " + Thread.currentThread().getName());
        return null;
    }

}
