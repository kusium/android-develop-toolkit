package com.youth168.sdk.freeweather.test;

import com.youth168.sdk.freeweather.Utils;
import com.youth168.sdk.freeweather.WeatherInfoLoader;

public class Test {

	/**
	 * 测试程序
	 */
	public static void main(String[] args) {
		printLine();
		testA();
		printLine();
		testB();
	}

	private static void testA() {
		WeatherInfoLoader mWeatherInfoLoader = WeatherInfoLoader.getInstance();
		try {// 自动定位当前城市，并获取对应城市天气
			System.out.println(mWeatherInfoLoader.getWeather());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {// 根据输入IP获取对应城市天气
			System.out.println(mWeatherInfoLoader
					.getWeatherByIp("59.173.236.130"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {// 根据输入城市ID获取对应城市天气
			System.out.println(mWeatherInfoLoader
					.getWeatherByCityId("101200101"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testB() {
		// 获取城市定位请求URL
		String cityUrl = WeatherInfoLoader.getCityUrl(null);
		System.out.println("cityUrl:" + cityUrl);
		System.out.println("cityInfo:" + Utils.getResponse(cityUrl));

		// 根据IP获取城市定位请求URL
		cityUrl = WeatherInfoLoader.getCityUrl("59.173.236.130");
		System.out.println("cityUrl:" + cityUrl);
		System.out.println("cityInfo:" + Utils.getResponse(cityUrl));

		// 根据城市ID获取天气请求URL
		String weatherUrl = WeatherInfoLoader.getWeatherUrl("101200101");
		System.out.println("weatherUrl:" + weatherUrl);
		System.out.println("weatherInfo:" + Utils.getResponse(weatherUrl));
	}

	private static void printLine() {
		System.out.println("=====================");
	}

}
