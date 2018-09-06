package com.gdts.selecting.util;

import java.util.Arrays;
import java.util.Map;

public class Series {
	
	private String name;
	private String type;
	private String stack;
	private Map<String, Map<String, Object>> label;
	private Integer[] data;
	
	public Series() {
		super();
	}

	public Series(String name, String type, String stack, Map<String, Map<String, Object>> label, Integer[] data) {
		super();
		this.name = name;
		this.type = type;
		this.stack = stack;
		this.label = label;
		this.data = data;
	}


	@Override
	public String toString() {
		return "Series [name=" + name + ", type=" + type + ", stack=" + stack + ", label=" + label + ", data="
				+ Arrays.toString(data) + "]";
	}

	public void setDataArr(Integer[] arr){
		this.data = arr;
	}
	
	public Integer[] getDataArr(){
		return this.data;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStack() {
		return stack;
	}
	
	public void setStack(String stack) {
		this.stack = stack;
	}
	
	public Map<String, Map<String, Object>> getLabel() {
		return label;
	}
	
	public void setLabel(Map<String, Map<String, Object>> label) {
		this.label = label;
	}
	
	

}
