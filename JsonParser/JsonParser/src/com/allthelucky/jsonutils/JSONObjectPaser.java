package com.allthelucky.jsonutils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @description An auto JSONObject parser, transform JSONObject to java Bean.
 * @author steven-pan
 * @date 2014-07-20
 */
public class JSONObjectPaser {

	public JSONObjectPaser() {

	}

	/**
	 * parse Array
	 * 
	 * @param array
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public List<Object> parse(JSONArray array, Class<?> cls) throws Exception {
		if (array == null || array.length() == 0) {
			return null;
		}
		int size = array.length();
		List<Object> list = new ArrayList<Object>();
		if (size > 0) {
			for (int index = 0; index < size; index++) {
				Object object = parseItem(array.optJSONObject(index), cls);
				list.add(object);
			}
		}
		return list;
	}

	/**
	 * parse Item
	 * 
	 * @param jobject
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	private Object parseItem(JSONObject jobject, Class<?> cls) throws Exception {
		final Object object = cls.newInstance();
		final Field[] fields = cls.getDeclaredFields();

		if (fields != null && fields.length > 0) {
			for (Field field : fields) {
				final String fieldName = field.getName();
				if ("serialVersionUID".equals(fieldName)) {
					continue;
				}
				final Class<?> filedClz = field.getType();
				final String fieldClzName = filedClz.getName();

				if (fieldClzName.equals("java.lang.String")) {
					try {
						field.set(object, jobject.opt(fieldName));
					} catch (Exception e) {
						field.set(object,null);
					}
				} else if (fieldClzName.equals("java.lang.Integer")) {
					try {
						field.set(object, jobject.opt(fieldName));
					} catch (Exception e) {
						field.set(object,0);
					}
				} else if (fieldClzName.equals("java.util.List")) {
					ParameterizedType pt = (ParameterizedType) field.getGenericType();
					Class<?> fieldListClz = (Class<?>) pt.getActualTypeArguments()[0];
					try {
						field.set(object, parse(jobject.optJSONArray(fieldName), fieldListClz));
					} catch (Exception e) {
						field.set(object,null);
					}
				} else {// other object type
					try {
						field.set(object, parseItem(jobject.optJSONObject(fieldName), filedClz));
					} catch (Exception e) {
						field.set(object,null);
					}
				}
			}
		}
		return object;
	}
}
