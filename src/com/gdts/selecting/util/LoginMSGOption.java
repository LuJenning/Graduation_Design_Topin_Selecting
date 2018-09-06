package com.gdts.selecting.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * ClassName: LoginMSGOption用户登录日志 
 * @Description: 柱形图工具实体组件
 * @author liuchunfu
 * @date 2018年5月30日
 */
@SuppressWarnings("unused")
public class LoginMSGOption <T>{
	/**
	 * option = {
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                color : ['#ff7f50','#87cefa', '#32C5E9'],
                legend: {
                    data: ['管理员', '教师','学生']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis:  {
                    type: 'value'
                },
                yAxis: {
                    type: 'category',
                    data: ['周一','周二','周三','周四','周五','周六','周日']
                },
                series: [
                    {
                        name: '管理员',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: [320, 302, 301, 334, 390, 330, 320]
                    },
                    {
                        name: '教师',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: [120, 132, 101, 134, 90, 230, 210]
                    },
                    {
                        name: '学生',
                        type: 'bar',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'insideRight'
                            }
                        },
                        data: [220, 182, 191, 234, 290, 330, 310]
                    },
                ]
            };
	 */
	//默认值
	private static final String DEFAULT_TOOLTIP_TRIGGER_KEY = "trigger";
	private static final String DEFAULT_TOOLTIP_TRIGGER_VALUE = "axis";
	private static final String DEFAULT_TOOLTIP_AXISPOINTER_TYPE_KEY = "axisPointer";
	private static final String DEFAULT_TOOLTIP_AXISPOINTER_TYPE_VALUE = "shadow";
	private static final String[] DEFAULT_COLOR = {"#ff7f50","#87cefa", "#32C5E9"};
	private static final String DEFAULT_LEGEND_KEY = "data";
	private static final String[] DEFAULT_LENGEND_VALUE = {"管理员","教师", "学生"};
	private static final String DEFAULT_GRID_LEFT_KEY = "left";
	private static final String DEFAULT_GRID_RIGHT_KEY = "right";
	private static final String DEFAULT_GRID_BOTTOM_KEY = "bottom";
	private static final String DEFAULT_GRID_LEFT_VALUE = "3%";
	private static final String DEFAULT_GRID_RIGHT_VALUE = "4%";
	private static final String DEFAULT_GRID_BOTTOM_VALUE = "3%";
	private static final String DEFAULT_GRID_CONTAINLABEL_KEY = "containLabel";
	private static final boolean DEFAULT_GRID_CONTAINLABEL_VALUE = true;
	private static final String DEFAULT_XAXIS_TYPE_KEY = "type";
	private static final String DEFAULT_XAXIS_TYPE_VALUE = "value";
	private static final String DEFAULT_YAXIS_TYPE_KEY = "type";
	private static final String DEFAULT_YAXIS_TYPE_VALUE = "category";
	private static final String DEFAULT_YAXIS_DATA_KEY = "data";
	private static final String[] DEFAULT_YAXIS_DATA_VALUE = {"周一","周二","周三","周四","周五","周六","周日"};


	private Map<String, Object> tooltip;
	private String[] color;
	private Map<String, Object> legend;
	private Map<String, Object> grid;
	private Map<String, Object> xAxis;
	private Map<String, Object> yAxis;
	private List<T> series;
	
	public LoginMSGOption() {
		super();
		init();
	}
	
	public void setDefault(){
		this.tooltip.put(DEFAULT_TOOLTIP_TRIGGER_KEY , DEFAULT_TOOLTIP_TRIGGER_VALUE);
		this.tooltip.put(DEFAULT_TOOLTIP_AXISPOINTER_TYPE_KEY, DEFAULT_TOOLTIP_AXISPOINTER_TYPE_VALUE);
		this.color = DEFAULT_COLOR;
		this.legend.put(DEFAULT_LEGEND_KEY, DEFAULT_LENGEND_VALUE);
		this.grid.put(DEFAULT_GRID_LEFT_KEY, DEFAULT_GRID_LEFT_VALUE);
		this.grid.put(DEFAULT_GRID_RIGHT_KEY, DEFAULT_GRID_RIGHT_VALUE);
		this.grid.put(DEFAULT_GRID_BOTTOM_KEY, DEFAULT_GRID_BOTTOM_VALUE);
		this.grid.put(DEFAULT_GRID_CONTAINLABEL_KEY, DEFAULT_GRID_CONTAINLABEL_VALUE);
		this.xAxis.put(DEFAULT_XAXIS_TYPE_KEY, DEFAULT_XAXIS_TYPE_VALUE);
		this.yAxis.put(DEFAULT_YAXIS_TYPE_KEY, DEFAULT_YAXIS_TYPE_VALUE);
		this.yAxis.put(DEFAULT_YAXIS_DATA_KEY, DEFAULT_YAXIS_DATA_VALUE);
	}
	
	public void setTooltip(String key, String value){
		this.tooltip.put(key, value);
	}
	
	public void setColor(String[] color){
		this.color = color;
	}
	
	public void setLegend(String key, String value){
		this.legend.put(key, value);
	}
	
	public void setGrid(String key, String value){
		this.grid.put(key, value);
	}
	
	public void setXAxis(String key, String value){
		this.xAxis.put(key, value);
	}
	
	public void setYAxis(String key, String value){
		this.yAxis.put(key, value);
	}
	
	public void setSeries(T value){
		this.series.add(value);
	}
	
	public LoginMSGOption(Map<String, Object> tooltip, String[] color, Map<String, Object> legend, Map<String, Object> grid, Map<String, Object> xAxis,
			Map<String, Object> yAxis, List<T> series) {
		super();
		this.tooltip = tooltip;
		this.color = color;
		this.legend = legend;
		this.grid = grid;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.series = series;
	}

	private void init(){
		tooltip = new HashMap<String, Object>();
		color = new String[]{};
		legend = new HashMap<String, Object>();
		grid = new HashMap<String, Object>();
		xAxis = new HashMap<String, Object>();
		yAxis = new HashMap<String, Object>();
		series = new ArrayList<T>();
	}

	@Override
	public String toString() {
		return "LoginMSGOption [tooltip=" + tooltip + ", color=" + Arrays.toString(color) + ", legend=" + legend
				+ ", grid=" + grid + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", series=" + series + "]";
	}
	
}
