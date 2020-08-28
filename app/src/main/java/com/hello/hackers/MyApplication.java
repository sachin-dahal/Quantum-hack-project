package com.hello.hackers;

import android.app.Application;

import androidx.lifecycle.LifecycleObserver;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}
