package com.gdts.core.service;

import java.io.Serializable;
/**
 *  
 * @version $ BaseService.java 2015-7-14 下午11:37:01
 */
public interface BaseService<T> {

	  /**
       * Generic method to save an object.
	   * @param entity the object to save
	   * @return
	   */
	  public  T save(T entity);
	  /**
	   * 
	   * @param entity  model class to lookup
	   * @return
	   */
	  public  T merge(T entity);
	  /**
	   * 
	   * @param entity  model class to lookup
	   */
	  public  void update(T entity);
	  
	  /**
	   * 
	   * @param clazz  model class to lookup
	   * @param id  the identifier (primary key) of the class
	   * @return
	   */
	  public  T get(Class<T> clazz,Serializable id);
	  /**
	   * delete an object based on class and id
	   * @param clazz
	   * @param id
	   */
	  public  void remove(Class <T> clazz,Serializable id);
	  /**
	   * delete an object based on class
	   * @param entity
	   */
	  public  void remove(T entity);
	 
	  
	

}
