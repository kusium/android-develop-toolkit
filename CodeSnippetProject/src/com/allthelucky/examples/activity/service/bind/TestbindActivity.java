package com.allthelucky.examples.activity.service.bind;

import android.app.Activity;
import android.os.Bundle;

/**
 * InfoService 测试
 */
public class TestbindActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageSendManager.setEnable(true);
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                    MessageSendManager.showInfo();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
