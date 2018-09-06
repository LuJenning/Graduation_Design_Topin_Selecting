package com.gdts.selecting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdts.selecting.dao.ClassDAO;
import com.gdts.selecting.entity.ClassInfo;
@Service("classService")
public class ClassServiceImpl implements IClassService {
	@Autowired
	private ClassDAO classDAO;
	/**
	 * 添加班级信息逻辑接口实现
	 * @author 秦松
	 * @param classInfo
	 */
	@Override
	public void classAdd(ClassInfo classInfo) {
		classDAO.save(classInfo);

	}
	/**
	 *根据专业id查询班级列表信息接口实现
	 *@author 秦松
	 * @param instituteId
	 * @return
	 */
	@Override
	public List<ClassInfo> findClassListById(Integer instituteId) {
		// TODO Auto-generated method stub
		return classDAO.findClassListById(instituteId);
	}
	
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
	@Override
	public ClassInfo findClassListByClassId(Integer classId) {
		/*List<ClassInfo> list = classDAO.findClassListById(classId);
		return (null != list && 0<list.size())?list.get(0):null;*/
		return classDAO.findClassByClassId(classId);
	}
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * 
	 */
	@Override
	public ClassInfo findPropertyByValue(String property, String value) {
		return classDAO.findPropertyByValue(property,value);
	}
	/**
	 * 查询班级信息（根据班级id）
	 * @author Tony
	 * @date 2018年6月19日
	 */
	@Override
	public ClassInfo findClassByClassId(Integer classId) {
		// TODO Auto-generated method stub
		return classDAO.findClassByClassId(classId);
	}

}
