package com.gdts.selecting.service;

import java.util.List;
import com.gdts.selecting.entity.SysUser;

public interface ISysUserService {
	/**
	 * 添加学生信息逻辑接口
	 * @author 秦松
	 * @param sysUser
	 */
	 public void addStudent(SysUser sysUser);
	 
	 /**
	  * administratorLimit 管理员分页
	  * totel 总数
	  * @param start 分页开始位置
	  * @param end 分页结束位置
	  * @return
	  */
	 public List<SysUser> administratorLimit(Long totel, Long start, Long end,int userType);
	 
	 /**
	  * 
	  * @Description: 根据用户id查询用户实体
	  * @param @param id
	  * @param @return   
	  * @return SysUser  
	  * @throws
	  * @author liuchunfu
	  * @date 2018年5月27日
	  */
	 public SysUser getSysUserById(int id);
	 
	 /**
	  * administratorLimit 管理员分页
	  * @param start 分页开始位置
	  * @param end 分页结束位置
	  * @return
	  */
	 public Long getTotel(String tableName,int userType);
	 /**
	  * 学生信息更新
	  * @author 秦松
	  * @param sysUser
	  */
	public void updateStudent(SysUser sysUser);

	/**
	 * 
	 * @Description: 管理员删除用户
	 * @param @param sysUser
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月27日
	 */
	public void delStudent(SysUser sysUser);
	/**
	 * 查询用户id
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	public SysUser findByUserId(String userId);
/**
 * 模糊查询
 * @author 陆建宁
 * @param property
 * @param value
 * @return
 */
	public SysUser findPropertyByValue(String property,String value);
}
