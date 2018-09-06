package com.gdts.selecting.util;

public class SeriesData {

	private Integer value;
	private String name;
	
	public SeriesData() {
		super();
	}

	@Override
	public String toString() {
		return "SeriesData [value=" + value + ", name=" + name + "]";
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public SeriesData(Integer value, String name) {
		super();
		this.value = value;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
