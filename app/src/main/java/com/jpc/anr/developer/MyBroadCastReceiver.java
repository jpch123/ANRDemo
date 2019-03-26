package com.jpc.anr.developer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int type = intent.getIntExtra("type", 0);
        Toast.makeText(context, "TYPE:" + type, Toast.LENGTH_SHORT).show();
        switch (type) {
            case 1:
                //主线程运行
                BubbleSort.sort(20);
                break;
            case 2:
                // The task now runs on a worker thread.
                Intent intentService = new Intent(context, MyIntentService.class);
                context.startService(intentService);
                break;
            case 3:
                final PendingResult pendingResult = goAsync();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        // This is a long-running operation
                        BubbleSort.sort(20);
//                        pendingResult.finish();
                        return null;
                    }
                }.execute();
                break;
            case 4:
                final PendingResult pendingResult2 = goAsync();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        // This is a long-running operation
                        BubbleSort.sort(20);
                        pendingResult2.finish();
                        return null;
                    }
                }.execute();
                break;
            default:

                break;
        }

    }


}
