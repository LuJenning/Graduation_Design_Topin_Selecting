package com.gdts.selecting.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UserAnalysisSeriesItem<T> {

	private static final String NAME = "用户数";
	private static final String TYPE = "pie";
	private static final String RADIUS = "55%";
	private static final String[] CENTER = {"50%", "60%"};
	private String name;
	private String type;
	private String radius;
	private String[] center;
	private List<T> data;
	private Map<String, Object> itemStyle;
	
	public UserAnalysisSeriesItem(){
		super();
		this.data = new ArrayList<>();
	}
	
	public UserAnalysisSeriesItem(String name, String type, String radius, String[] center, List<T> data,
			Map<String, Object> itemStyle) {
		super();
		this.name = name;
		this.type = type;
		this.radius = radius;
		this.center = center;
		this.data = data;
		this.itemStyle = itemStyle;
	}

	public void addData(T t){
		this.data.add(t);
	}
	
	public void setDefault(){
		this.name = NAME;
		this.type = TYPE;
		this.radius = RADIUS;
		this.center = CENTER;
	}
	
	@Override
	public String toString() {
		return "UserAnalysisSeriesItem [name=" + name + ", type=" + type + ", radius=" + radius + ", center="
				+ Arrays.toString(center) + ", data=" + data + ", itemStyle=" + itemStyle + "]";
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

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String[] getCenter() {
		return center;
	}

	public void setCenter(String[] center) {
		this.center = center;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Map<String, Object> getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Map<String, Object> itemStyle) {
		this.itemStyle = itemStyle;
	}
	
}
