package com.gdts.selecting.service;

import com.gdts.selecting.util.LoginMSGOption;
import com.gdts.selecting.util.UserAnalysis;
/**
 * 
 * ClassName: IAdministorManager 
 * @Description: 管理员管理统计接口
 * @author liuchunfu
 * @date 2018年5月30日
 */
public interface IAdminManagerService<T> {

	/**
	 * loginMSG
	 * @Description: 统计用户活跃量
	 * @param @return   
	 * @return LoginMSGOption<T>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	public LoginMSGOption<T> loginMSG();
	
	/**
	 * countSysUserAll
	 * @Description: 统计不同类型用户数
	 * @param @return   
	 * @return UserAnalysis<T>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	public UserAnalysis<T> countSysUserAll();
}
