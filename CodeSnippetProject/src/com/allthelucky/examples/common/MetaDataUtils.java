package com.allthelucky.examples.common;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;

/**
 * MetaDataUtils 
 * @author panxw
 *
 */
public class MetaDataUtils {
	
	private MetaDataUtils(){
		
	}
	
	// <activity...>
	// <meta-data android:name="myMsg"
	// android:value="hello my activity"></meta-data>
	// </activity>
	public static Bundle getApplicationMetaData(Application application)
			throws NameNotFoundException {
		ApplicationInfo appInfo = application.getPackageManager()
				.getApplicationInfo(application.getPackageName(),
						PackageManager.GET_META_DATA);
		// String msg=info.metaData.getString("myMsg");
		return appInfo.metaData;
	}

	// <application...>
	// <meta-data android:value="hello my application"
	// android:name="myMsg"></meta-data>
	// </application>
	public static Bundle getActivityMetaData(Activity activity)
			throws NameNotFoundException {
		ActivityInfo info = activity.getPackageManager().getActivityInfo(
				activity.getComponentName(), PackageManager.GET_META_DATA);
		// String msg=info.metaData.getString("myMsg");
		return info.metaData;
	}

	// <service android:name="MetaDataService">
	// <meta-data android:value="hello my service"
	// android:name="myMsg"></meta-data>
	// </service>
	public static Bundle getReceiverMetaData(Context context,
			Class<?> receiverClss) throws NameNotFoundException {
		ComponentName cn = new ComponentName(context, receiverClss);
		ActivityInfo info = context.getPackageManager().getReceiverInfo(cn,
				PackageManager.GET_META_DATA);
		// String msg=info.metaData.getString("myMsg");
		return info.metaData;
	}

	// <receiver android:name="MetaDataReceiver">
	// <meta-data android:value="hello my receiver"
	// android:name="myMsg"></meta-data>
	// <intent-filter>
	// <action android:name="android.intent.action.PHONE_STATE"></action>
	// </intent-filter>
	// </receiver>
	/**
	 * @param context
	 * @param serviceClss
	 * @return
	 * @throws NameNotFoundException
	 */
	public static Bundle getServiceMetaData(Context context,
			Class<?> serviceClss) throws NameNotFoundException {
		ComponentName cn = new ComponentName(context, serviceClss);
		ServiceInfo info = context.getPackageManager().getServiceInfo(cn,
				PackageManager.GET_META_DATA);
		return info.metaData;
	}

}
