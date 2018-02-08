package com.qa.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qa.http.HttpLog;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {
	private HashMap<String, String> jsonMap = new HashMap<String, String>();
	private ArrayList<String> appendKeys = new ArrayList<String>();
	private JSONObject obj = null;

	public boolean checkParam(String args) {
		JSONObject objResponse = this.getObj();
		boolean flag = true;
		ArrayList<String> params = new ArrayList<String>();
		String regex = ",";
		if (0 == args.split(regex).length) {
			params.add(args);
		} else {
			for (int index = 0; index < args.split(regex).length; index++) {
				params.add(args.split(regex)[index].trim());
			}
			for (String temp : params) {
				if (!objResponse.has(temp)) {
					flag = false;
				}
			}
			return flag;
		}
		return false;
	}

	public JSONObject getObj() {
		return this.obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public String getResult(String Key) {
		if(jsonMap.containsKey(Key)){
			HttpLog.info("json-node-value： "+Key+" : "+jsonMap.get(Key).toString());
			return this.jsonMap.get(Key).toString();
		}else {
			String m = jsonOnArrayFind(jsonMap, Key);
			if (m != null) {
				return m;
			} else {
				System.out.println("json-node not exist:" + Key);
				return null;
			}
		}
	}

	/**
	 * 由于数组索引值不固定,需要模糊验证(可以使用正则表达式)
	 * 如下:
	 * 数据
	 * .body[7].updatedAt:	2017-05-18T10:05:50+08:00
	 * 验证: .body[*].updatedAt ==	2017-05-18T10:05:50+08:00
	 * @param map
	 * @param mkey
	 * @return
	 */
	public static String jsonOnArrayFind(Map<String,String> map, String mkey){
		Pattern pattern = Pattern.compile(mkey);

		for(String stringKey :map.keySet()){
			Matcher matcher = pattern.matcher(stringKey);
			if (matcher.find()) {
				return map.get(stringKey);
			}
		}
		return null;
	}

	/**
	 * 使用*号表达式
	 * 	 * .body[7].updatedAt:	2017-05-18T10:05:50+08:00
	 * 验证: .body[*].updatedAt ==	2017-05-18T10:05:50+08:00
	 * @param map
	 * @param mkey
	 * @return
	 */
	public static String jsonOnArrayFindR(Map<String,String> map, String mkey){
		Pattern pattern = Pattern.compile(mkey);
		String[] mat=mkey.split("\\*");
		if(mat.length<2)
			return null;
		if(mat.length==2){
			for(String stringKey :map.keySet()){
				if(stringKey.startsWith(mat[0]) && stringKey.endsWith(mat[1]))
					return map.get(stringKey);
			}
		}
		if(mat.length>2){
			for(String stringKey :map.keySet()){
				boolean isAllContains=true;
				if(stringKey.startsWith(mat[0]) && stringKey.endsWith(mat[mat.length-1])){
					for(String m:mat){
						isAllContains&=stringKey.contains(m);
					}
				}else{
					isAllContains=false;
				}
				if(isAllContains)
					return map.get(stringKey);
			}
		}
		return null;
	}


	public void parseJson(String  string) {
		JSONObject jsonObject=new JSONObject(string);
		this.setObj(jsonObject);
		parseJson(jsonObject, "");
	}
	public void parseJson(JSONObject jsonObject) {
		this.setObj(jsonObject);
		parseJson(jsonObject, "");
		// getArrayJsonKey(appendKeys);
	}
	static String jsonSepChar=".";

	private void parseJson(JSONObject jsonObject, String appendKey) {
		if (null == jsonObject)
			return;
		Iterator<String> it = jsonObject.keys();
		while (it.hasNext()) {
			String key = it.next();
			JSONArray arrayTmp = jsonObject.optJSONArray(key);
			JSONObject objTmp = jsonObject.optJSONObject(key);
			String value = jsonObject.optString(key);

			if (null != arrayTmp) {
				appendKeys.add(jsonSepChar);
				appendKeys.add(key);
				parseJsonArray(arrayTmp);
 			} else if (null != objTmp) {
 				appendKeys.add(jsonSepChar);
				appendKeys.add(key);
				parseJson(objTmp, "");
			} else if (value != null && !value.contains("{")) {

				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.add(jsonSepChar);
					appendKeys.add(key);
					appendKey = getArrayJsonKey(appendKeys);
				} else {
					appendKey = key;
				}
				appendKey=subPointString(appendKey);
				//String value1 = new String (value.getBytes("ISO-8859-1"),"utf-8");
				if(System.getProperty("json-node-debug")!=null){
					HttpLog.info("json-node-debug: "+ appendKey+":\t"+value);
				}
				
				jsonMap.put(appendKey, value);
				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.remove(appendKeys.size() - 1);
					appendKeys.remove(appendKeys.size() - 1);
				}
			} else {
				System.out.println("arrayTmp value objTmp is all null");
			}

		}
		if ("" != getArrayJsonKey(appendKeys)) {
			appendKeys.remove(appendKeys.size() - 1);
			appendKeys.remove(appendKeys.size() - 1);
			// getArrayJsonKey(appendKeys);
		}
	}
	public  static String subPointString(String src){
		if(src!=null&& src.startsWith(".")){
			if(src.length()>1)
				return src.substring(1,src.length());
			else{
				return src;
			}
		}
		return src;
	}

	public static String jsonArraySep="[";
	public static String jsonArraySepend="]";
	private void parseJsonArray(JSONArray array) {
		String appendKey = "";
		int len = array.length();
		for (int i = 0; i < len; i++) {
			JSONArray arrayTmp = array.optJSONArray(i);
			JSONObject objTmp = array.optJSONObject(i);
			String value = array.optString(i);

			if (null != arrayTmp) {
				parseJsonArray(arrayTmp);
			} else if (null != objTmp) {
				appendKeys.add(jsonArraySep);
				appendKeys.add(i + jsonArraySepend);

				parseJson(objTmp, "");

			} else if (value != null && !value.contains("{")) {

				if ("" != getArrayJsonKey(appendKeys)) {
					appendKeys.add(jsonArraySep);
					appendKeys.add(i + jsonArraySepend);
					appendKey = getArrayJsonKey(appendKeys);
				}
//				System.out.println("appendKeys:"+ appendKey+"\n"+"value-----:"+value);
				jsonMap.put(appendKey, value);
				appendKeys.remove(appendKeys.size() - 1);
				appendKeys.remove(appendKeys.size() - 1);
			} else {
				System.out.println("arrayTmp value objTmp is all null");
			}

		}
		if ("" != getArrayJsonKey(appendKeys)) {
			appendKeys.remove(appendKeys.size() - 1);
			appendKeys.remove(appendKeys.size() - 1);
		}
	}

	public String getArrayJsonKey(ArrayList<String> appendKeys) {
		String arrayJsonKey = "";
		for (int i = 0; i < appendKeys.size(); i++) {
			// System.out.println(appendKeys.get(i));
			arrayJsonKey = arrayJsonKey + appendKeys.get(i);
		}
		// System.out.println("appendKeys--------:" + arrayJsonKey);
		return arrayJsonKey;
	}

}