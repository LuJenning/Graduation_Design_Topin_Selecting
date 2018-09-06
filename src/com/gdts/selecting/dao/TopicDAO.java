package com.gdts.selecting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.TopicInfo;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 
 * @author 陆建宁
 * @date 2018-5-23
 */
@Repository("topicDAO")
public class TopicDAO extends BaseDaoImpl<TopicInfo>{
/**
 * 
 * @author 陆建宁
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<TopicInfo> findAll() {
		return getHibernateTemplate().find("from TopicInfo where 1=1");
	}
/**
 * 模糊查询
 * @author 陆建宁
 * @param property
 * @param value
 * @return
 */
	public TopicInfo findPropertyByValue(String property, String value) {
		TopicInfo topic = null;
		@SuppressWarnings("unchecked")
		List<TopicInfo> list = this.getHibernateTemplate().find("from TopicInfo where "+property+" = '"+value+"'");
		if(list != null && 0<list.size()){
			list.get(0);
		}
		return topic;
	}
/**
 * 查询课题最大的课题编号
 * @author 陆建宁
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<TopicInfo> findMaxTopicId() {
		return this.getHibernateTemplate().find("select max(topicId) from TopicInfo");
	}
/**
 * 
 * @author 陆建宁
 * @param topicTitle
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<TopicInfo> findTopicByTitle(String topicTitle) {
		return (List<TopicInfo>) this.getHibernateTemplate().find("from TopicInfo where topicTitle='"+topicTitle+"'");
	}

/**
 * 根据id查询一条记录
 * @author 陆建宁
 * @param id
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<TopicInfo> findByPriId(Integer id) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM ");
		hql.append(" TopicInfo ");
		hql.append(" WHERE ");
		hql.append(" id=");
		hql.append(id);
	    return (List<TopicInfo>) this.getHibernateTemplate().find(hql.toString());
	}
/**
 * 分页
 * @author 陆建宁
 * @param topic
 * @param page
 * @param row
 * @return
 */
	@SuppressWarnings("unchecked")
	public Result<TopicInfo> find(TopicInfo topic, int page, int row) {
		String queryString="from TopicInfo where 1=1";
		if(null !=topic.getUserId() && !"".equals(topic.getUserId())){	
			//查询该教师所发布的课题
			queryString =queryString +"and userId like'%"+topic.getUserId()+"%'";
		}
		//查询标题。学生和导师查询都用如下查
		if(null !=topic.getTopicTitle() && !"".equals(topic.getTopicTitle())){
			queryString =queryString +"and topicTitle like'%"+topic.getTopicTitle()+"%'";
		}
		//用户为学生时执行如下，查看自己所属专业的课题
		if(null != topic.getInstituteId()&&!"".equals(topic.getInstituteId())){
			queryString =queryString +"and instituteId like'%"+topic.getInstituteId()+"%'";
		}
		int start=(page-1)*row;
		int limit=row;
		return (Result<TopicInfo>)super.find(queryString, null, null, start, limit);
	}
	
	/**
	 * 
	 * @Description: 查看课题信息
	 * @param @param otherEntity
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	@SuppressWarnings("unchecked")
	public Result<TeacherSeeSelecting> findForTeacherSeeSelecting(TopicInfo topic, int page, int row) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" s.userName, t.id, t.topicId, t.topicTitle, t.topicContent, t.topicRequest, ");
		hql.append(" t.topicSurplus, t.issueDate, t.userId, t.instituteId, i.instituteName, t.intNelen ) ");
		hql.append(" FROM TopicInfo t, SysUser s, InstituteInfo i");
		hql.append(" WHERE 1=1 AND t.userId=s.userId AND i.instituteId=t.instituteId ");
		//查询该教师所发布的课题
		if(null != topic.getUserId() && !"".equals(topic.getUserId()))hql.append(" AND t.userId LIKE'%"+topic.getUserId()+"%'");

		//查询标题。学生和导师查询都用如下查
		if(null !=topic.getTopicTitle() && !"".equals(topic.getTopicTitle())){
			hql.append("  AND ( t.topicTitle like'%"+topic.getTopicTitle()+"%'");
			hql.append("  		OR t.topicContent like'%"+topic.getTopicTitle()+"%'");
			hql.append("  		OR t.topicRequest like'%"+topic.getTopicTitle()+"%')");
		}
		//用户为学生时执行如下，查看自己所属专业的课题
		if(null != topic.getInstituteId() &&!"".equals(topic.getInstituteId()))hql.append(" AND t.instituteId like'%"+topic.getInstituteId()+"%'");
		int start=(page-1)*row;
		int limit=row;
		return (Result<TeacherSeeSelecting>)super.find(hql.toString(), null, null, start, limit);
	}
	
	/**
	 * 
	 * @Description: 学生选课题
	 * @param @param otherEntity
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherSeeSelecting> queryTeacherSeeSelectingForStudent(TopicInfo topic) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" s.userName, t.id, t.topicId, t.topicTitle, t.topicContent, t.topicRequest, ");
		hql.append(" t.topicSurplus, t.issueDate, t.userId, t.instituteId, i.instituteName, t.intNelen ) ");
		hql.append(" FROM TopicInfo t, SysUser s, InstituteInfo i");
		hql.append(" WHERE 1=1 AND t.userId=s.userId AND i.instituteId=t.instituteId AND t.id="+topic.getId());
		return this.getHibernateTemplate().find(hql.toString());
	}
	
	/**
	 * 
	 * @Description: saveTopicInfoByAjax通过ajax修改课题信息
	 * @param @param topic
	 * @param @return   
	 * @return Integer  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月8日
	 */
	public Integer saveTopicInfoByAjax(TopicInfo topic){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE TopicInfo SET ");
		hql.append(" topicTitle='"+topic.getTopicTitle()+"', ");
		hql.append(" topicRequest='"+topic.getTopicRequest()+"', ");
		hql.append(" topicContent='"+topic.getTopicContent()+"', ");
		hql.append(" topicSurplus="+topic.getTopicSurplus()+", ");
		hql.append(" instituteId="+topic.getInstituteId());
		hql.append(" WHERE id="+topic.getId());
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	/**
	 * 教师查看志愿情况（打开已发布课题信息）
	 * @author 秦松
	 * @param sysUser
	 * @param page
	 * @param row
	 * @return
	 */
	public Result<TopicInfo> openIdealTopicList(SysUser sysUser, int page, int row) {
		String hql="FROM TopicInfo WHERE userId='"+sysUser.getUserId()+"'";
	    int start=(page-1)*row;
		int limit=row;
		return (Result<TopicInfo>)super.find(hql, null, null, start, limit);
	}
	
	/**
	  * 
	  * @Description: updateTopicForStudentChecked学生选题后修改课题数量
	  * @param @param TopicInfo
	  * @param @return   
	  * @return Integer  
	  * @throws
	  * @author liuchunfu
	  * @date 2018年6月9日
	  */
	public Integer updateTopicForStudentChecked(TopicInfo topic){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE TopicInfo SET ");
		hql.append(" intNelen="+topic.getIntNelen());
		hql.append(" WHERE id="+topic.getId());
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
}


