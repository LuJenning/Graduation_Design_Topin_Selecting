package com.gdts.selecting.service;

import java.util.List;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.InstituteInfo;

public interface IInstetuteService {
	
	/**
	 * 
	 * @Description: 删除学院或班级
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public boolean deleteInstituteInfo(InstituteInfo instituteInfo);
	
	/**
	 * 
	 * @Description: 自定义更新学院或者专业信息
	 * @param @param instituteInfo
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public boolean instituteUpdate(InstituteInfo instituteInfo);
	
	/**
	 * 添加学院及专业业务逻辑接口
	 * @author 秦松
	 * @param instituteInfo
	 */
	public void instituteAdd(InstituteInfo instituteInfo);
	/**
	 * 获取院系列表逻辑接口
	 * @author 秦松
	 * @return
	 */
	public List<InstituteInfo> findInstituteList();
	/**
	 * 通过学院Id查询专业列表逻辑接口
	 * @param instituteId
	 * @author 秦松
	 * @return
	 */
	public List<InstituteInfo> findMajorsListByPid(Integer instituteId);
	/**
	 * 查找学院列表逻辑接口
	 * @author 秦松
	 * @param instituteId
	 * @return
	 */
	public InstituteInfo findByInstituteId(Integer instituteId);
	/**
	 * 分页查询学院，专业列表
	 * @author 秦松
	 * @param instituteInfo
	 * @param page
	 * @param row
	 * @return
	 */
	public Result<InstituteInfo> institutePageList(InstituteInfo instituteInfo, int page, int row);
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * @param property
	 * @param value
	 * @return
	 */
	 InstituteInfo findPropertyByValue(String property, String value);
}
