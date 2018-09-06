package com.gdts.selecting.util;

import java.util.HashMap;
import java.util.Map;

public class UserAnalysisSeries {
	
	private static final String ITEMSTYLE_EMPHSIS_KEY = "emphasis";
	private static final String ITEMSTYLE_EMPHSIS_SHADOWBLUR_KEY = "shadowBlur";
	private static final int ITEMSTYLE_EMPHSIS_SHADOWBLUR_VALUE = 10;
	private static final String ITEMSTYLE_EMPHSIS_SHADOWOFFSETX_KEY = "shadowOffsetX";
	private static final int ITEMSTYLE_EMPHSIS_SHADOWOFFSETX_VALUE = 0;
	private static final String ITEMSTYLE_EMPHSIS_SHADOWCOLOR_KEY = "shadowColor";
	private static final String ITEMSTYLE_EMPHSIS_SHADOWCOLOR_VALUE = "rgba(0, 0, 0, 0.5)";
	
	private Map<String, Object> emphasis;
	private Map<String, Object> itemStyle;

	public UserAnalysisSeries(){
		super();
		init();
	}
	
	public void setDefault(){
		this.emphasis.put(ITEMSTYLE_EMPHSIS_SHADOWBLUR_KEY, ITEMSTYLE_EMPHSIS_SHADOWBLUR_VALUE);
		this.emphasis.put(ITEMSTYLE_EMPHSIS_SHADOWOFFSETX_KEY, ITEMSTYLE_EMPHSIS_SHADOWOFFSETX_VALUE);
		this.emphasis.put(ITEMSTYLE_EMPHSIS_SHADOWCOLOR_KEY, ITEMSTYLE_EMPHSIS_SHADOWCOLOR_VALUE);
		this.itemStyle.put(ITEMSTYLE_EMPHSIS_KEY, this.emphasis);
	}
	
	private void init() {
		this.emphasis = new HashMap<>();
		this.itemStyle = new HashMap<>();
	}
	
	public Map<String, Object> getItemStyle(){
		return this.itemStyle;
	}
}
