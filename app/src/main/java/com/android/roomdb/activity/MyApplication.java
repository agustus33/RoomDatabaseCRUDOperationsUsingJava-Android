package com.android.roomdb.activity;

import android.app.Application;

public class MyApplication extends Application {
    //app class
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
