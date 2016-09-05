package com.mh.ormlitedemo.test.network;

import android.os.Handler;
import android.support.annotation.NonNull;

/**
 * Created by JackHuang on 2016/3/17.
 */
public class HttpProxy {

    public static void action(final Handler handler, final int what,@NonNull final HttpProxy.IRequest  request){
        new Thread(){
            @Override
            public void run() {
                try {
                   Object o =  request.action();
                    handler.obtainMessage(what,o).sendToTarget();
                } catch (Exception e) {
                    handler.sendEmptyMessage(NetHandler.NET_ERROR);
                }
            }
        }.start();
    }

    public  interface IRequest<T>{

        T action();

    }
}
