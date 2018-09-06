package com.gdts.selecting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.selecting.entity.LoginInfoUser;
import com.gdts.selecting.entity.SysUser;

@Repository("adminManagerDao")
public class AdminManagerDao extends BaseDaoImpl<LoginInfoUser>{
	
	/**
	 * @Description: 查所有用户
	 * @param @return   
	 * @return List<LoginInfoUser>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月30日
	 */
	@SuppressWarnings("unchecked")
	public List<LoginInfoUser> loginMSG(Integer userType){
		StringBuffer hql = new StringBuffer();
		hql.append("FROM LoginInfoUser");
		hql.append(" WHERE ");
		hql.append(" 1=1");
		hql.append(" AND userType="+userType);
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * @Description: 通过userType查用户
	 * @param @return   
	 * @return List<SysUser>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月30日
	 */
	@SuppressWarnings("unchecked")
	public List<SysUser> findUsers(Integer userType){
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysUser");
		hql.append(" WHERE ");
		hql.append(" 1=1");
		hql.append(" AND userType="+userType);
		return this.getHibernateTemplate().find(hql.toString());
	}

}
