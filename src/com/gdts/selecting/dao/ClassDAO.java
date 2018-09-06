package com.gdts.selecting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.selecting.entity.ClassInfo;

@Repository("classDAO")
public class ClassDAO extends BaseDaoImpl<ClassInfo> {
	/**
	 * 根据专业id查询班级信息列表
	 * @author 秦松
	 * @param instituteId
	 * @return
	 */
	public List<ClassInfo> findClassListById(Integer instituteId) {
		String hql="from ClassInfo where instituteId="+instituteId;
		System.out.println(hql);
		return this.getHibernateTemplate().find(hql);
	}
	

/**
 * 模糊查询
 * @author 陆建宁
 * @param property
 * @param value
 * @return
 */
	public ClassInfo findPropertyByValue(String property, String value) {
		ClassInfo classInfo = null;
		@SuppressWarnings("unchecked")
		List<ClassInfo> list = this.getHibernateTemplate().find("from ClassInfo where "+property+" = '"+value+"'");
		if(list != null && 0<list.size()){
			classInfo = list.get(0);
		}
		return classInfo;
	}

	/**
	 * 
	 * @Description: 根据班级id查询班级信息
	 * @param @param classId
	 * @param @return   
	 * @return ClassInfo  
	 * @throws
	 * @author Tony
	 * @date 2018年6月19日
	 */
	public ClassInfo findClassByClassId(Integer classId) {
		String hql="from ClassInfo where classId="+classId;
		System.out.println(hql);
		List<ClassInfo> classInfo=this.getHibernateTemplate().find(hql);
		return (null != classInfo && 0<classInfo.size())?classInfo.get(0):null;
	}
}
