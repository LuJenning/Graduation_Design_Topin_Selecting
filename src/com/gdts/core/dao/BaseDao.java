package com.gdts.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Data Access Object (DAO) interface.   This is an interface
 * used to tag our DAO classes and to provide common methods to all DAOs.
 *
 */
public interface BaseDao<T> {

	
	  /**
	   * 
	   * @param entity
	   * @return
	   */
	  public  T save(T entity);
	  /**
	   * 
	   * @param entity
	   * @return
	   */
	  public  T merge(T entity);
	  /**
	   * 
	   * @param entity
	   */
	  public  void update(T entity);
	  /**
	   * 
	   * @param clazz
	   * @param id
	   * @return
	   */
	  public  T get(Class<T> clazz,Serializable id);
	  /**
	   * 
	   * @param clazz
	   * @param id
	   */
	  public  void remove(Class <T> clazz,Serializable id);
	  /**
	   * 
	   * @param entity
	   */
	  public  void remove(T entity);
	  /**
	   * 
	   * @return
	   */
	  public  List<T> getAll(Class<T> clazz);
	  /**
	   * 
	   * @param queryString
	   * @param params
	   * @return
	   */
	  public  List<T> findByHql(String queryString, Object[] params);
	  /**
	   * 
	   * @param queryString
	   * @param params
	   * @param start
	   * @param limit
	   * @return
	   */
	  public  List<T> findByHql(String queryString, Object[] params, int start, int limit);

	  /**
	   * Remove the given object from the Session cache
	   * @param entity
	   */
	  public  void evict(T entity);
	  /**
	   * 
	   */
	  public  void flush();
	  /**
	   * 
	   * @param paramString
	   * @param paramArrayOfObject
	   * @return
	   */
	  public  Long update(String queryString, Object[] values);
	  
		/**
		 * 
		 * @param String
		 * @return T
		 */
	  public T findByHql(String hql);
	}
