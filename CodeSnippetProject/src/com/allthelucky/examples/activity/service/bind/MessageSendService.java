package com.allthelucky.examples.activity.service.bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 信息服务类
 */
public class MessageSendService extends Service implements IMessageSender {
    private static final String TAG = "InfoService";
    private boolean enable = false;
    private ServiceBinder binder = new ServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    final class ServiceBinder extends Binder {
        public MessageSendService getService() {
            return MessageSendService.this;
        }
    }

    @Override
    public void setEnable(boolean flag) {
        this.enable = flag;
    }

    @Override
    public void showInfo() {
        if (!this.enable) {
            Log.d(TAG, "not show info for user");
        } else {
            for(int index=0; index < 10; index++) {
                Log.d(TAG, "index:"+index);
            }
        }
    }

}
