package com.gdts.selecting.service;

import com.gdts.selecting.entity.LoginInfoUser;
import com.gdts.selecting.entity.SysUser;

public interface ILoginService {
//	boolean isLogon(SysUser SysUser);
/**
 * 找管理员
 * @author 陆建宁
 * @param userId
 * @param userPassword
 * @return
 * @date 2018-5-20
 */
	boolean findManager(String userId, String userPassword);
/**
 * 找老师
 * @author 陆建宁
 * @param userId
 * @param userPassword
 * @return
 * @date 2018-5-20
 */
	boolean findTutor(String userId, String userPassword);
/**
 * 找学生
 * @author 陆建宁
 * @param userId
 * @param userPassword
 * @return
 * @date 2018-5-20
 */
	boolean findStudent(String userId, String userPassword);
	
	/**
	 * @Description: 用户登录验证
	 * @param SysUser
	 * @return boolean  
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月21日
	 */
	SysUser toLogin(SysUser sysUser);
	
	/**
	 * @Description: 用户登录日志记录
	 * @param LoginInfoUser 
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月30日
	 */
	void saveLoginInfo(LoginInfoUser loginInfoUser);
}
