package com.gdts.selecting.entity;

import java.util.Date;

/**
 * 
 * ClassName: LoginInfoUser 
 * @Description: 用户登陆日志实体
 * @author liuchunfu
 * @date 2018年5月30日
 */
public class LoginInfoUser {
	
	private Integer id;
	private String userId;
	private Integer userType;
	private Date loginDate;
	private String weekDay;
	
	public LoginInfoUser() {
		super();
	}

	public LoginInfoUser(Integer id, String userId, Integer userType, Date loginDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.userType = userType;
		this.loginDate = loginDate;
	}

	@Override
	public String toString() {
		return "LoginInfoUser [id=" + id + ", userId=" + userId + ", userType=" + userType + ", loginDate=" + loginDate
				+ ", weekDay=" + weekDay + "]";
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getUserType() {
		return userType;
	}
	
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
}
