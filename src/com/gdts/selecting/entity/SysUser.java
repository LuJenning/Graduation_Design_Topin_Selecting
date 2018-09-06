package com.gdts.selecting.entity;


import java.util.Date;

public class SysUser {
	private Integer id;
	private String userId;
	private String userName;
	private String userPassword;
	private Integer userSex;
	private Date userBirthday;
	private Integer userType;
	private String userPhone;
	private Integer instituteId;
	private Integer classId;
	private String teacherTitle;
	private String userNative;
	private Double studentScore; 
	
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userSex=" + userSex + ", userBirthday=" + userBirthday + ", userType=" + userType + ", userPhone="
				+ userPhone + ", instituteId=" + instituteId + ", classId=" + classId + ", teacherTitle=" + teacherTitle
				+ ", userNative=" + userNative + ", studentScore=" + studentScore + "]";
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
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public Integer getUserSex() {
		return userSex;
	}
	
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	
	public Integer getUserType() {
		return userType;
	}
	
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public Integer getInstituteId() {
		return instituteId;
	}
	
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(String teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public String getUserNative() {
		return userNative;
	}

	public void setUserNative(String userNative) {
		this.userNative = userNative;
	}

	public Double getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(Double studentScore) {
		this.studentScore = studentScore;
	}
	
}
