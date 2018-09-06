package com.gdts.selecting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.dao.TutorDAO;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 实现层
 * @author 陆建宁
 * @date 2018 5-30
 */
@Service("tutorService")
public class TutorServiceImpl implements ITutorService{
	@Autowired
	private TutorDAO tutorDAO;
	/**
	 * 分页
	 * 
	 * @author 陆建宁
	 */
	@Override
	public Result<SysUser> find(SysUser sysUser, int page, int row) {
		return tutorDAO.find(sysUser,  page,  row);
	}

	/**
	 * 删除教师
	 * @author 陆建宁
	 */
	@CacheEvict(value="userCache",key="#sysUser.userId + 'add'")
	public void deleteTutor(SysUser sysUser) {
		tutorDAO.remove(sysUser);
	}
	/**
	 * 查找所有教师
	 * @author 陆建宁
	 */
	@Override
	public List<SysUser> findAllTutor() {
		return tutorDAO.findAllTutor();
	}

	/**
	 * 更新教师信息
	 * @author 陆建宁
	 * 
	 */
	@Override
	public void updateTutor(SysUser sysUser) {
		tutorDAO.update(sysUser);
	}
	/**
	 * 根据教师名字查询教师
	 * @author 陆建宁
	 */
	@Override
	public List<SysUser> findTutorByName(SysUser sysUser) {
		// TODO Auto-generated method stub
		return tutorDAO.findTutorByName(sysUser);
	}
	/**
	 * 添加教师
	 * @author 陆建宁
	 */
	@Cacheable(value="userCache", key="#sysUser.userId + 'add'")
	public void add(SysUser sysUser) {
		 tutorDAO.save(sysUser);
		
	}
	/**
	 * 根据主键id查找一条数据
	 * @author 陆建宁
	 */
	@Override
	public SysUser findByPriId(int id) {
		List<SysUser> List = (List<SysUser>) tutorDAO.findByPriId(id);
		return (null != List && 0<List.size())?List.get(0):null;
	}
	/**
	 * 模糊查询
	 * @author 陆建宁
	 */
	@Override
	public SysUser findTutorByProperty(String property, String value) {
		// TODO Auto-generated method stub
		return null;
	}

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
	@Override
	public Result<TeacherSeeSelecting> queryTeacherAllForAdmin(String likeString, SysUser sysUser, int page, int row) {
		return tutorDAO.queryTeacherAllForAdmin(likeString, sysUser, page, row);
	}
	
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
	@Override
	public Result<TeacherSeeSelecting> queryStudentAllForAdmin(String likeString, SysUser sysUser, int page, int row) {
		return tutorDAO.queryStudentAllForAdmin(likeString, sysUser, page, row);
	}

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
	@Override
	public boolean saveTeacherByAjax(SysUser sysUser) {
		Integer num = tutorDAO.saveTeacherByAjax(sysUser);
		System.out.println("num:"+num);
		return (null != num && 0<num)?true:false;
	}
	
}
