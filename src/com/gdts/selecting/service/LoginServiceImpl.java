package com.gdts.selecting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdts.selecting.dao.LoginDAO;
import com.gdts.selecting.dao.LoginInfoUserDao;
import com.gdts.selecting.entity.LoginInfoUser;
import com.gdts.selecting.entity.SysUser;
@Service("loginService")
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private LoginDAO loginDAO;
	@Autowired
	private LoginInfoUserDao loginInfoUserDao;
	private SysUser sysUser;
	/**
	 * 封装到DAO层
	 * @author 陆建宁
	 * @date 2018-5-20
	 * @param userId
     * @param userPassword 
     * @return 
	 */
	@Override
	public boolean findManager(String userId, String userPassword) {
		boolean flag = false;
		if (sysUser != null) {
			sysUser = loginDAO.findManager(userId, userPassword);
			flag = true;
		}
		return flag;

	}

	/**
	 * 封装到DAO层
	 * @author 陆建宁
	 * @date 2018-5-20
	 * @param userId
     * @param userPassword 
     * @return 
	 */
	@Override
	public boolean findTutor(String userId, String userPassword) {
		boolean flag = false;
		if (sysUser != null) {
			sysUser = loginDAO.findTutor(userId, userPassword);
			flag = true;
		}

		return flag;

	}
    /**
     * 封装到DAO层
	 * @author 陆建宁
	 * @date 2018-5-20
	 * @param userId
     * @param userPassword 
     * @return 
     */
	@Override
	public boolean findStudent(String userId, String userPassword) {
		boolean flag = false;
		if (sysUser != null) {
			sysUser = loginDAO.findStudent(userId, userPassword);
			flag = true;
		}

		return flag;

	}

	/**
	 * @Description: 用户登录验证
	 * @param SysUser
	 * @return boolean  
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月21日
	 */
	@Override
	public SysUser toLogin(SysUser sysUserIn){
		SysUser sysUser = loginDAO.toLogin(sysUserIn);
		return (null != sysUser )?sysUser:null;
	}

	/**
	 * @Description: 用户登录日志记录
	 * @param LoginInfoUser 
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月30日
	 */
	@Override
	public void saveLoginInfo(LoginInfoUser loginInfoUser) {
		loginInfoUserDao.save(loginInfoUser);
	}
}