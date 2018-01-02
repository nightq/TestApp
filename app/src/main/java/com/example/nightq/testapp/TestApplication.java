package com.example.nightq.testapp;

import android.app.Application;
import android.util.Log;

import com.microquation.linkedme.android.LinkedME;

/**
 * Created by nightq on 2017/12/29.
 */

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化SDK
        LinkedME.getInstance(this);

        if (BuildConfig.DEBUG) {
            //设置debug模式下打印LinkedME日志
            LinkedME.getInstance().setDebug();
        }
        //初始时设置为false，在配置Uri Scheme的Activity的onResume()中设置为true
        LinkedME.getInstance().setImmediate(false);
        Log.e("nightq", "TestApplication create this = " + this);
    }

}
