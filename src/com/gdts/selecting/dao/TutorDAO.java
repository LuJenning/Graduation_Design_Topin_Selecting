package com.gdts.selecting.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.gdts.core.dao.impl.BaseDaoImpl;
import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * DAO层
 * @author 陆建宁
 * @date 2018 5-30
 */
@Repository("tutorDAO")
public class TutorDAO extends BaseDaoImpl<SysUser>{
/**
 * 查找所有教师
 * @author 陆建宁
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<SysUser> findAllTutor() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from SysUser where 1=1");
	}

/**
 * 根据名字查寻教师
 * @author 陆建宁
 * @param sysUser
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<SysUser> findTutorByName(SysUser sysUser) {
		
		return getHibernateTemplate().find("from SysUser where userName='"+sysUser.getUserName()+"'");
	}

/**
 * 根据id查找一条数据
 * @author 陆建宁
 * @param id
 * @return
 */
	@SuppressWarnings("unchecked")
	public List<SysUser> findByPriId(int id) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM ");
		hql.append(" SysUser ");
		hql.append(" WHERE ");
		hql.append(" id=");
		hql.append(id);
	    return (List<SysUser>) this.getHibernateTemplate().find(hql.toString());
	}

/**
 * 分页
 * @author 陆建宁
 * @param sysUser
 * @param page
 * @param row
 * @return
 */
	@SuppressWarnings("unchecked")
	public Result<SysUser> find(SysUser sysUser, int page, int row) {
		String queryString="from SysUser  where 1=1";
		
	if(null != sysUser.getUserType() && !"".equals(sysUser.getUserType())){
			queryString =queryString +"and userType like'%"+sysUser.getUserType()+"%'";
		}
	if(null !=sysUser.getUserName() && !"".equals(sysUser.getUserName())){
	queryString =queryString +"and userName like'%"+sysUser.getUserName()+"%'";
	}
		int start=(page-1)*row;
		int limit=row;
		return (Result<SysUser>)super.find(queryString, null, null, start, limit);
	}
	
	/**
	 * 
	 * @Description: 查看教师信息
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
	public Result<TeacherSeeSelecting> queryTeacherAllForAdmin(String likeString, SysUser sysUser, int page, int row) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" s.id, s.userId, s.userName , s.userPassword, s.userSex, s.userType, s.userPhone, ");
		hql.append(" s.instituteId, s.userBirthday, s.teacherTitle, s.userNative, i.instituteName, s.studentScore ) ");
		hql.append(" FROM SysUser s, InstituteInfo i ");
		hql.append(" WHERE 1=1 AND i.instituteId=s.instituteId AND s.userType="+sysUser.getUserType());
		//查询教师
		System.out.println("likeString:"+likeString);
		if(null != likeString && !likeString.isEmpty()){
			hql.append(" AND (s.userId LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userName LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userPassword LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userPhone LIKE'%"+likeString+"%' ");
			hql.append("       OR s.teacherTitle LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userNative LIKE'%"+likeString+"%') ");
		}
		int start=(page-1)*row;
		int limit=row;
		return super.find(hql.toString(), null, null, start, limit);
	}
	
	/**
	 * 
	 * @Description: 查看学生信息
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
	public Result<TeacherSeeSelecting> queryStudentAllForAdmin(String likeString, SysUser sysUser, int page, int row) {
		StringBuffer hql = new StringBuffer();
		hql.append(" SELECT new com.gdts.selecting.entity.other.TeacherSeeSelecting ( ");
		hql.append(" s.id, s.userId, s.userName , s.userPassword, s.userSex, s.userType, s.userPhone, ");
		hql.append(" s.instituteId, s.userBirthday, s.teacherTitle, s.userNative, i.instituteName, s.studentScore, ");
		hql.append(" c.classId, c.className ) ");
		hql.append(" FROM SysUser s, InstituteInfo i, ClassInfo c ");
		hql.append(" WHERE 1=1 AND i.instituteId=s.instituteId AND c.classId=s.classId AND s.userType="+sysUser.getUserType());
		//查询教师
		System.out.println("likeString:"+likeString);
		if(null != likeString && !likeString.isEmpty()){
			hql.append(" AND (s.userId LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userName LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userPassword LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userPhone LIKE'%"+likeString+"%' ");
			hql.append("       OR s.teacherTitle LIKE'%"+likeString+"%' ");
			hql.append("       OR s.userNative LIKE'%"+likeString+"%') ");
		}
		int start=(page-1)*row;
		int limit=row;
		return super.find(hql.toString(), null, null, start, limit);
	}
	
	/**
	  * 
	  * @Description: saveTeacherByAjax保存修改后的教师/学生
	  * @param @param sysUser
	  * @param @return   
	  * @return boolean  
	  * @throws
	  * @author liuchunfu
	  * @date 2018年6月9日
	  */
	public Integer saveTeacherByAjax(SysUser sysUser){
		StringBuffer hql = new StringBuffer();
		hql.append(" UPDATE SysUser SET ");
		hql.append(" userId='"+sysUser.getUserId()+"', ");
		if(null != sysUser.getUserName() && !"".equals(sysUser.getUserName())) hql.append(" userName='"+sysUser.getUserName()+"', ");
		if(null != sysUser.getUserPassword() && !"".equals(sysUser.getUserPassword())) hql.append(" userPassword='"+sysUser.getUserPassword()+"', ");
		if(null != sysUser.getUserSex() && !"".equals(sysUser.getUserSex())) hql.append(" userSex='"+sysUser.getUserSex()+"', ");
		if(null != sysUser.getUserBirthday() && !"".equals(sysUser.getUserBirthday())) hql.append(" userBirthday='"+sysUser.getUserBirthday()+"', ");
		if(null != sysUser.getUserPhone() && !"".equals(sysUser.getUserPhone())) hql.append(" userPhone="+sysUser.getUserPhone()+", ");
		if(null != sysUser.getTeacherTitle() && !"".equals(sysUser.getTeacherTitle())) hql.append(" teacherTitle='"+sysUser.getTeacherTitle()+"', ");
		if(null != sysUser.getUserNative() && !"".equals(sysUser.getUserNative())) hql.append(" userNative='"+sysUser.getUserNative()+"', ");
		if(null != sysUser.getInstituteId() && !"".equals(sysUser.getInstituteId())) hql.append(" instituteId="+sysUser.getInstituteId());
		if(null != sysUser.getStudentScore() && !"".equals(sysUser.getStudentScore())) hql.append(", studentScore="+sysUser.getStudentScore());
		if(null != sysUser.getClassId() && !"".equals(sysUser.getClassId())) hql.append(", classId="+sysUser.getClassId());
		hql.append(" WHERE id="+sysUser.getId());
		return this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	
}
