package com.youth168.sdk.freeweather;

import com.alibaba.fastjson.JSONObject;

public class WeatherInfoLoader {

	public static final String SINA_CITY_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";

	public final static String CHINA_WEATHER_URL = "http://www.weather.com.cn/data/cityinfo/CITY_CODE.html";

	public static volatile WeatherInfoLoader INS = null;

	public static WeatherInfoLoader getInstance() {
		if (null == INS) {
			synchronized (WeatherInfoLoader.class) {
				if (null == INS) {
					INS = new WeatherInfoLoader();
				}
			}
		}
		return INS;
	}

	/**
	 * 取当前城市天气
	 * 
	 * @return
	 */
	public JSONObject getWeather() throws Exception {
		return getWeatherByIp(null);
	}

	/**
	 * 根据IP请求天气
	 * 
	 * @param ip
	 *            (ip，选填)
	 * @return
	 */
	public JSONObject getWeatherByIp(String ip) throws Exception {
		final String cityJson = Utils.getResponse(getCityUrl(ip));
		if (!Utils.isStringEmpty(cityJson)) {
			JSONObject jsonObject = Utils.stringToJSONObject(cityJson);
			if (jsonObject != null) {
				return getWeatherByCityId(Utils
						.getCityId(jsonObject.getString("city")));
			}
		}

		throw new Exception("请求当前位置信息失败");
	}

	/**
	 * 根据城市ID请求天气
	 * 
	 * @param cityId
	 *            (城市ID，必填)
	 * @return
	 */
	public JSONObject getWeatherByCityId(String cityId) throws Exception {
		if (Utils.isStringEmpty(cityId)) {
			throw new Exception("请求城市超出可查询范围");
		}
		final String response = Utils.getResponse(getWeatherUrl(cityId));
		if (Utils.isStringEmpty(response)) {
			throw new Exception("请求天气信息网络失败");
		}

		JSONObject result = Utils.stringToJSONObject(response);
		if (result != null) {
			result = result.getJSONObject("weatherinfo");
			if (result != null && !result.containsKey("weather")) {
				return result;
			}
		}

		throw new Exception("请求天气信息响应异常");
	}

	/**
	 * 取得获取当前所在城市ID的URL
	 * 
	 * @param ip
	 *            (ip，选填)
	 * @return
	 */
	public static String getCityUrl(String ip) {
		return !Utils.isStringEmpty(ip) ? (SINA_CITY_URL + "&ip=" + ip)
				: SINA_CITY_URL;
	}

	/**
	 * 取取指定城市ID的天气请求URL
	 * 
	 * @param cityId
	 *            (城市ID，必填)
	 * @return
	 */
	public static String getWeatherUrl(String cityId) {
		if (Utils.isStringEmpty(cityId)) {
			return null;
		}

		return CHINA_WEATHER_URL.replace("CITY_CODE", cityId);
	}

}
