package com.gdts.core.util;

/**
 * 通过Gson工具打印实体对象和提示字符串
 * ClassName: JsonEntityMapping 
 * @Description: TODO
 * @author liuchunfu
 * @date 2018年5月27日
 */
public class JsonEntityMapping<T> {

	private T t;

	private String msg;
	
	public JsonEntityMapping() {
		super();
	}

	public JsonEntityMapping(T t, String msg) {
		super();
		this.t = t;
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonEntityMapping [t=" + t + ", msg=" + msg + "]";
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
