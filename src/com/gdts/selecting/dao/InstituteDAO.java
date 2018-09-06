package com.gdts.selecting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.InstituteInfo;


@Repository("instetuteDAO")
public class InstituteDAO extends BaseDaoImpl<InstituteInfo> {
	/**
	 * 查询学院，专业表的父id来查询所有的学院信息，因为学院pid=0
	 * @author 秦松
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InstituteInfo> findInstituteList() {
		String hql="from InstituteInfo where institutePid=0";
		return this.getHibernateTemplate().find(hql);
	}
	//
	/**
	 * 通过学院id(也就是专业信息的Pid)查询专业列表
	 * @param instituteId
	 * @author 秦松
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<InstituteInfo> findMajorListByPid(Integer instituteId) {
		String hql="from InstituteInfo where institutePid='"+instituteId+"'";
		return this.getHibernateTemplate().find(hql);
	}
	/**
	 * 查询学院信息
	 * @author 秦松
	 * @param instituteId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public InstituteInfo findByInstituteId(Integer instituteId) {
		String hql = "from InstituteInfo where instituteId='"+instituteId+"'";
		List<InstituteInfo> instituteInfoList =  getHibernateTemplate().find(hql);
		if(null != instituteInfoList && instituteInfoList.size() > 0){
		    return instituteInfoList.get(0);
		}else {
			return null;
		}
	}
	/**
	 * 分页查询学院列表
	 * @author 秦松
	 * @param instituteInfo
	 * @param page
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Result<InstituteInfo> institutePageList(InstituteInfo instituteInfo, int page, int size) {
		String queryString="from InstituteInfo where 1=1 ";
		/*if(null !=instituteInfo.getInstitutePid() && !"".equals(instituteInfo.getInstitutePid())){
			queryString =queryString +" and institutePid="+instituteInfo.getInstitutePid();
		}*/
		if(null !=instituteInfo.getInstituteName() && !"".equals(instituteInfo.getInstituteName())){
			queryString =queryString +" and (instituteName like'%"+instituteInfo.getInstituteName()+"%' OR instituteId LIKE'%"
							+instituteInfo.getInstituteName()+"')";
		}
		int start=(page-1)*size;
		int limit=size;
		return (Result<InstituteInfo>)super.find(queryString, null, null, start, limit);
	}
	/**
	 * 模糊查询
	 * @author 陆建宁
	 * @param property
	 * @param value
	 * @return
	 */
	public InstituteInfo findPropertyByValue(String property, String value) {
		InstituteInfo instituteInfo = null;
		@SuppressWarnings("unchecked")
		List<InstituteInfo> list = this.getHibernateTemplate().find("from InstituteInfo where "+property+" = '"+value+"'");
		if(list != null && 0<list.size()){
			instituteInfo = list.get(0);
		}
		return instituteInfo;
	}
	
	/**
	 * 
	 * @Description: 更新学院或专业
	 * @param @param instituteInfo
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public int doUpdate(InstituteInfo instituteInfo){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE InstituteInfo ");
		hql.append(" SET instituteName='"+instituteInfo.getInstituteName()+"', ");
		hql.append(" institutePid="+instituteInfo.getInstitutePid());
		hql.append(" WHERE instituteId="+instituteInfo.getInstituteId());
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	
	/**
	 * 
	 * @Description: 通过id删除学院或班级
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月22日
	 */
	public int doDelete(InstituteInfo instituteInfo){
		/*StringBuffer hql = new StringBuffer();
		hql.append(" DALETE FROM InstituteInfo WHERE instituteId="+instituteInfo.getInstituteId());*/
		
		//return this.getHibernateTemplate().bulkUpdate(hql.toString());
		try {
			this.getHibernateTemplate().delete(instituteInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}

