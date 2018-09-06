package com.gdts.selecting.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gdts.selecting.entity.LoginInfoUser;


public class LoginUserService<T> {
	
	@SuppressWarnings("unused")
	private static final String DEFAULT_NAME_KEY = "name";
	@SuppressWarnings("unused")
	private static final String DEFAULT_TYPE_KEY = "type";
	@SuppressWarnings("unused")
	private static final String DEFAULT_TYPE_VALUE = "bar";
	@SuppressWarnings("unused")
	private static final String DEFAULT_STACK_KEY = "总量";
	private static final String DEFAULT_LABEL_KEY = "normal";
	@SuppressWarnings("unused")
	private static final String DEFAULT_DATA_KEY = "data";
	private Map<String, List<T>> map;
	private Map<String, Map<String, Object>> label;
	private Map<String, Object> normal;
	
	public LoginUserService(){
		super();
		init();
	}
	
	public void init(){
		map = new HashMap<String, List<T>>();
		label = new HashMap<String, Map<String, Object>>();
		normal = new HashMap<String, Object>();
	}
	
	public void setDefaultLabel(){
		this.normal.put("show",true);
		this.normal.put("position", "insideRight");
		this.label.put(DEFAULT_LABEL_KEY, this.normal);
	}
	
	public Map<String, Map<String, Object>> getLabel(){
		return this.label;
	}
	
	public void setMap(String key,List<T> value){
		this.map.put(key, value);
	}

	@SuppressWarnings("unchecked")
	public Integer[] getSizeByWeekDay(String key){
		List<LoginInfoUser> list = (List<LoginInfoUser>) this.map.get(key);
		Integer[] arr = new Integer[7];
		arr[0] = 0;
		arr[1] = 0;
		arr[2] = 0;
		arr[3] = 0;
		arr[4] = 0;
		arr[5] = 0;
		arr[6] = 0;
		for(int i=0; i<list.size(); i++){
			int temp = 0;
			switch (list.get(i).getWeekDay()) {
				case "星期一":
					temp = 1 + arr[0];
					arr[0] = temp;
					break;
				case "星期二":	
					temp = 1 + arr[1];
					arr[1] = temp;
					break;
				case "星期三":
					temp = 1 + arr[2];
					arr[2] = temp;
					break;
				case "星期四":
					temp = 1 + arr[3];
					arr[3] = temp;
					break;
				case "星期五":
					temp = 1 + arr[4];
					arr[4] = temp;
					break;
				case "星期六":
					temp = 1 + arr[5];
					arr[5] = temp;
					break;
				case "星期日":
					temp = 1 + arr[6];
					arr[6] = temp;
					break;
			}
		}
		return arr;
	}
}
