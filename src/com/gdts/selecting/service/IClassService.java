package com.gdts.selecting.service;

import java.util.List;

import com.gdts.selecting.entity.ClassInfo;

public interface IClassService {
	/**
	 * 添加班级信息逻辑接口
	 * @author 秦松
	 * @param classInfo
	 */
	public void classAdd(ClassInfo classInfo);
	/**
	 *根据专业id查询班级列表信息接口
	 *@author 秦松
	 * @param instituteId
	 * @return
	 */
	public List<ClassInfo> findClassListById(Integer instituteId);
	
	/**
	 * 
	 * @Description: 通过班级id查班级
	 * @param @param classId
	 * @param @return   
	 * @return ClassInfo  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月10日
	 */
	public ClassInfo findClassListByClassId(Integer classId);
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * @param property
	 * @param value
	 * @return
	 */
	ClassInfo findPropertyByValue(String property,String value);
	/**
	 * 
	 * @Description: 查询班级信息（根据班级id）
	 * @param @param classId
	 * @param @return   
	 * @return ClassInfo  
	 * @throws
	 * @author Tony
	 * @date 2018年6月19日
	 */
	public ClassInfo findClassByClassId(Integer classId);
}
