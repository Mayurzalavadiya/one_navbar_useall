package com.myapplication;

import android.app.Application;
import android.content.Context;


public class ApplicationController extends Application {

    public static ApplicationController applicationController = null;
    private static Context appContext;


    public static ApplicationController the() {
        return applicationController;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if (applicationController == null) {
            applicationController = this;
        }

        this.setAppContext(getApplicationContext());

    }

    private void setAppContext(Context mAppContext) {
        appContext = mAppContext;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
