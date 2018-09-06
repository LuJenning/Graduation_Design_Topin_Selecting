package com.gdts.selecting.service;

import java.util.List;

import com.gdts.core.pagination.Result;
import com.gdts.selecting.entity.*;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;

/**
 * 志愿接口
 * @author 陆建宁
 * @date 2018 5-31
 */
public interface IIdealService {
	
	Result<Ideal> find(Ideal ideal,int page, int row);
	
	/**
	 * 
	 * @Description: 通过课题id查询课题
	 * @param @param id
	 * @param @return   
	 * @return Ideal 
	 * @throws
	 * @author liuchunf
	 * @date 2018年6月11日
	 */
	public Ideal queryIdealById(Integer id);
	
	/**
	 * 
	 * @Description: 添加志愿
	 * @param @param ideal   
	 * @return void  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月11日
	 */
	public Integer addIdeal(Ideal ideal);
	/**
	 * 通过学生id查看志愿信息(学生查看已选志愿信息)
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	public List<TeacherSeeSelecting> queryIdealByStuId(String userId);
	/**
	 * 教师查看志愿选择情况
	 * @author 秦松
	 * @param topicId
	 * @param page
	 * @param row
	 * @return
	 */
	public Result<TeacherSeeSelecting> IdealByTeacher(String topicId, int page, int row);
	
	/**
	 * 通过学生id和审核状态查看志愿信息（暂时未使用）
	 * @author 刘春福
	 * @param userId,isAccept
	 * @return
	 */
	public List<TeacherSeeSelecting> queryIdealByStuIdAndAccept(String userId, Integer isAccept);
	
	/**
	 * 
	 * @Description: 通知教师需要审核的志愿
	 * @param @param userId
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	public Result<TeacherSeeSelecting> queryIdealForTeacherInfo(String userId, int page, int row);
	
	/**
	 * 
	 * @Description: 学生报考志愿时，先判断是否是已选的志愿
	 * @param @return   
	 * @return boolean 
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	public List<Ideal> seacherIsSelectIdeal(Ideal ideal);
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》同意志愿
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public boolean agreeIdeal(Ideal ideal);
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》退档志愿
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public boolean ctrlIdeal(Ideal ideal);
	/**
	 * 
	 * @Description: 教师查看录取学生名单
	 * @param @param userId
	 * @param @param page
	 * @param @param row
	 * @param @return   
	 * @return Result<TeacherSeeSelecting>  
	 * @throws
	 * @author Tony
	 * @date 2018年6月18日
	 */
	Result<TeacherSeeSelecting> enrollList(String userId, int page, int row);
}
