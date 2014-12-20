package com.youth168.sdk.freeweather.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.youth168.sdk.freeweather.Utils;
import com.youth168.sdk.freeweather.openapi.URLBuilder;

public class OpenApiTest {
	public static final String APPID = "abcdefghijklmn";// 请先申请
	public static final String APPKEY = "xxxx_SmartWeatherAPI_xxxx";// 请先申请
	public static final String TYPE = "forecast_v"; // index_f(基础),index_v(常规),forecast_f(基础),forecast_v(常规)(3
													// 天常规预报(24 小时))
	public static final String CITYID = "101200105";

	public static void main(String[] args) {
		String url = URLBuilder.buildRequest(CITYID, TYPE,
				URLBuilder.getSystemDate(), APPID, APPKEY);
		System.out.println(url);
		String resp = Utils.getResponse(url);
		System.out.println("resp:" + resp);

		try {
			if (resp != null) {
				JSONObject json = JSON.parseObject(resp);
				System.out.println(json.getJSONObject("c"));// 城市信息
				JSONObject f = json.getJSONObject("f");
				JSONArray fs = f.getJSONArray("f1");// 三天天气
				System.out.println(fs);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			System.out.println("数据异常，请检查appid或appkey是否配置正常。");
		}
	}

}
