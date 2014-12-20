package com.youth168.sdk.freeweather.openapi;

import java.util.HashMap;

public class IndexQueryMap {
	private static HashMap<String, String> weatherMap = new HashMap<String, String>();
	private static HashMap<String, String> windLevelMap = new HashMap<String, String>();
	private static HashMap<String, String> windDirMap = new HashMap<String, String>();

	static {
		initWeatherMap();
		
		initWindLevelMap();
		
		initWindDirMap();
	}
	
	public static String getWeatherBrief(String weatherCode) {
		String ret = weatherMap.get(weatherCode);
		return (ret == null || ret.length() == 0) ? "无" : ret;
	}

	public static String getWindLevel(String levelCode) {
		String ret = windLevelMap.get(levelCode);
		return (ret == null || ret.length() == 0) ? "无" : ret;
	}

	public static String getWindDirection(String dirCode) {
		String ret = windDirMap.get(dirCode);
		return (ret == null || ret.length() == 0) ? "无" : ret;
	}

	private static void initWeatherMap() {
		weatherMap.put("00", "晴");
		weatherMap.put("01", "多云");
		weatherMap.put("02", "阴");
		weatherMap.put("03", "阵雨");
		weatherMap.put("04", "雷阵雨");
		weatherMap.put("05", "雷阵雨伴有冰雹");
		weatherMap.put("06", "雨夹雪");
		weatherMap.put("07", "小雨");
		weatherMap.put("08", "中雨");
		weatherMap.put("09", "大雨");
		weatherMap.put("10", "暴雨");
		weatherMap.put("11", "大暴雨");
		weatherMap.put("12", "特大暴雨");
		weatherMap.put("13", "阵雪");
		weatherMap.put("14", "小雪");
		weatherMap.put("15", "中雪");
		weatherMap.put("16", "大雪");
		weatherMap.put("17", "暴雪");
		weatherMap.put("18", "雾");
		weatherMap.put("19", "冻雨");
		weatherMap.put("20", "沙尘暴");
		weatherMap.put("21", "小到中雨");
		weatherMap.put("22", "中到大雨");
		weatherMap.put("23", "大到暴雨");
		weatherMap.put("24", "暴雨到大暴雨");
		weatherMap.put("25", "大暴雨到特大暴雨");
		weatherMap.put("26", "小到中雪");
		weatherMap.put("27", "中到大雪");
		weatherMap.put("28", "大到暴雪");
		weatherMap.put("29", "浮尘");
		weatherMap.put("30", "扬沙");
		weatherMap.put("31", "强沙尘暴");
		weatherMap.put("53", "霾");
		weatherMap.put("99", "无");
	}
	
	private static void initWindLevelMap() {
		windLevelMap.put("0", "微风");
		windLevelMap.put("1", "3-4级");
		windLevelMap.put("2", "4-5级");
		windLevelMap.put("3", "5-6级");
		windLevelMap.put("4", "6-7级");
		windLevelMap.put("5", "7-8级");
		windLevelMap.put("6", "8-9级");
		windLevelMap.put("7", "9-10级");
		windLevelMap.put("8", "10-11级");
		windLevelMap.put("9", "11-12级");
	}

	private static void initWindDirMap() {
		windDirMap.put("0", "无持续风向");
		windDirMap.put("1", "东北风");
		windDirMap.put("2", "东风");
		windDirMap.put("3", "东南风");
		windDirMap.put("4", "南风");
		windDirMap.put("5", "西南风");
		windDirMap.put("6", "西风");
		windDirMap.put("7", "西北风");
		windDirMap.put("8", "北风");
		windDirMap.put("9", "旋转风");		
	}

}
