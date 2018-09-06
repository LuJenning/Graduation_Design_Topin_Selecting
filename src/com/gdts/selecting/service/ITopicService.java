package com.gdts.selecting.service;

import java.util.List;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.TopicInfo;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 
 * @author 陆建宁
 * @date 2018-5-23
 */
public interface ITopicService {
	/**
	 * 分页
	 * @author 陆建宁
	 * @param topic
	 * @param page
	 * @param row
	 * @return
	 */
	Result<TopicInfo> find(TopicInfo topic,int page, int row);
	
	/**
	 * 教师发布课题
	 * @author 陆建宁
	 * @param topic
	 */
	void addTopic(TopicInfo topic);//发布课题
	
	/**
	 * 教师删除课题
	 * @author 陆建宁
	 * @param topic
	 */
	void deleteTopic(TopicInfo topic);
	
	/**
	 * 教师编辑课题
	 * @author 陆建宁
	 * @param topic
	 */
	void updateTopic(TopicInfo topic);
	
	/**
	 * 查询所有课题
	 * @author 陆建宁
	 * @return
	 */
	List<TopicInfo> findAll();
	
	/**
	 * 查询课题的最大的课题编号
	 * @author 陆建宁
	 * @return
	 */
	List<TopicInfo> findMaxTopicId();
	
	/**
	 * 根据主键id删除一条记录
	 * @author 陆建宁
	 * @param id
	 * @return
	 */
	public TopicInfo findByPriId(int id);

	public List<TopicInfo> findByTopicTitle(String topicTitle);
	
	public TopicInfo findPropertyByValue(String property ,String value);
	
	/**
	 * 
	 * @Description: 查看课题信息
	 * @param @param topic
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	public Result<TeacherSeeSelecting> findForTeacherSeeSelecting(TopicInfo topic, int page, int row);
	
	/**
	 * 
	 * @Description: saveTopicInfoByAjax通过ajax修改课题信息
	 * @param @param topic
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月8日
	 */
	public boolean saveTopicInfoByAjax(TopicInfo topic);
	
	/**
	 * 
	 * @Description: 学生选课题
	 * @param @param topic
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return TeacherSeeSelecting  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	public TeacherSeeSelecting queryTeacherSeeSelectingForStudent(TopicInfo topic);
	/**
	 * 教师查看志愿情况（打开已发布课题信息）
	 * @author 秦松
	 * @param sysUser
	 * @param page
	 * @param row
	 * @return
	 */
	public Result<TopicInfo> openIdealTopicList(SysUser sysUser, int page, int row);
	
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
	public Integer updateTopicForStudentChecked(TopicInfo topic);
}
