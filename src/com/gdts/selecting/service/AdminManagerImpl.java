package com.gdts.selecting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdts.selecting.dao.AdminManagerDao;
import com.gdts.selecting.entity.LoginInfoUser;
import com.gdts.selecting.entity.SysUser;
import com.gdts.selecting.util.LoginMSGOption;
import com.gdts.selecting.util.LoginUserService;
import com.gdts.selecting.util.Series;
import com.gdts.selecting.util.SeriesData;
import com.gdts.selecting.util.UserAnalysis;
import com.gdts.selecting.util.UserAnalysisSeries;
import com.gdts.selecting.util.UserAnalysisSeriesItem;

/**
 * 
 * ClassName: AdministorManagerImpl 
 * @Description: 管理员管理统计service
 * @author liuchunfu
 * @date 2018年5月30日
 */
@Service("adminManagerImpl")
public class AdminManagerImpl implements IAdminManagerService<Object>{

	@Autowired
	private AdminManagerDao adminManagerDao;
	@SuppressWarnings("rawtypes")
	private LoginMSGOption loginMSGOption;
	@SuppressWarnings("unused")
	private StringBuffer buffer;
	
	/**
	 * loginMSG
	 * @Description: 统计用户活跃量
	 * @param @return   
	 * @return LoginMSGOption<T>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public LoginMSGOption<Object> loginMSG() {
		loginMSGOption = new LoginMSGOption<LoginInfoUser>();
		buffer = new StringBuffer();
		List<LoginInfoUser> adminList = adminManagerDao.loginMSG(new Integer(1));
		List<LoginInfoUser> teacherList = adminManagerDao.loginMSG(new Integer(2));
		List<LoginInfoUser> studentList = adminManagerDao.loginMSG(new Integer(3));
		LoginUserService login = new LoginUserService();
		login.setMap("admin", adminList);
		login.setMap("teacher", teacherList);
		login.setMap("student", studentList);
		login.setDefaultLabel();
		loginMSGOption.setDefault();
		Series adminSeries = new Series("管理员","bar","总量",login.getLabel(),login.getSizeByWeekDay("admin"));
		Series teacherSeries = new Series("教师","bar","总量",login.getLabel(),login.getSizeByWeekDay("teacher"));
		Series studentSeries = new Series("学生","bar","总量",login.getLabel(),login.getSizeByWeekDay("student"));
		loginMSGOption.setSeries(adminSeries);
		loginMSGOption.setSeries(teacherSeries);
		loginMSGOption.setSeries(studentSeries);
		return loginMSGOption;
	}

	/**
	 * countSysUserAll
	 * @Description: 统计不同类型用户数
	 * @param @return   
	 * @return UserAnalysis<T>  
	 * @throws
	 * @author liuchunfu
	 * @date 2018年5月31日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UserAnalysis<Object> countSysUserAll() {
		loginMSGOption = new LoginMSGOption<LoginInfoUser>();
		List<SysUser> adminList = adminManagerDao.findUsers(new Integer(1));
		List<SysUser> teacherList = adminManagerDao.findUsers(new Integer(2));
		List<SysUser> studentList = adminManagerDao.findUsers(new Integer(3));
		SeriesData adminMap = new SeriesData();
		SeriesData teacherMap = new SeriesData();
		SeriesData studentMap = new SeriesData();
		adminMap.setValue(adminList.size());
		adminMap.setName("管理员");
		teacherMap.setValue(teacherList.size());
		teacherMap.setName("教师");
		studentMap.setValue(studentList.size());
		studentMap.setName("学生");
		List<SeriesData> list = new ArrayList<>();
		list.add(adminMap);
		list.add(teacherMap);
		list.add(studentMap);
		UserAnalysisSeries userAnalysisSeries = new UserAnalysisSeries();
		userAnalysisSeries.setDefault();
		UserAnalysisSeriesItem userAnalysisSeriesItem = new UserAnalysisSeriesItem();
		userAnalysisSeriesItem.setDefault();
		userAnalysisSeriesItem.setData(list);
		userAnalysisSeriesItem.setItemStyle(userAnalysisSeries.getItemStyle());
		UserAnalysis userAnalysis = new UserAnalysis();
		userAnalysis.setDefault();
		userAnalysis.setSeries(userAnalysisSeriesItem);
		return userAnalysis;
	}

}
