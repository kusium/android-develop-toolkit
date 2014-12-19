package com.allthelucky.examples.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Network ConnectivityUtils
 * 
 * @author panxw
 * 
 * @refer http://stackoverflow.com/users/220710/emil
 */
public final class ConnectivityUtils {

	/**
	 * Get the network info
	 * 
	 * @param context
	 * @return
	 */
	public static NetworkInfo getNetworkInfo(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}

	/**
	 * Check if there is any connectivity
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		NetworkInfo info = ConnectivityUtils.getNetworkInfo(context);
		return (info != null && info.isConnected());
	}

	/**
	 * get network type
	 * 
	 * @param context
	 * @return network type{ConnectivityManager.TYPE_WIFI,
	 *         ConnectivityManager.TYPE_MOBILE,
	 *         ConnectivityManager.TYPE_ETHERNET, -1(not connected) }
	 */
	public static int getConnectedType(Context context) {
		NetworkInfo info = ConnectivityUtils.getNetworkInfo(context);

		if ((info != null && info.isConnected())) {
			return info.getType();
		} else {
			return -1;
		}
	}

	/**
	 * get network type name
	 * 
	 * @param type
	 * @return
	 */
	public static String getTypeString(int type) {
		if (ConnectivityManager.TYPE_WIFI == type) {
			return "WIFI";
		} else if (ConnectivityManager.TYPE_MOBILE == type) {
			return "MOBILE";
		} else if (ConnectivityManager.TYPE_ETHERNET == type) {
			return "ETHERNET";
		} else {
			return "unknow";
		}
	}

	/**
	 * Check if there is fast connectivity
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnectedFast(Context context) {
		NetworkInfo info = ConnectivityUtils.getNetworkInfo(context);
		return (info != null && info.isConnected() && ConnectivityUtils.isConnectionFast(info.getType(),
				info.getSubtype()));
	}

	/**
	 * Check if the connection is fast
	 * 
	 * @param type
	 * @param subType
	 * @return
	 */
	public static boolean isConnectionFast(int type, int subType) {
		System.out.println("type=" + type + ",subType=" + subType);

		if (type == ConnectivityManager.TYPE_WIFI) {
			return true;
		} else if (type == ConnectivityManager.TYPE_MOBILE) {
			switch (subType) {
			case TelephonyManager.NETWORK_TYPE_1xRTT:
				System.out.println("speed: 50-100 kbps");
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_CDMA:
				System.out.println("speed: 14-64 kbps");
				return false; // ~ 14-64 kbps
			case TelephonyManager.NETWORK_TYPE_EDGE:
				System.out.println("speed: 50-100 kbps");
				return false; // ~ 50-100 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
				System.out.println("speed: 400-1000 kbps");
				return true; // ~ 400-1000 kbps
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
				System.out.println("speed: 600-1400 kbps");
				return true; // ~ 600-1400 kbps
			case TelephonyManager.NETWORK_TYPE_GPRS:
				System.out.println("speed: 100 kbps");
				return false; // ~ 100 kbps
			case TelephonyManager.NETWORK_TYPE_HSDPA:
				System.out.println("speed: 2-14 Mbps");
				return true; // ~ 2-14 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPA:
				System.out.println("speed: 700-1700 kbps");
				return true; // ~ 700-1700 kbps
			case TelephonyManager.NETWORK_TYPE_HSUPA:
				System.out.println("speed: 1-23 Mbps");
				return true; // ~ 1-23 Mbps
			case TelephonyManager.NETWORK_TYPE_UMTS:
				System.out.println("speed: 400-7000 kbps");
				return true; // ~ 400-7000 kbps
				/*
				 * Above API level 7, make sure to set android:targetSdkVersion
				 * to appropriate level to use these
				 */
			case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
				System.out.println("speed: 1-2 Mbps");
				return true; // ~ 1-2 Mbps
			case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
				System.out.println("speed: 5 Mbps");
				return true; // ~ 5 Mbps
			case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
				System.out.println("speed: 10-20 Mbps");
				return true; // ~ 10-20 Mbps
			case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
				System.out.println("speed: 25 kbps ");
				return false; // ~25 kbps
			case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
				System.out.println("speed: 10+ Mbps");
				return true; // ~ 10+ Mbps
				// Unknown
			case TelephonyManager.NETWORK_TYPE_UNKNOWN:
			default:
				return false;
			}
		} else {
			return false;
		}
	}

}
