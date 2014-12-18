package com.allthelucky.examples.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.allthelucky.examples.R;

/**
 * 浮动布局(button)测试
 */
public class FloatButtonActivity extends Activity {
    private WindowManager windowManager;
    private View floatView;
    private WindowManager.LayoutParams lp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.float_main);
        
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 反解View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        floatView = inflater.inflate(R.layout.float_button, null);
        Button button = (Button) floatView.findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FloatButtonActivity.this, "touched", Toast.LENGTH_SHORT).show();
            }
        });
        
    	lp = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		lp.gravity = Gravity.RIGHT | Gravity.TOP;
		windowManager.addView(floatView, lp);

		button.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, android.view.MotionEvent event) {
				// TODO Auto-generated method stub
				lp.x = (int) event.getRawX();
				lp.y = (int) event.getRawY();
				windowManager.updateViewLayout(floatView, lp);
				return false; // 此处必须返回false，否则OnClickListener获取不到监听
			}
		});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (windowManager != null && floatView != null) {
            windowManager.removeViewImmediate(floatView);
        }
    }
}
