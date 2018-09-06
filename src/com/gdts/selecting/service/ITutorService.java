package com.gdts.selecting.service;

import java.util.List;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 接口层
 * @author 陆建宁
 * @date 2018 5-30
 */
public interface ITutorService {
	/**
	 * 分页
	 * @author 陆建宁
	 * @param topic
	 * @param page
	 * @param row
	 * @return
	 */
	Result<SysUser> find(SysUser topic,int page, int row);
	/**
	 * 添加教师
	 * @author 陆建宁
	 * @param sysUser
	 */
	 void add(SysUser sysUser);
	/**
	 * 删除教师
	 * @author 陆建宁
	 * @param sysUser
	 */
	 void deleteTutor(SysUser sysUser);
	/**
	 * 查勋所有教师
	 * @author 陆建宁
	 * @return
	 */
	 List<SysUser> findAllTutor();
	/**
	 * 更新教师
	 * @author 陆建宁
	 * @param sysUser
	 */
	 void updateTutor(SysUser sysUser);
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * @param property
	 * @param value
	 * @return
	 */
	 SysUser findTutorByProperty(String property,String value);
	/**
	 * 根据名字查询教师
	 * @author 陆建宁
	 * @param sysUser
	 * @return
	 */
	 List<SysUser> findTutorByName(SysUser sysUser);
	/**
	 * 根据主键id查找一条数据
	 * @author 陆建宁
	 * @param id
	 * @return
	 */
	 public SysUser findByPriId(int id);
	 
	 /**
	 * 
	 * @Description: 教师列表
	 * @param @param otherEntity
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	 public Result<TeacherSeeSelecting> queryTeacherAllForAdmin(String likeString,SysUser sysUser, int page, int row);

	 /**
	 * 
	 * @Description: 学生列表
	 * @param @param otherEntity
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	 public Result<TeacherSeeSelecting> queryStudentAllForAdmin(String likeString,SysUser sysUser, int page, int row);

	 /**
	  * 
	  * @Description: saveTeacherByAjax保存修改后的教师
	  * @param @param sysUser
	  * @param @return   
	  * @return boolean  
	  * @throws
	  * @author liuchunfu
	  * @date 2018年6月9日
	  */
	 public boolean saveTeacherByAjax(SysUser sysUser);
}
