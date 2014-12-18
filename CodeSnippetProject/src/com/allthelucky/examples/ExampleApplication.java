package com.allthelucky.examples;

import com.allthelucky.examples.floatbar.FloatBarService;

import android.app.Application;
import android.content.Intent;

public class ExampleApplication  extends Application{

	private Intent floatBarService=null;
	
	@Override
	public void onCreate() {
	
		floatBarService = new Intent(this, FloatBarService.class);
		startService(floatBarService);
		
		super.onCreate();
	}
	
	@Override
	public void onTerminate() {
		stopService(floatBarService);
		super.onTerminate();
	}
	
}
