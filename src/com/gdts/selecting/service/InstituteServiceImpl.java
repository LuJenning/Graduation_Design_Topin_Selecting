package com.gdts.selecting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.dao.InstituteDAO;
import com.gdts.selecting.entity.InstituteInfo;

@Service("instituteService")
public class InstituteServiceImpl implements IInstetuteService {
	@Autowired
	private InstituteDAO instituteDAO;
	
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
	@Override
	public boolean instituteUpdate(InstituteInfo instituteInfo) {
		int flag = instituteDAO.doUpdate(instituteInfo);
		return (0<flag)?true:false;
	}
	
	/**
	 * 添加学院及专业业务逻辑接口实现
	 * @author 秦松
	 * @param instituteInfo
	 */
	@Override
	public void instituteAdd(InstituteInfo instituteInfo) {
		instituteDAO.save(instituteInfo); //直接调用DAO继承模板的save方法
		
	}
	/**
	 * 获取院系列表逻辑接口实现
	 * @author 秦松
	 * @return
	 */
	@Override
	public List<InstituteInfo> findInstituteList() {
		return instituteDAO.findInstituteList();
	}
	/**
	 * 通过学院Id查询专业列表逻辑接口实现
	 * @param instituteId
	 * @author 秦松
	 * @return
	 */
	@Override
	public List<InstituteInfo> findMajorsListByPid(Integer instituteId) {
		
		return instituteDAO.findMajorListByPid(instituteId);
	}
	/**
	 * 查找学院列表逻辑接口实现
	 * @author 秦松
	 * @param instituteId
	 * @return
	 */
	@Override
	public InstituteInfo findByInstituteId(Integer instituteId) {
		// TODO Auto-generated method stub
		return instituteDAO.findByInstituteId(instituteId);
	}
	/**
	 * 分页查询学院，专业列表
	 * @author 秦松
	 * @param instituteInfo
	 * @param page
	 * @param row
	 * @return
	 */
	@Override
	public Result<InstituteInfo> institutePageList(InstituteInfo instituteInfo, int page, int row) {
		// TODO Auto-generated method stub
		return instituteDAO.institutePageList(instituteInfo,page,row);
	}
	
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * 
	 */
	@Override
	public InstituteInfo findPropertyByValue(String property, String value) {
		return instituteDAO.findPropertyByValue(property,value);
	}

	/**
	 * 
	 * @Description: 删除学院或班级
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	@Override
	public boolean deleteInstituteInfo(InstituteInfo instituteInfo) {
		int flag = instituteDAO.doDelete(instituteInfo);
		return (0<flag)?true:false;
	}

}
