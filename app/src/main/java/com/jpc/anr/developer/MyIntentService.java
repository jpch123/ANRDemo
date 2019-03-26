package com.jpc.anr.developer;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public  class MyIntentService extends IntentService {

        public MyIntentService() {
            super("MyIntentService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            BubbleSort.sort(20);
        }
    }