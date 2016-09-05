package com.mh.ormlitedemo.test.network;

import okhttp3.OkHttpClient;

/**
 * Created by JackHuang on 2016/5/25.
 */
public class HttpClient {

    public static volatile OkHttpClient INSTANCE = null;

    public static OkHttpClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OkHttpClient();
            /*INSTANCE.setConnectTimeout(8, TimeUnit.SECONDS);
            INSTANCE.setReadTimeout(8, TimeUnit.SECONDS);
            INSTANCE.setWriteTimeout(8, TimeUnit.SECONDS);
            INSTANCE.setRetryOnConnectionFailure(true);*/

//            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
//
//            // Set log level
//            if (BuildConfig.DEBUG) {
//                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            } else {
//                logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//            }
//
//            INSTANCE.interceptors().add(logInterceptor);
        }

        return  INSTANCE;
    }
}
