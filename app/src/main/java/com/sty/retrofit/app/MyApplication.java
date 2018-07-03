package com.sty.retrofit.app;

import android.app.Application;

import com.sty.retrofit.api.ApiClient;

/**
 * Created by tian on 2018/7/3.
 */

public class MyApplication extends Application {
    private static MyApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
        ApiClient.init();
    }

    public static MyApplication getmApp(){
        return mApp;
    }
}
