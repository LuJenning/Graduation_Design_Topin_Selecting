package com.gdts.core.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 前端分页工具
 * @author liuchunfu
 * @param <T>
 */
public class ResoultMapping<T> {

	private Map<String, Object> resoult;
	private static final String MAXTOTEL = "maxTotel";
	private static final String START = "start";
	
	public ResoultMapping() {
		super();
		this.init();
	}
	
	/**
	 * Long totel 表中总记录数
	 * Long start 分页后开始的序号
	 * String key 结果集key,推荐使用实体对象
	 * List<T> resoult 结果集
	 * @Description: 有参构造
	 * @param @param totel
	 * @param @param key
	 * @param @param resoult   
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月25日
	 */
	public ResoultMapping(Long totel, Long start, String key, List<T> resoult) {
		super();
		this.init();
		this.resoult.put(ResoultMapping.MAXTOTEL, totel);
		this.resoult.put(ResoultMapping.START,start);
		this.resoult.put(key,resoult);
	}
	
	/**
	 * 
	 * @Description: 初始化
	 * @param    
	 * @return void  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月25日
	 */
	public void init(){
		this.resoult = new HashMap<String, Object>();
	}
	
	/**
	 * 
	 * @Description: 获取结果集
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月25日
	 */
	public Map<String, Object> getResoult() {
		return resoult;
	}
	
	/**
	 * Long totel 表中总记录数
	 * Long start 分页后开始的序号
	 * String key 结果集key,推荐使用实体对象
	 * List<T> resoult 结果集
	 * @Description: set方式注入结果集
	 * @param @param key
	 * @param @param resoult   
	 * @return void  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月25日
	 */
	public void setResoult(Long totel, Long start, String key, List<T> resoult) {
		//if(null == resoult || 0 >= resoult.size())return;
		this.resoult.put(ResoultMapping.MAXTOTEL, totel);
		this.resoult.put(ResoultMapping.START,start);
		this.resoult.put(key, resoult);
	}
}
