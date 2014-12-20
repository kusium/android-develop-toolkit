package com.youth168.service.freeweather;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.youth168.sdk.freeweather.Utils;
import com.youth168.sdk.freeweather.WeatherInfoLoader;

/**
 * Servlet implementation class FreeWeatherServlet
 */
@WebServlet("/FreeWeatherServlet")
public class FreeWeatherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FreeWeatherServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(200);
		
		try {
			String cityId = request.getParameter("cityid");
			if (cityId == null || cityId.length() == 0 || cityId.equals("null")) {
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
				JSONObject weatherJson = Utils.stringToJSONObject(Utils
						.getResponse(weatherUrl));
				if (weatherJson != null) {
					weatherJson = weatherJson.optJSONObject("weatherinfo");
					if (weatherJson != null) {
						response.getWriter().write(weatherJson.toString());
					}
					return;
				}
			}
			response.getWriter().write("{}");
		} catch (Exception e) {
			response.getWriter().write("{}");
		}
	}

}
