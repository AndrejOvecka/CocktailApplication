package com.example.andre.cocktailapp;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by andre on 15. 1. 2019.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
