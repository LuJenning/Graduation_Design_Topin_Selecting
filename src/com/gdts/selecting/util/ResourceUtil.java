package com.gdts.selecting.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 国际化工具类
 * 思路：1：读取配置文件(包括本地语言选项，哪些资源文件)
 * 	   2：根据配置文件读取对应本地语言所有的国际化资源文件
 * 	   3：讲所有国际化资源的内容放入集合备用
 * 	   4：提供供外部调用的方法，进行国际化的key-value转换	
 * @author 
 */
public class ResourceUtil{
	//国际化配置文件的名称
	private  static final String CONFIG_FILE_NAME_EN = "conf/i18nForEn.properties";
	private  static final String CONFIG_FILE_NAME_ZH = "conf/i18nForZh.properties";
	//国际化配置文件里面国际化资源文件的key
	private static final String RESOURCE_FILES = "i18nFiles";
	//国际化配置文件里面当前的语言选项
	private static final String LOCALE_CODE = "i18nLocale";
	//
	private static Map<String,String> i18nKeyValue= null;
	
	/**
	 * 
	 * @Description: 供英文调用
	 * @param    
	 * @return void  
	 * @throws
	 * @author 
	 * @date 2018年6月25日
	 */
	public void loadForEn(){
		//读取配置文件，将配置文件赋值给properties对象
		Properties configPro = new Properties();
		try {
			configPro.load(ResourceUtil.class.getClassLoader()
					.getResourceAsStream(CONFIG_FILE_NAME_EN));
			//获取所有国际化资源的前缀 sysmanage  后缀zn_CN	
			String resourceFiles= configPro.getProperty(RESOURCE_FILES).toString();
			String locale = configPro.getProperty(LOCALE_CODE).toString();
			String[] resouceArray =  resourceFiles.split(",");
			
			i18nKeyValue = new HashMap<String,String>();
			//循环资源文件，将所有的key和value放入map中
			ResourceBundle bundle =null;
			for(int i = 0;resouceArray!=null&& i<resouceArray.length;i++ ){
				try{
					ResourceBundle.clearCache();
					//得到某个资源文件里面的所有内容
					bundle = ResourceBundle.getBundle("conf/"+resouceArray[i]+"_"+locale);
					@SuppressWarnings("rawtypes")
					Set set =(bundle!=null)?bundle.keySet():null;
					
					Iterator<String> iter = null;
					iter= (set!=null)?set.iterator():null;
					while(iter!=null&&iter.hasNext()){
						String key = iter.next();
						i18nKeyValue.put(key, bundle.getString(key));
						System.out.println("加载资源文件["+key+"]"+"["+bundle.getString(key)+"]");
					}
				}catch(java.util.MissingResourceException e){
					System.out.println("没找到资源文件"+e);
				}
				
			}		
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 默认调用中文
	 * @param    
	 * @return void  
	 * @throws
	 * @author 
	 * @date 2018年6月25日
	 */
	@SuppressWarnings("unchecked")
	public void loadForZh(){
		//读取配置文件，将配置文件赋值给properties对象
		Properties configPro = new Properties();
		try {
			configPro.load(ResourceUtil.class.getClassLoader()
					.getResourceAsStream(CONFIG_FILE_NAME_ZH));
			//获取所有国际化资源的前缀 sysmanage  后缀zn_CN	
			String resourceFiles= configPro.getProperty(RESOURCE_FILES).toString();
			String locale = configPro.getProperty(LOCALE_CODE).toString();
			String[] resouceArray =  resourceFiles.split(",");
			
			i18nKeyValue = new HashMap<String,String>();
			//循环资源文件，将所有的key和value放入map中
			ResourceBundle bundle =null;
			for(int i = 0;resouceArray!=null&& i<resouceArray.length;i++ ){
				try{
					ResourceBundle.clearCache();
					//得到某个资源文件里面的所有内容
					bundle = ResourceBundle.getBundle("conf/"+resouceArray[i]+"_"+locale);
					@SuppressWarnings("rawtypes")
					Set set =(bundle!=null)?bundle.keySet():null;
					
					Iterator<String> iter = null;
					iter= (set!=null)?set.iterator():null;
					while(iter!=null&&iter.hasNext()){
						String key = iter.next();
						i18nKeyValue.put(key, bundle.getString(key));
						System.out.println("加载资源文件["+key+"]"+"["+bundle.getString(key)+"]");
					}
				}catch(java.util.MissingResourceException e){
					System.out.println("没找到资源文件"+e);
				}
				
			}		
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}
	
	/**
	 * 提供外部调用的国际化方法 
	 * @param keyCode properties文件的key
	 * @return  properties文件的value
	 */
	public static String text(String keyCode){
		if(i18nKeyValue.containsKey(keyCode)){
			return i18nKeyValue.get(keyCode);
		}
		return keyCode;
	}
	
	public static Set<String> getKeys(){
		return i18nKeyValue.keySet();
	}
}