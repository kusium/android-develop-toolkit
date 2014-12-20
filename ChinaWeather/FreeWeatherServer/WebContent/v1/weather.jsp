<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.youth168.sdk.freeweather.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setHeader("Content-type", "text/html;charset=UTF-8");
	response.setCharacterEncoding("UTF-8");
%>

<%
	try {
		String cityId = request.getParameter("cityid");
		if (cityId == null || cityId.length() == 0
				|| cityId.equals("null")) {
			String ip = request.getParameter("ip");
			String cityUrl = null;
			if (ip == null || ip.length() == 0 || ip.equals("null")) {
				cityUrl = WeatherInfoLoader.getCityUrl(null);
			} else {
				cityUrl = WeatherInfoLoader.getCityUrl(ip);
			}
			JSONObject cityJson = Utils.stringToJSONObject(Utils
					.getResponse(cityUrl));
			if (cityJson != null) {
				cityId = Utils.getCityId(cityJson.optString("city"));
			}
		}
		if (cityId != null) {
			String weatherUrl = WeatherInfoLoader.getWeatherUrl(cityId);
			String ret = Utils
					.getResponse(weatherUrl);

			JSONObject weatherJson = Utils.stringToJSONObject(Utils
					.getResponse(weatherUrl));
			if (weatherJson != null) {
				weatherJson = weatherJson.optJSONObject("weatherinfo");
				if (weatherJson != null) {
					out.print(weatherJson.toString());
				}
				return;
			}
		}
		out.print("{}");
	} catch (Exception e) {
		out.print("{}");
	}
%>

