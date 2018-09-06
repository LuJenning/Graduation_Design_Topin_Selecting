package com.gdts.core.service.impl;

import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gdts.core.dao.BaseDao;
import com.gdts.core.service.BaseService;
/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ BaseServiceImpl.java 2015-7-15 上午07:10:48
 */
@Service("baseService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor =RuntimeException.class)
public  class BaseServiceImpl<T> implements BaseService<T> {
	protected final Log log = LogFactory.getLog(getClass());
	
	protected BaseDao<T> dao;
	
	public  void setDao(BaseDao<T> dao){
		this.dao=dao;
	}

	@Override
	public T save(T entity) {
		
		return dao.save(entity);
	}

	@Override
	public T merge(T entity) {
		
		return dao.merge(entity);
	}

	@Override
	public void update(T entity) {
		dao.update(entity);
		
	}

	@Override
	public T get(Class<T> clazz, Serializable id) {
		
		return dao.get(clazz, id);
	}

	@Override
	public void remove(Class<T> clazz, Serializable id) {
		dao.remove(clazz, id);
	}
	@Override
	public void remove(T entity) {
		dao.remove(entity);
	}

	

}
