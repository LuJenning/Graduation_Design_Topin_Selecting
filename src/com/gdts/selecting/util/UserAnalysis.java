package com.gdts.selecting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * text: '站点用户总量分析',
 * subtext: '来自毕业设计选题系统',
 * ClassName: UserAnalysis 
 * @Description: TODO
 * @author liuchunfu
 * @date 2018年5月31日
 */
public class UserAnalysis<T> {

	private static final String TITLE_TEXT_KEY = "text";
	private static final String TITLE_TEXT_VALUE = "站点用户总量分析";
	private static final String TITLE_SUBTEXT_KEY = "subtext";
	private static final String TITLE_SUBTEXT_VALUE = "来自毕业设计选题系统";
	private static final String TITLE_X_KEY = "x";
	private static final String TITLE_X_VALUE = "center";
	private static final String[] COLOR = {"#ff7f50","#87cefa", "#32C5E9"};
	private static final String TOOLTIP_TRIGGER_KEY = "trigger";
	private static final String TOOLTIP_TRIGGER_VALUE = "item";
	private static final String TOOLTIP_FORMATTER_KEY = "formatter";
	private static final String TOOLTIP_FORMATTER_VALUE = "{a} <br/>{b} : {c} ({d}%)";
	private static final String LEGEND_ORIENT_KEY = "orient";
	private static final String LEGEND_ORIENT_VALUE = "vertical";
	private static final String LEGEND_LEFT_KEY = "left";
	private static final String LEGEND_LEFT_VALUE = "left";
	private static final String LEGEND_DATA_KEY = "data";
	private static final String[] LEGEND_DATA_VALUE = {"管理员","教师","学生"};
	
	private Map<String, String> title;
	@SuppressWarnings("unused")
	private String[] color;
	private Map<String, String> tooltip;
	private Map<String, Object> legend;
	private List<T> series;
	
	public UserAnalysis(){
		super();
		init();
	}

	public void setSeries(T t){
		series.add(t);
	}
	
	public void setDefault(){
		this.color = COLOR;
		this.title.put(TITLE_TEXT_KEY, TITLE_TEXT_VALUE);
		this.title.put(TITLE_SUBTEXT_KEY, TITLE_SUBTEXT_VALUE);
		this.title.put(TITLE_X_KEY, TITLE_X_VALUE);
		this.tooltip.put(TOOLTIP_TRIGGER_KEY, TOOLTIP_TRIGGER_VALUE);
		this.tooltip.put(TOOLTIP_FORMATTER_KEY, TOOLTIP_FORMATTER_VALUE);
		this.legend.put(LEGEND_ORIENT_KEY, LEGEND_ORIENT_VALUE);
		this.legend.put(LEGEND_LEFT_KEY, LEGEND_LEFT_VALUE);
		this.legend.put(LEGEND_DATA_KEY, LEGEND_DATA_VALUE);
	}
	
	private void init() {
		this.title = new HashMap<>();
		this.tooltip = new HashMap<>();
		this.legend = new HashMap<>();
		this.series = new ArrayList<>();
	}
	
}
