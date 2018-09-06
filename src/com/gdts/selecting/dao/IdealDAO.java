package com.gdts.selecting.dao;

import java.util.List;

import org.hibernate.hql.ast.HqlASTFactory;
import org.springframework.stereotype.Repository;

import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.*;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 数据交互dao层
 * @author Administrator
 * @date 2018 5-31
 */
@Repository("idealDAO")
public class IdealDAO extends BaseDaoImpl<Ideal>{

	@SuppressWarnings("unchecked")
	public Result<Ideal> find(Ideal ideal, int page, int row) {
		String queryString="from TopicInfo  where 1=1";
		if(null != ideal.getInstituteId()&&!"".equals(ideal.getInstituteId())){
			queryString =queryString +"and instituteId like'%"+ideal.getInstituteId()+"%'";
		}
		int start=(page-1)*row;
		int limit=row;
		return (Result<Ideal>)super.find(queryString, null, null, start, limit);
	}

	/**
	 * 
	 * @Description: 通过课题id查询课题
	 * @param @param id
	 * @param @return   
	 * @return List<Ideal>  
	 * @throws
	 * @author liuchunf
	 * @date 2018年6月11日
	 */
	@SuppressWarnings("unchecked")
	public List<Ideal> queryIdealById(Integer id){
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM Ideal ");
		hql.append(" WHERE 1=1 AND id="+id);
		return this.getHibernateTemplate().find(hql.toString());
	};
	
	/**
	 * 
	 * @Description: 添加志愿（作废）
	 * @param @param ideal
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月11日
	 */
	public Integer addIdeal(Ideal ideal){
		StringBuffer hql = new StringBuffer();
		hql.append(" INSERT INTO Ideal(");
		hql.append(" 	 idealType,");
		hql.append(" 	 topicId,");
		hql.append("     userId,");
		hql.append("     selectDate,");
		hql.append("     instituteId,");
		hql.append("     isAccept) ");
		hql.append(" values('"+ideal.getIdealType()+"',");
		hql.append("		'"+ideal.getTopicId()+"',");
		hql.append("		'"+ideal.getUserId()+"',");
		hql.append("		'"+ideal.getSelectDate()+"',");
		hql.append("		"+ideal.getInstituteId()+",");
		hql.append("		"+ideal.getIsAccept()+")");
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	/**
	 * 通过学生id查看志愿信息(学生查看已选志愿信息)
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherSeeSelecting> queryIdealByStuId(String userId) {
		StringBuffer hql= new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" i.idealId,i.idealType,i.selectDate,i.isAccept,s.userId,s.userName,t.topicId,t.topicTitle)");
		hql.append(" FROM SysUser s,Ideal i,TopicInfo t");
		hql.append(" WHERE i.userId='"+userId+"' AND s.userId=t.userId AND i.topicId=t.topicId ");
		
		
		//String hql="From Ideal where userId='"+userId+"'";
		
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * 通过学生id和审核状态查看志愿信息（暂时未使用）
	 * @author 刘春福
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherSeeSelecting> queryIdealByStuIdAndAccept(String userId, Integer isAccept) {
		StringBuffer hql= new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" i.idealId,i.idealType,i.selectDate,i.isAccept,s.userId,s.userName,t.topicId,t.topicTitle)");
		hql.append(" FROM SysUser s,Ideal i,TopicInfo t");
		hql.append(" WHERE i.userId='"+userId+"' AND s.userId=i.userId AND i.topicId=t.topicId ");
		hql.append(" AND i.isAccept="+isAccept);
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * 通知教师待审核的志愿
	 * @param userId
	 * @param page
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Result<TeacherSeeSelecting> queryIdealForTeacherInfo(String userId, int page, int row) {
		StringBuffer hql=new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" i.id, i.idealType, i.selectDate, i.isAccept, i.topicId, i.userId, i.instituteId, ");
		hql.append(" t.topicTitle, t.id, s.userName) ");
		hql.append(" FROM SysUser s,Ideal i, TopicInfo t ");
		hql.append(" WHERE 1=1 AND s.userId=i.userId AND t.topicId=i.topicId AND i.isAccept=3 AND t.userId='" +userId+"' ");
		int start=(page-1)*row;
		int limit=row;
		return (Result<TeacherSeeSelecting>)super.find(hql.toString(), null, null, start, limit);
	}
	
	/**
	 * 通过课程号分页查询志愿列表
	 * @param topicId
	 * @param page
	 * @param row
	 * @return
	 */
	public Result<TeacherSeeSelecting> queryIdealByTopicId(String topicId, int page, int row) {
		StringBuffer hql=new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" i.id,s.userId,s.userName,i.selectDate,i.idealType,i.isAccept, i.topicId)");
		hql.append(" FROM SysUser s,Ideal i");
		hql.append(" WHERE i.topicId='"+topicId+"' AND i.userId=s.userId");
		int start=(page-1)*row;
		int limit=row;
		return (Result<TeacherSeeSelecting>)super.find(hql.toString(), null, null, start, limit);
	}
	
	/**
	 * 
	 * @Description: 学生报考志愿时，先判断是否是已选的志愿
	 * @param @return   
	 * @return List<Ideal>
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	@SuppressWarnings("unchecked")
	public List<Ideal> seacherIsSelectIdeal(Ideal ideal){
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM Ideal ");
		hql.append(" WHERE 1=1 ");
		hql.append(" AND topicId='"+ideal.getTopicId()+"' ");
		hql.append(" AND userId='"+ideal.getUserId()+"' ");
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * 
	 * @Description: 教师审核学生志愿
	 * @param @param ideal
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public Integer changeIdealForTeacher(Ideal ideal){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE Ideal SET ");
		hql.append(" isAccept="+ideal.getIsAccept());
		hql.append(" WHERE id="+ideal.getId());
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	/**
	 * 
	 * @Description: 教师查看录取名单
	 * @param @param userId
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author Tony
	 * @date 2018年6月18日
	 */
	public Result<TeacherSeeSelecting> enrollList(String userId, int page, int row) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting (");
		hql.append(" s.userId,s.userName,t.topicId,t.topicTitle,i.id,");
		hql.append(" i.idealType,i.isAccept,d.instituteId,d.instituteName)");
		hql.append(" FROM ");
		hql.append(" InstituteInfo d,SysUser s,TopicInfo t,Ideal i");
		hql.append(" WHERE t.userId='"+userId+"' AND i.isAccept=1 AND d.instituteId=s.instituteId AND s.userId=i.userId AND t.topicId=i.topicId");
		
		int start=(page-1)*row;
		int limit=row;
		return (Result<TeacherSeeSelecting>)super.find(hql.toString(), null, null, start, limit);
	}
}
