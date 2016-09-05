package com.mh.ormlitedemo.test.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Created by adminis on 2016/3/17.
 */
public class NetHandler extends Handler {
    Context context;

    public static final int SHOW_HINT = -1;
    public static final int NET_ERROR = 0;

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case SHOW_HINT:{
                Toast.makeText(context,msg.obj.toString(),Toast.LENGTH_SHORT).show();
//                ToastUtil.showToast(MyApp.INSTANCE,msg.obj.toString());
            }break;
            case NET_ERROR:{
                Toast.makeText(context,"网络链接错误",Toast.LENGTH_SHORT).show();
//                ToastUtil.showToast(MyApp.INSTANCE,"网络链接错误");
            }break;
        }
    }

    public void showHint(String msg){
        obtainMessage(SHOW_HINT,msg).sendToTarget();
    }
}
