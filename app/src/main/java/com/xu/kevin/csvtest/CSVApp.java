package com.xu.kevin.csvtest;

import android.app.Application;

import com.socks.library.KLog;

/**
 * Created by kevin on 2018/2/22.
 */

public class CSVApp extends Application {

    private static CSVApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化log
        KLog.init(false, "kevin");
        mInstance = this;

    }


    public static CSVApp getInstance() {
        return mInstance;
    }
}