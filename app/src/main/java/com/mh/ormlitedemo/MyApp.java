package com.mh.ormlitedemo;

import android.content.Context;

import com.alibaba.sdk.android.oss.OSS;

/**
 * Created by JackHuang on 2016/8/24.
 */

public class MyApp extends BaseApplication {
    public static MyApp INSTANCE;
    private static MyApp gInstance;
    private OSS	oss;

    @Override
    protected void create() {

    }

    public static Context getAppContext() {
        return INSTANCE;
    }

    public static MyApp getInstance() {
        return gInstance;
    }

    public OSS getOSS() {
        return oss;
    }
}
