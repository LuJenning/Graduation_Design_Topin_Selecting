package com.gdts.selecting.service;

import java.util.List;

import javax.mail.Flags.Flag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gdts.core.pagination.Result;
import com.gdts.selecting.dao.IdealDAO;
import com.gdts.selecting.entity.*;
import com.gdts.selecting.entity.other.TeacherSeeSelecting;
/**
 * 志愿实现类
 * @author 陆建宁
 * @date 2018 5-31
 */
@Service("idealService")
public class IdealServiceImpl implements IIdealService{
	@Autowired
	private IdealDAO idealDAO;
	@Override
	public Result<Ideal> find(Ideal ideal, int page, int row) {
		return idealDAO.find(ideal, page, row);
	}

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
	public Ideal queryIdealById(Integer id){
		List<Ideal> list = idealDAO.queryIdealById(id);
		return (null != list && 0<list.size())?list.get(0):null;
	};
	
	/**
	 * 
	 * @Description: 添加志愿
	 * @param @param ideal   
	 * @return void  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月11日
	 */
	public Integer addIdeal(Ideal ideal){
		int flag = 1;
		try {
			idealDAO.save(ideal);
		} catch (Exception e) {
			e.printStackTrace();
			flag = 0;
		}
		return flag;
	}
	/**
	 * 通过学生id查看志愿信息(学生查看已选志愿信息)
	 * @author 秦松
	 * @param userId
	 * @return
	 */
	@Override
	public List<TeacherSeeSelecting> queryIdealByStuId(String userId) {
		// TODO Auto-generated method stub
		return idealDAO.queryIdealByStuId(userId);
	}
	/**
	 * 教师查看志愿选择情况
	 * @author 秦松
	 * @param topicId
	 * @param page
	 * @param row
	 * @return
	 */
	@Override
	public Result<TeacherSeeSelecting> IdealByTeacher(String topicId, int page, int row) {
		// TODO Auto-generated method stub
		return idealDAO.queryIdealByTopicId(topicId,page,row);
	}

	/**
	 * 通过学生id和审核状态查看志愿信息（暂时未使用）
	 * @author 刘春福
	 * @param userId,isAccept
	 * @return
	 */
	@Override
	public List<TeacherSeeSelecting> queryIdealByStuIdAndAccept(String userId, Integer isAccept) {
		return idealDAO.queryIdealByStuIdAndAccept(userId, isAccept);
	}

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
	@Override
	public Result<TeacherSeeSelecting> queryIdealForTeacherInfo(String userId, int page, int row) {
		return idealDAO.queryIdealForTeacherInfo(userId, page, row);
	};
	
	/**
	 * 
	 * @Description: 学生报考志愿时，先判断是否是已选的志愿
	 * @param @return   
	 * @return List<Ideal>
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	@Override
	public List<Ideal> seacherIsSelectIdeal(Ideal ideal){
		List<Ideal> list = idealDAO.seacherIsSelectIdeal(ideal);
		return (null != list && 0<list.size())?list:null;
	};
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》同意志愿
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public boolean agreeIdeal(Ideal ideal){
		Integer flag = idealDAO.changeIdealForTeacher(ideal);
		return (null != flag && flag>0)?true:false;
	};
	
	/**
	 * 
	 * @Description: 教师审核学生志愿：---》退档志愿
	 * @param @return   
	 * @return boolean  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月17日
	 */
	public boolean ctrlIdeal(Ideal ideal){
		Integer flag = idealDAO.changeIdealForTeacher(ideal);
		return (null != flag && flag>0)?true:false;
	}
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
	@Override
	public Result<TeacherSeeSelecting> enrollList(String userId, int page, int row) {
		// TODO Auto-generated method stub
		return idealDAO.enrollList(userId,page,row);
	};
}
