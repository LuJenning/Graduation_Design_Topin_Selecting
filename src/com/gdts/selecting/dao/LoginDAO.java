package com.gdts.selecting.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.selecting.entity.SysUser;
/**
 * 查询管理员账号、密码和类型
 * @author 陆建宁
 * @date 2018-5-20
 */
@Repository("loginDAO")
public class LoginDAO extends BaseDaoImpl<SysUser> {
	@SuppressWarnings("unchecked")
	public SysUser findManager(String userId, String userPassword){
		SysUser sysUser = null;
		List<SysUser> list = this.getHibernateTemplate().find("from SysUser where userId = '"+userId+"' and userPassword = '"+userPassword+"'and userType = 1");
		if(null != list && 0<list.size()){
			sysUser = list.get(0);			
		}
		return sysUser;
	}
	
	/**
	 * 
	 * @Description: 用户登录验证
	 * @return boolean  
	 * @throws
	 * @author 刘春福
	 * @date 2018年5月21日
	 */
	@SuppressWarnings("unchecked")
	public SysUser toLogin(SysUser sysUser){
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysUser ");
		hql.append(" WHERE userId='"+sysUser.getUserId()+"'");
		List<SysUser> list = this.getHibernateTemplate().find(hql.toString());
		return (null != list && 0 < list.size())?list.get(0):null;
	}
	
	/**
	 * 查询教师账号、密码和类型
	 * @author 陆建宁
	 * @param userId
	 * @param userPassword
	 * @return
	 * @date 2018-5-20
	 */
		@SuppressWarnings("unchecked")
		public SysUser findTutor(String userId, String userPassword){
			SysUser sysUser = null;
			List<SysUser> list = this.getHibernateTemplate().find("from SysUser where userId = '"+userId+"' and userPassword = '"+userPassword+"'and userType = 2");
			if(null != list && 0<list.size()){
				sysUser = list.get(0);			
			}
			return sysUser;
		}
	 
		/**
		 * 查询学生账号、密码和类型
		 * @author 陆建宁
		 * @param userId
		 * @param userPassword
		 * @return
		 * @date 2018-5-20
		 */
		@SuppressWarnings("unchecked")
		public SysUser findStudent(String userId, String userPassword){
			SysUser sysUser = null;
			List<SysUser> list = this.getHibernateTemplate().find("from SysUser where userId = '"+userId+"' and userPassword = '"+userPassword+"'and userType = 3");
			if(null != list && 0<list.size()){
				sysUser = list.get(0);			
			}
			return sysUser;
		}
	//
	}
