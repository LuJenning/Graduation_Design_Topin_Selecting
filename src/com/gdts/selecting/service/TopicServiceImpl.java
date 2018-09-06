package com.gdts.selecting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.dao.TopicDAO;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.entity.TopicInfo;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * @date 2018-5-23
 * @author 陆建宁
 *
 */
@Service("topicService")
public class TopicServiceImpl implements ITopicService {
	@Autowired
	private TopicDAO topicDAO;

/**
 * 发布课题
 * @author 陆建宁
 */
	@Cacheable(value="topicCache", key="#topic.topicId + 'addTopic'")
	public void addTopic(TopicInfo topic) {
		topicDAO.save(topic);
		
	}

/**
* 删除
* @author 陆建宁
*/
	@CacheEvict(value="topicCache", key="#topic.topicId + 'addTopic'")
	public void deleteTopic(TopicInfo topic) {
		topicDAO.remove(topic);
	}
	
/**
* 更新
* @author 陆建宁
*/
	@Override
	public void updateTopic(TopicInfo topic) {
		topicDAO.update(topic);
	}

	@Override
	public List<TopicInfo> findAll() {
		return topicDAO.findAll();
	}


/**
* 查询最大课题编号
* @author 陆建宁
*/
	@Override
	public List<TopicInfo> findMaxTopicId() {
		return topicDAO.findMaxTopicId();
	}


/**
* 根据id删除一条记录
* @author 陆建宁
*/
	@Override
	public TopicInfo findByPriId(int id) {
		List<TopicInfo> List = (List<TopicInfo>) topicDAO.findByPriId(id);
		return (null != List && 0<List.size())?List.get(0):null;
	}

	/**
	* 分页
	* @author 陆建宁
	*/
	@Override
	public Result<TopicInfo> find(TopicInfo topic, int page, int row) {
		return topicDAO.find(topic,  page,  row);
	}

	@Override
	public List<TopicInfo> findByTopicTitle(String topicTitle) {
		return (List<TopicInfo>) topicDAO.findTopicByTitle(topicTitle);
	}
	
	@Override
	public TopicInfo findPropertyByValue(String property, String value) {
		return topicDAO.findPropertyByValue(property, value);
	}

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
	@Override
	public Result<TeacherSeeSelecting> findForTeacherSeeSelecting(TopicInfo topic, int page, int row) {
		return topicDAO.findForTeacherSeeSelecting(topic, page, row);
	}
	
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
	@Override
	public TeacherSeeSelecting queryTeacherSeeSelectingForStudent(TopicInfo topic) {
		List<TeacherSeeSelecting> list = topicDAO.queryTeacherSeeSelectingForStudent(topic);
		return (null != list && 0<list.size())?list.get(0):null;
	}

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
	@Override
	public boolean saveTopicInfoByAjax(TopicInfo topic) {
		Integer num = topicDAO.saveTopicInfoByAjax(topic);
		System.out.println("num:"+num);
		return (null != num && 0<num)?true:false;
	}
	/**
	 * 教师查看志愿情况（打开已发布课题信息）
	 * @author 秦松
	 * @param sysUser
	 * @param page
	 * @param row
	 * @return
	 */
	@Override
	public Result<TopicInfo> openIdealTopicList(SysUser sysUser, int page, int row) {
		// TODO Auto-generated method stub
		return topicDAO.openIdealTopicList(sysUser,page,row);
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
		return topicDAO.updateTopicForStudentChecked(topic);
	}
}
