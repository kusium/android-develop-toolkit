package com.youth168.sdk.freeweather;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * @author steven-pan
 * @version 1.0
 */
public final class Utils {
	
	private static final HashMap<String, HashMap<String, String>> all = new HashMap<String, HashMap<String, String>>();

	private static final String NULL = "null";

	public static JSONObject stringToJSONObject(String json) {
		if (isStringEmpty(json)) {
			return null;
		}

		try {
			return JSON.parseObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static boolean isStringEmpty(String str) {
		return (null == str) || str.length() == 0 || str.equals(NULL);
	}
	
	public static String getResponse(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(10000);// 连接超时 单位毫秒
			con.setReadTimeout(5000);// 读取超时 单位毫秒
			con.setRequestProperty("Proxy-Connection", "Keep-Alive");
			con.connect();

			InputStream is = con.getInputStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			byte[] data = outStream.toByteArray();// 网页的二进制数据
			outStream.close();
			is.close();
			con.disconnect();
			return new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	

	
	public static void main(String[] args) {
		System.out.println(getProvinceList());
		System.out.println(getCityList("台湾"));
		System.out.println(getCityId("恩施"));
		System.out.println(getCityName("101201001"));
	}
	
	static {
		init();
	}

	public static List<String> getProvinceList() {
		checkData();

		Iterator<String> iterator = all.keySet().iterator();
		List<String> list = new ArrayList<String>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}

		return list;
	}

	public static List<String> getCityList(String province) {
		checkData();

		HashMap<String, String> citys = all.get(province);
		if (citys == null || citys.isEmpty()) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		Iterator<String> iterator = citys.keySet().iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}

		return list;
	}

	public static String getCityId(String cityName) {
		checkData();

		List<String> provinceList = getProvinceList();
		if (provinceList == null || provinceList.size() == 0) {
			return null;
		}

		for (String province : provinceList) {
			HashMap<String, String> cityMap = all.get(province);
			if (cityMap == null || cityMap.isEmpty()) {
				continue;
			}
			String result = cityMap.get(cityName);
			if (result == null || result.length() == 0) {
				continue;
			}
			return result;
		}

		return null;
	}
	
	public static String getCityName(String cityId) {
		checkData();

		List<String> provinceList = getProvinceList();
		if (provinceList == null || provinceList.size() == 0) {
			return null;
		}

		for (String province : provinceList) {
			HashMap<String, String> cityMap = all.get(province);
			if (cityMap == null || cityMap.isEmpty()) {
				continue;
			}
			
			Iterator<String> iterator = cityMap.keySet().iterator();
			while(iterator.hasNext()) {
				String cityName = iterator.next();
				if(	cityMap.get(cityName).equals(cityId) ) {
					return cityName;
				} else {
					continue;
				}
			}
		}

		return null;
	}
	

	private static void checkData() {
		if (all == null && all.isEmpty()) {
			init();
		}
	}

	public static void init() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("上海", "101020100");
		map.put("闵行", "101020200");
		map.put("宝山", "101020300");
		map.put("嘉定", "101020500");
		map.put("南汇", "101020600");
		map.put("金山", "101020700");
		map.put("青浦", "101020800");
		map.put("松江", "101020900");
		map.put("奉贤", "101021000");
		map.put("崇明", "101021100");
		map.put("徐家汇", "101021200");
		map.put("浦东", "101021300");
		all.put("上海", map);
		map = new HashMap<String, String>();
		map.put("昆明", "101290101");
		map.put("大理", "101290201");
		map.put("红河", "101290301");
		map.put("曲靖", "101290401");
		map.put("保山", "101290501");
		map.put("文山", "101290601");
		map.put("玉溪", "101290701");
		map.put("楚雄", "101290801");
		map.put("普洱", "101290901");
		map.put("昭通", "101291001");
		map.put("临沧", "101291101");
		map.put("怒江", "101291201");
		map.put("迪庆", "101291301");
		map.put("丽江", "101291401");
		map.put("德宏", "101291501");
		map.put("西双版纳", "101291601");
		all.put("云南", map);
		map = new HashMap<String, String>();
		map.put("呼和浩特", "101080101");
		map.put("包头", "101080201");
		map.put("乌海", "101080301");
		map.put("乌兰察布", "101080401");
		map.put("通辽", "101080501");
		map.put("赤峰", "101080601");
		map.put("鄂尔多斯", "101080701");
		map.put("巴彦淖尔", "101080801");
		map.put("锡林郭勒", "101080901");
		map.put("呼伦贝尔", "101081001");
		map.put("兴安盟", "101081101");
		map.put("阿拉善盟", "101081201");
		all.put("内蒙古", map);
		map = new HashMap<String, String>();
		map.put("北京", "101010100");
		map.put("海淀", "101010200");
		map.put("朝阳", "101010300");
		map.put("顺义", "101010400");
		map.put("怀柔", "101010500");
		map.put("通州", "101010600");
		map.put("昌平", "101010700");
		map.put("延庆", "101010800");
		map.put("丰台", "101010900");
		map.put("石景山", "101011000");
		map.put("大兴", "101011100");
		map.put("房山", "101011200");
		map.put("密云", "101011300");
		map.put("门头沟", "101011400");
		map.put("平谷", "101011500");
		all.put("北京", map);
		map = new HashMap<String, String>();
		map.put("台北", "101340101");
		map.put("高雄", "101340201");
		map.put("台中", "101340401");
		all.put("台湾", map);
		map = new HashMap<String, String>();
		map.put("长春", "101060101");
		map.put("吉林", "101060201");
		map.put("延边", "101060301");
		map.put("四平", "101060401");
		map.put("通化", "101060501");
		map.put("白城", "101060601");
		map.put("辽源", "101060701");
		map.put("松原", "101060801");
		map.put("白山", "101060901");
		all.put("吉林", map);
		map = new HashMap<String, String>();
		map.put("成都", "101270101");
		map.put("攀枝花", "101270201");
		map.put("自贡", "101270301");
		map.put("绵阳", "101270401");
		map.put("南充", "101270501");
		map.put("达州", "101270601");
		map.put("遂宁", "101270701");
		map.put("广安", "101270801");
		map.put("巴中", "101270901");
		map.put("泸州", "101271001");
		map.put("宜宾", "101271101");
		map.put("内江", "101271201");
		map.put("资阳", "101271301");
		map.put("乐山", "101271401");
		map.put("眉山", "101271501");
		map.put("凉山", "101271601");
		map.put("雅安", "101271701");
		map.put("甘孜", "101271801");
		map.put("阿坝", "101271901");
		map.put("德阳", "101272001");
		map.put("广元", "101272101");
		all.put("四川", map);
		map = new HashMap<String, String>();
		map.put("天津", "101030100");
		map.put("武清", "101030200");
		map.put("宝坻", "101030300");
		map.put("东丽", "101030400");
		map.put("西青", "101030500");
		map.put("北辰", "101030600");
		map.put("宁河", "101030700");
		map.put("汉沽", "101030800");
		map.put("静海", "101030900");
		map.put("津南", "101031000");
		map.put("塘沽", "101031100");
		map.put("大港", "101031200");
		map.put("蓟县", "101031400");
		all.put("天津", map);
		map = new HashMap<String, String>();
		map.put("银川", "101170101");
		map.put("石嘴山", "101170201");
		map.put("吴忠", "101170301");
		map.put("固原", "101170401");
		map.put("中卫", "101170501");
		all.put("宁夏", map);
		map = new HashMap<String, String>();
		map.put("合肥", "101220101");
		map.put("蚌埠", "101220201");
		map.put("芜湖", "101220301");
		map.put("淮南", "101220401");
		map.put("马鞍山", "101220501");
		map.put("安庆", "101220601");
		map.put("宿州", "101220701");
		map.put("阜阳", "101220801");
		map.put("亳州", "101220901");
		map.put("黄山", "101221001");
		map.put("滁州", "101221101");
		map.put("淮北", "101221201");
		map.put("铜陵", "101221301");
		map.put("宣城", "101221401");
		map.put("六安", "101221501");
		map.put("巢湖", "101221601");
		map.put("池州", "101221701");
		all.put("安徽", map);
		map = new HashMap<String, String>();
		map.put("济南", "101120101");
		map.put("青岛", "101120201");
		map.put("淄博", "101120301");
		map.put("德州", "101120401");
		map.put("烟台", "101120501");
		map.put("潍坊", "101120601");
		map.put("济宁", "101120701");
		map.put("泰安", "101120801");
		map.put("临沂", "101120901");
		map.put("菏泽", "101121001");
		map.put("滨州", "101121101");
		map.put("东营", "101121201");
		map.put("威海", "101121301");
		map.put("枣庄", "101121401");
		map.put("日照", "101121501");
		map.put("莱芜", "101121601");
		map.put("聊城", "101121701");
		all.put("山东", map);
		map = new HashMap<String, String>();
		map.put("太原", "101100101");
		map.put("大同", "101100201");
		map.put("阳泉", "101100301");
		map.put("晋中", "101100401");
		map.put("长治", "101100501");
		map.put("晋城", "101100601");
		map.put("临汾", "101100701");
		map.put("运城", "101100801");
		map.put("朔州", "101100901");
		map.put("忻州", "101101001");
		map.put("吕梁", "101101100");
		all.put("山西", map);
		map = new HashMap<String, String>();
		map.put("广州", "101280101");
		map.put("韶关", "101280201");
		map.put("惠州", "101280301");
		map.put("梅州", "101280401");
		map.put("汕头", "101280501");
		map.put("深圳", "101280601");
		map.put("珠海", "101280701");
		map.put("佛山", "101280800");
		map.put("肇庆", "101280901");
		map.put("湛江", "101281001");
		map.put("江门", "101281101");
		map.put("河源", "101281201");
		map.put("清远", "101281301");
		map.put("云浮", "101281401");
		map.put("潮州", "101281501");
		map.put("东莞", "101281601");
		map.put("中山", "101281701");
		map.put("阳江", "101281801");
		map.put("揭阳", "101281901");
		map.put("茂名", "101282001");
		map.put("汕尾", "101282101");
		all.put("广东", map);
		map = new HashMap<String, String>();
		map.put("南宁", "101300101");
		map.put("崇左", "101300201");
		map.put("柳州", "101300301");
		map.put("来宾", "101300401");
		map.put("桂林", "101300501");
		map.put("梧州", "101300601");
		map.put("贺州", "101300701");
		map.put("贵港", "101300801");
		map.put("玉林", "101300901");
		map.put("百色", "101301001");
		map.put("钦州", "101301101");
		map.put("河池", "101301201");
		map.put("北海", "101301301");
		map.put("防城港", "101301401");
		all.put("广西", map);
		map = new HashMap<String, String>();
		map.put("乌鲁木齐", "101130101");
		map.put("克拉玛依", "101130201");
		map.put("石河子", "101130301");
		map.put("昌吉", "101130401");
		map.put("吐鲁番", "101130501");
		map.put("巴州", "101130601");
		map.put("阿拉尔", "101130701");
		map.put("阿克苏", "101130801");
		map.put("喀什", "101130901");
		map.put("伊犁", "101131001");
		map.put("塔城", "101131101");
		map.put("哈密", "101131201");
		map.put("和田", "101131301");
		map.put("阿勒泰", "101131401");
		map.put("克州", "101131501");
		map.put("博州", "101131601");
		all.put("新疆", map);
		map = new HashMap<String, String>();
		map.put("南京", "101190101");
		map.put("无锡", "101190201");
		map.put("镇江", "101190301");
		map.put("苏州", "101190401");
		map.put("南通", "101190501");
		map.put("扬州", "101190601");
		map.put("盐城", "101190701");
		map.put("徐州", "101190801");
		map.put("淮安", "101190901");
		map.put("连云港", "101191001");
		map.put("常州", "101191101");
		map.put("泰州", "101191201");
		map.put("宿迁", "101191301");
		all.put("江苏", map);
		map = new HashMap<String, String>();
		map.put("南昌", "101240101");
		map.put("九江", "101240201");
		map.put("上饶", "101240301");
		map.put("抚州", "101240401");
		map.put("宜春", "101240501");
		map.put("吉安", "101240601");
		map.put("赣州", "101240701");
		map.put("景德镇", "101240801");
		map.put("萍乡", "101240901");
		map.put("新余", "101241001");
		map.put("鹰潭", "101241101");
		all.put("江西", map);
		map = new HashMap<String, String>();
		map.put("石家庄", "101090101");
		map.put("保定", "101090201");
		map.put("张家口", "101090301");
		map.put("承德", "101090402");
		map.put("唐山", "101090501");
		map.put("廊坊", "101090601");
		map.put("沧州", "101090701");
		map.put("衡水", "101090801");
		map.put("邢台", "101090901");
		map.put("邯郸", "101091001");
		map.put("秦皇岛", "101091101");
		all.put("河北", map);
		map = new HashMap<String, String>();
		map.put("郑州", "101180101");
		map.put("安阳", "101180201");
		map.put("新乡", "101180301");
		map.put("许昌", "101180401");
		map.put("平顶山", "101180501");
		map.put("信阳", "101180601");
		map.put("南阳", "101180701");
		map.put("开封", "101180801");
		map.put("洛阳", "101180901");
		map.put("商丘", "101181001");
		map.put("焦作", "101181101");
		map.put("鹤壁", "101181201");
		map.put("濮阳", "101181301");
		map.put("周口", "101181401");
		map.put("漯河", "101181501");
		map.put("驻马店", "101181601");
		map.put("三门峡", "101181701");
		map.put("济源", "101181801");
		all.put("河南", map);
		map = new HashMap<String, String>();
		map.put("杭州", "101210101");
		map.put("湖州", "101210201");
		map.put("嘉兴", "101210301");
		map.put("宁波", "101210401");
		map.put("绍兴", "101210501");
		map.put("台州", "101210601");
		map.put("温州", "101210701");
		map.put("丽水", "101210801");
		map.put("金华", "101210901");
		map.put("衢州", "101211001");
		map.put("舟山", "101211101");
		all.put("浙江", map);
		map = new HashMap<String, String>();
		map.put("海南", "101150401");
		all.put("海南", map);
		map = new HashMap<String, String>();
		map.put("武汉", "101200101");
		map.put("襄阳", "101200201");
		map.put("鄂州", "101200301");
		map.put("孝感", "101200401");
		map.put("黄冈", "101200501");
		map.put("黄石", "101200601");
		map.put("咸宁", "101200701");
		map.put("荆州", "101200801");
		map.put("宜昌", "101200901");
		map.put("恩施", "101201001");
		map.put("十堰", "101201101");
		map.put("神农架", "101201201");
		map.put("随州", "101201301");
		map.put("荆门", "101201401");
		map.put("天门", "101201501");
		map.put("仙桃", "101201601");
		map.put("潜江", "101201701");
		all.put("湖北", map);
		map = new HashMap<String, String>();
		map.put("长沙", "101250101");
		map.put("湘潭", "101250201");
		map.put("株洲", "101250301");
		map.put("衡阳", "101250401");
		map.put("郴州", "101250501");
		map.put("常德", "101250601");
		map.put("益阳", "101250700");
		map.put("娄底", "101250801");
		map.put("邵阳", "101250901");
		map.put("岳阳", "101251001");
		map.put("张家界", "101251101");
		map.put("怀化", "101251201");
		map.put("永州", "101251401");
		map.put("湘西", "101251501");
		all.put("湖南", map);
		map = new HashMap<String, String>();
		map.put("澳门", "101330101");
		all.put("澳门", map);
		map = new HashMap<String, String>();
		map.put("兰州", "101160101");
		map.put("定西", "101160201");
		map.put("平凉", "101160301");
		map.put("庆阳", "101160401");
		map.put("武威", "101160501");
		map.put("金昌", "101160601");
		map.put("张掖", "101160701");
		map.put("酒泉", "101160801");
		map.put("天水", "101160901");
		map.put("陇南", "101161001");
		map.put("临夏", "101161101");
		map.put("甘南", "101161201");
		map.put("白银", "101161301");
		map.put("嘉峪关", "101161401");
		all.put("甘肃", map);
		map = new HashMap<String, String>();
		map.put("福州", "101230101");
		map.put("厦门", "101230201");
		map.put("宁德", "101230301");
		map.put("莆田", "101230401");
		map.put("泉州", "101230501");
		map.put("漳州", "101230601");
		map.put("龙岩", "101230701");
		map.put("三明", "101230801");
		map.put("南平", "101230901");
		all.put("福建", map);
		map = new HashMap<String, String>();
		map.put("拉萨", "101140101");
		map.put("日喀则", "101140201");
		map.put("山南", "101140301");
		map.put("林芝", "101140401");
		map.put("昌都", "101140501");
		map.put("那曲", "101140601");
		map.put("阿里", "101140701");
		all.put("西藏", map);
		map = new HashMap<String, String>();
		map.put("贵阳", "101260101");
		map.put("遵义", "101260201");
		map.put("安顺", "101260301");
		map.put("黔南", "101260401");
		map.put("黔东南", "101260501");
		map.put("铜仁", "101260601");
		map.put("毕节", "101260701");
		map.put("六盘水", "101260801");
		map.put("黔西南", "101260901");
		all.put("贵州", map);
		map = new HashMap<String, String>();
		map.put("沈阳", "101070101");
		map.put("大连", "101070201");
		map.put("鞍山", "101070301");
		map.put("抚顺", "101070401");
		map.put("本溪", "101070501");
		map.put("丹东", "101070601");
		map.put("锦州", "101070701");
		map.put("营口", "101070801");
		map.put("阜新", "101070901");
		map.put("辽阳", "101071001");
		map.put("铁岭", "101071101");
		map.put("朝阳", "101010300");
		map.put("盘锦", "101071301");
		map.put("葫芦岛", "101071401");
		all.put("辽宁", map);
		map = new HashMap<String, String>();
		map.put("重庆", "101040100");
		map.put("永川", "101040200");
		map.put("合川", "101040300");
		map.put("南川", "101040400");
		map.put("江津", "101040500");
		map.put("万盛", "101040600");
		map.put("渝北", "101040700");
		map.put("北碚", "101040800");
		map.put("巴南", "101040900");
		map.put("长寿", "101041000");
		map.put("黔江", "101041100");
		map.put("万州", "101041300");
		map.put("涪陵", "101041400");
		map.put("开县", "101041500");
		map.put("城口", "101041600");
		map.put("云阳", "101041700");
		map.put("巫溪", "101041800");
		map.put("奉节", "101041900");
		map.put("巫山", "101042000");
		map.put("潼南", "101042100");
		map.put("垫江", "101042200");
		map.put("梁平", "101042300");
		map.put("忠县", "101042400");
		map.put("石柱", "101042500");
		map.put("大足", "101042600");
		map.put("荣昌", "101042700");
		map.put("铜梁", "101042800");
		map.put("璧山", "101042900");
		map.put("丰都", "101043000");
		map.put("武隆", "101043100");
		map.put("彭水", "101043200");
		map.put("綦江", "101043300");
		map.put("酉阳", "101043400");
		map.put("秀山", "101043600");
		all.put("重庆", map);
		map = new HashMap<String, String>();
		map.put("西安", "101110101");
		map.put("咸阳", "101110200");
		map.put("延安", "101110300");
		map.put("榆林", "101110401");
		map.put("渭南", "101110501");
		map.put("商洛", "101110601");
		map.put("安康", "101110701");
		map.put("汉中", "101110801");
		map.put("宝鸡", "101110901");
		map.put("铜川", "101111001");
		map.put("杨凌", "101111101");
		all.put("陕西", map);
		map = new HashMap<String, String>();
		map.put("西宁", "101150101");
		map.put("海东", "101150201");
		map.put("黄南", "101150301");
		map.put("海南", "101150401");
		map.put("果洛", "101150501");
		map.put("玉树", "101150601");
		map.put("海西", "101150701");
		map.put("海北", "101150801");
		map.put("格尔木", "101150901");
		all.put("青海", map);
		map = new HashMap<String, String>();
		map.put("香港", "101320101");
		all.put("香港", map);
		map = new HashMap<String, String>();
		map.put("哈尔滨", "101050101");
		map.put("齐齐哈尔", "101050201");
		map.put("牡丹江", "101050301");
		map.put("佳木斯", "101050401");
		map.put("绥化", "101050501");
		map.put("黑河", "101050601");
		map.put("大兴安岭", "101050701");
		map.put("伊春", "101050801");
		map.put("大庆", "101050901");
		map.put("七台河", "101051002");
		map.put("鸡西", "101051101");
		map.put("鹤岗", "101051201");
		map.put("双鸭山", "101051301");
		all.put("黑龙江", map);
	}



}
