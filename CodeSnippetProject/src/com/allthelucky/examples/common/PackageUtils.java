package com.allthelucky.examples.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;

public class PackageUtils {
	
	/**
	 * uninstall apk silently
	 * 
	 * @param packageName
	 */
	public static void deleteAppSilent(String packageName) {
		String cmd = "adb shell pm uninstall " + packageName;
		exccmd(cmd);
	}

	private static int exccmd(String cmd) {
		int ret = -1;
		try {
			Process	process = Runtime.getRuntime().exec(cmd);
			try {
				ret = process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static final String getAndroidBuildModel() {
		try {
			return URLEncoder.encode(Build.MODEL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void startAppByPackageName(final Context context,
			final String packageName) {
		PackageManager pm = context.getPackageManager();
		Intent intent = pm.getLaunchIntentForPackage(packageName);
		try {
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	public static long getInstallAPPTime(Context context,
			final String packageName) {
		long installTime = 0;
		try {
			if (installTime <= 0 && Build.VERSION.SDK_INT >= 9) {
				PackageInfo pi = context.getPackageManager().getPackageInfo(
						packageName, 0);
				installTime = pi.firstInstallTime / 1000;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return installTime;
	}

	public static JSONArray getUserApps(Context context) {
		PackageManager pm = context.getPackageManager();
		JSONArray array = new JSONArray();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		List<ResolveInfo> infos = pm.queryIntentActivities(intent,
				PackageManager.GET_INTENT_FILTERS);
		if (infos != null) {
			for (ResolveInfo info : infos) {
				String pkg = info.activityInfo.packageName;
				if (isSystem(pkg)) {
					JSONObject item = new JSONObject();
					try {
						item.put("icon", info.activityInfo.loadIcon(pm));// drawable
						item.put("name", info.activityInfo.loadLabel(pm)
								.toString());
						item.put("packageName", info.activityInfo.packageName);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					array.put(item);
				}
			}
		}
		return array;
	}

	private static boolean isSystem(String pkg) {
		if (pkg.contains("com.android.")) {
			return true;
		} else if (pkg.contains("com.google.")) {
			return true;
		} else {
			return false;
		}
	}

}
