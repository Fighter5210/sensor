package com.dy.sensor.foundation.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/**
	 * ��һ��JSON �����ַ��ʽ�еõ�һ��java����
	 * 
	 * @param jsonString
	 * @param pojoCalss
	 * @return java����
	 */
	public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
		Object pojo;
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		pojo = JSONObject.toBean(jsonObject, pojoCalss);
		return pojo;
	}
	
	/**
	 * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Map getMap4Json(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Iterator keyIter = jsonObject.keys();
		String key;
		Object value;
		Map valueMap = new HashMap();

		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}

		return valueMap;
	}

	/**
	 * ��json�����еõ���Ӧjava����
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray4Json(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();

	}

	/**
	 * ��json���󼯺ϱ��ʽ�еõ�һ��java�����б�
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static List getList4Json(String jsonString, Class pojoClass) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);

		JSONObject jsonObject;
		Object pojoValue;

		List list = new ArrayList();
		for (int i = 0; i < jsonArray.size(); i++) {

			jsonObject = jsonArray.getJSONObject(i);
			pojoValue = JSONObject.toBean(jsonObject, pojoClass);
			list.add(pojoValue);

		}
		return list;
	}

	/**
	 * ��json�����н����java�ַ�����
	 * 
	 * @param jsonString
	 * @return
	 */
	public static String[] getStringArray4Json(String jsonString) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		String[] stringArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringArray[i] = jsonArray.getString(i);
		}
		return stringArray;
	}

	/**
	 * ��json�����н����javaLong�Ͷ�������
	 * 
	 * @param jsonString
	 * @return
	 */
	
	/*
	 * public static Long[] getLongArray4Json(String jsonString) {
	 * 
	 * JSONArray jsonArray = JSONArray.fromObject(jsonString); Long[] longArray
	 * = new Long[jsonArray.size()]; for (int i = 0; i < jsonArray.size(); i++)
	 * { longArray[i] = jsonArray.getLong(i);
	 * 
	 * } return longArray; }
	 */

	/**
	 * ��json�����н����java Integer�Ͷ�������
	 * 
	 * @param jsonString
	 * @return
	 */
	/*
	 * public static Integer[] getIntegerArray4Json(String jsonString) {
	 * 
	 * JSONArray jsonArray = JSONArray.fromObject(jsonString); Integer[]
	 * integerArray = new Integer[jsonArray.size()]; for (int i = 0; i <
	 * jsonArray.size(); i++) { integerArray[i] = jsonArray.getInt(i);
	 * 
	 * } return integerArray; }
	 */

	/**
	 * ��json�����н����java Date �Ͷ������飬ʹ�ñ��������뱣֤
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Date[] getDateArray4Json(String jsonString, String DataFormat) {

		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		Date[] dateArray = new Date[jsonArray.size()];
		// String dateString = null;
		Date date = null;

		for (int i = 0; i < jsonArray.size(); i++) {
			// dateString = jsonArray.getString(i);
			// date = DateUtil.stringToDate(dateString, DataFormat);//��ʵ����
			date = null;
			dateArray[i] = date;

		}
		return dateArray;
	}

	/**
	 * ��json�����н����java Integer�Ͷ�������
	 * 
	 * @param jsonString
	 * @return
	 */
	/*
	 * public static Double[] getDoubleArray4Json(String jsonString) {
	 * 
	 * JSONArray jsonArray = JSONArray.fromObject(jsonString); Double[]
	 * doubleArray = new Double[jsonArray.size()]; for (int i = 0; i <
	 * jsonArray.size(); i++) { doubleArray[i] = jsonArray.getDouble(i);
	 * 
	 * } return doubleArray; }
	 */
	
	/**
	 * ��java����ת����json�ַ�
	 * 
	 * @param javaObj
	 * @return
	 */
	public static String getJsonString4JavaPOJO(Object javaObj) {
		JSONObject json;
		json = JSONObject.fromObject(javaObj);
		return json.toString();
	}
	

}
