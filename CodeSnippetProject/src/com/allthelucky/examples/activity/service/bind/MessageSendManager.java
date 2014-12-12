package com.allthelucky.examples.activity.service.bind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

/**
 * InfoService 调用管理类．在调用服务之前需init，退出时stop
 */
public class MessageSendManager {

    protected static final String TAG = MessageSendManager.class.getSimpleName();
    private static IMessageSender infoService;

    public static void init(Context context) {
        Intent i = new Intent("cn.winfirm.examples.servicebind.InfoService");
        context.bindService(i, conn, Context.BIND_AUTO_CREATE);
    }

    public static void stop(Context context) {
        context.unbindService(conn);
    }

    private static ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "InfoService disconnected...");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "InfoService connected...");
            infoService = ((MessageSendService.ServiceBinder) service).getService();
        }
    };

    public static void setEnable(boolean flag) {
        infoService.setEnable(flag);
    }

    public static void showInfo() {
        infoService.showInfo();
    }
}
