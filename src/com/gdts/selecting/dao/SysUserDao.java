package com.gdts.selecting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.selecting.entity.SysUser;
@Repository("sysUserDAO")
public class SysUserDao extends BaseDaoImpl<SysUser> {

	/**
	  * administratorLimit 管理员分页
	  * @param start 分页开始位置
	  * @param end 分页结束位置
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<SysUser> administratorLimit(int userType) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM SysUser ");
		hql.append(" WHERE 1=1 ");
		hql.append(" AND userType="+userType);
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	  * administrator count 总数
	  * @return
	  */
	public Long findTotel(String tableName,int userType){
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT ");
		hql.append(" count(*) ");
		hql.append("FROM ");
		hql.append( tableName );
		hql.append(" WHERE 1=1 ");
		hql.append(" AND userType="+userType);
		return (Long) this.getHibernateTemplate().find(hql.toString()).listIterator().next();
	}
	
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
	@SuppressWarnings("unchecked")
	public List<SysUser> getSysUserById(int id){
		StringBuffer hql = new StringBuffer();
		hql.append("FROM ");
		hql.append(" SysUser ");
		hql.append(" WHERE ");
		hql.append(" id=");
		hql.append(id);
	    return this.getHibernateTemplate().find(hql.toString());	
	}
	/**
	 * 根据id查询用户
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	public SysUser findByUserId(String userId) {
		String hql="from SysUser where userId=?";
		List<SysUser> list=(List<SysUser>) this.getHibernateTemplate().find(hql, userId);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
/**
 * 根据property查询每一列的值value
 * @author 陆建宁
 * @param property
 * @param value
 * @return
 */
	public SysUser findPropertyByValue(String property, String value) {
		SysUser sysUser = null;
		@SuppressWarnings("unchecked")
		List<SysUser> list = this.getHibernateTemplate().find("from SysUser where "+property+" = '"+value+"'");
		if(null != list && 0<list.size()){
			sysUser = list.get(0);
		}
		return sysUser;

	
	}
}
