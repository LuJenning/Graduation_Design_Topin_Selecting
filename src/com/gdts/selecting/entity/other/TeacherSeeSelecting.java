package com.gdts.selecting.entity.other;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.ParseException;

/**
 * 
 * ClassName: TeacherSeeSelecting 
 * @Description: TODO
 * @author Tony
 * @date 2018年6月2日
 */
public class TeacherSeeSelecting {
	//用户表信息
	private Integer sysUserId;
	private String userPassword;
	private Integer userSex;
	private Date userBirthday;
	private Integer userType;
	private String userPhone;
	private Integer classId;
	private String teacherTitle;
	private String userNative;
	private Double studentScore;
	//课题表信息
	private Integer topicInfoId;
	private String topicTitle;//课题标题
	private String topicContent;//课题内容
	private String topicRequest;// 课题要求
	private Integer topicSurplus;// 课题余量
	private Timestamp issueDate;// 课题发布时间
	private Integer intNelen;
	//志愿表信息
	private Integer tableId;
	private String idealId;
	private String idealType;//志愿类型。1-第一志愿。2-第二志愿
	private Timestamp selectDate;//填报时间
	private Integer isAccept;//是否录取。0-未录取。1-录取。2-退档
	//学院表信息
	private String instituteName;//学院，专业名称
	private Integer institutePid;//父id、如果institute_id是专业则该字段为该专业所在院系的id
	
	//班级表
	private String className;
	//公用属性
	private String userId;
	private String userName;
	private Integer instituteId;//学院，专业编号
	private String topicId;
	
	public TeacherSeeSelecting() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @Description: 通知教师待审核的志愿
	 * @param    
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月16日
	 */
	public TeacherSeeSelecting(Integer tableId, String idealType, Object selectDate, Integer isAccept
			, String topicId, String userId, Integer instituteId, String topicTitle, Integer topicInfoId
			, String userName){
		this.tableId = tableId;
		this.idealType = idealType;
		try {
			this.selectDate = stringToTimestamp(selectDate.toString());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		this.isAccept = isAccept;
		this.topicId = topicId;
		this.userId = userId;
		this.instituteId = instituteId;
		this.topicTitle = topicTitle;
		this.topicInfoId = topicInfoId;
		this.userName = userName;
	}
	
	/**
	 * 
	 * @Description: 教师信息列表
	 * @param @param sysUserId
	 * @param @param userId
	 * @param @param userName
	 * @param @param userPassword
	 * @param @param userSex
	 * @param @param userType
	 * @param @param userPhone
	 * @param @param instituteId
	 * @param @param userBirthday
	 * @param @param teacherTitle
	 * @param @param userNative
	 * @param @param instituteName   
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月9日
	 */
	public TeacherSeeSelecting(Integer sysUserId, String userId, String userName, String userPassword,
			Integer userSex, Integer userType, String userPhone, Integer instituteId, Date userBirthday,
			String teacherTitle, String userNative, String instituteName, Double studentScore){
		this.sysUserId = sysUserId;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userSex = userSex;
		this.userType = userType;
		this.userPhone = userPhone;
		this.instituteId = instituteId;
		this.userBirthday = userBirthday;
		this.teacherTitle = teacherTitle;
		this.userNative = userNative;
		this.instituteName = instituteName;
		this.studentScore = studentScore;
	}
	
	/**
	 * 
	 * @Description: 学生信息列表
	 * @param @param sysUserId
	 * @param @param userId
	 * @param @param userName
	 * @param @param userPassword
	 * @param @param userSex
	 * @param @param userType
	 * @param @param userPhone
	 * @param @param instituteId
	 * @param @param userBirthday
	 * @param @param teacherTitle
	 * @param @param userNative
	 * @param @param instituteName   
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月9日
	 */
	public TeacherSeeSelecting(Integer sysUserId, String userId, String userName, String userPassword,
			Integer userSex, Integer userType, String userPhone, Integer instituteId, Date userBirthday,
			String teacherTitle, String userNative, String instituteName, Double studentScore,
			Integer classId, String className){
		this.sysUserId = sysUserId;
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userSex = userSex;
		this.userType = userType;
		this.userPhone = userPhone;
		this.instituteId = instituteId;
		this.userBirthday = userBirthday;
		this.teacherTitle = teacherTitle;
		this.userNative = userNative;
		this.instituteName = instituteName;
		this.studentScore = studentScore;
		this.classId = classId;
		this.className = className;
	}
	
	/**
	 * 
	 * @Description: 查看课题信息
	 * @param @param userName
	 * @param @param topicInfoId
	 * @param @param topicTitle
	 * @param @param topicContent
	 * @param @param topicRequest
	 * @param @param topicSurplus
	 * @param @param issueDate
	 * @param @param userId
	 * @param @param instituteId   
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	public TeacherSeeSelecting(String userName, Integer topicInfoId, String topicId,
			String topicTitle, String topicContent, String topicRequest, 
			Integer topicSurplus,Object issueDate, String userId, Integer instituteId, String instituteName){
		this.userName = userName;
		this.topicInfoId = topicInfoId;
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicContent = topicContent;
		this.topicRequest = topicRequest;
		this.topicSurplus = topicSurplus;
		try {
			this.issueDate = stringToTimestamp(issueDate.toString());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		this.userId = userId;
		this.instituteId = instituteId;
		this.instituteName = instituteName;
	}
	
	/**
	 * 
	 * @Description: 学生查看课题信息
	 * @param @param userName
	 * @param @param topicInfoId
	 * @param @param topicTitle
	 * @param @param topicContent
	 * @param @param topicRequest
	 * @param @param topicSurplus
	 * @param @param issueDate
	 * @param @param userId
	 * @param @param instituteId   
	 * @throws
	 * @author liuchunfu
	 * @date 2018年6月2日
	 */
	public TeacherSeeSelecting(String userName, Integer topicInfoId, String topicId,
			String topicTitle, String topicContent, String topicRequest, 
			Integer topicSurplus,Object issueDate, String userId, Integer instituteId, String instituteName, Integer intNelen){
		this.userName = userName;
		this.topicInfoId = topicInfoId;
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicContent = topicContent;
		this.topicRequest = topicRequest;
		this.topicSurplus = topicSurplus;
		try {
			this.issueDate = stringToTimestamp(issueDate.toString());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		this.userId = userId;
		this.instituteId = instituteId;
		this.instituteName = instituteName;
		this.intNelen = intNelen;
	}
	/**
	 * 学生查看已选志愿情况
	 * @author 秦松
	 * @param idealId
	 * @param idealType
	 * @param selectDate
	 * @param isAccept
	 * @param userId
	 * @param userName
	 * @param topicId
	 * @param topicTitle
	 */
	public TeacherSeeSelecting(String idealId,String idealType,Object selectDate,
			Integer isAccept,String userId,String userName,String topicId,String topicTitle){
		this.idealId=idealId;
		this.idealType=idealType;
		try {
			this.selectDate = stringToTimestamp(selectDate.toString());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		this.isAccept=isAccept;
		this.userId=userId;
		this.userName=userName;
		this.topicId=topicId;
		this.topicTitle=topicTitle;
	}
	/**
	 * 教师查看学生选题情况
	 * @param tableId
	 * @param userId
	 * @param userName
	 * @param selectDate
	 * @param idealType
	 * @param isAccept
	 */
	public TeacherSeeSelecting(Integer tableId,String userId,String userName,Object selectDate,String idealType,Integer isAccept,String topicId){
		this.tableId=tableId;
		this.userId=userId;
		this.userName=userName;
		try {
			this.selectDate = stringToTimestamp(selectDate.toString());
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		this.idealType=idealType;
		this.isAccept=isAccept;
		this.topicId=topicId;
		
		
	}
	/**
	 * 
	 * @Description: 教师查看录取名单
	 * @param @param userId
	 * @param @param userName
	 * @param @param topicId
	 * @param @param topicTitle
	 * @param @param tableId
	 * @param @param idealType
	 * @param @param isAccept
	 * @param @param instituteId
	 * @param @param instituteName   
	 * @throws
	 * @author Tony
	 * @date 2018年6月18日
	 */
	public TeacherSeeSelecting(String userId,String userName,String topicId,String topicTitle,
			Integer tableId,String idealType,Integer isAccept,Integer instituteId,String instituteName){
		this.userId=userId;
		this.userName=userName;
		this.topicId=topicId;
		this.topicTitle=topicTitle;
		this.tableId=tableId;
		this.idealType=idealType;
		this.isAccept=isAccept;
		this.instituteId=instituteId;
		this.instituteName=instituteName;
		
		
	}

	@Override
	public String toString() {
		return "TeacherSeeSelecting [sysUserId=" + sysUserId + ", userPassword=" + userPassword + ", userSex=" + userSex
				+ ", userBirthday=" + userBirthday + ", userType=" + userType + ", userPhone=" + userPhone
				+ ", classId=" + classId + ", teacherTitle=" + teacherTitle + ", userNative=" + userNative
				+ ", studentScore=" + studentScore + ", topicInfoId=" + topicInfoId + ", topicTitle=" + topicTitle
				+ ", topicContent=" + topicContent + ", topicRequest=" + topicRequest + ", topicSurplus=" + topicSurplus
				+ ", issueDate=" + issueDate + ", intNelen=" + intNelen + ", tableId=" + tableId + ", idealId="
				+ idealId + ", idealType=" + idealType + ", selectDate=" + selectDate + ", isAccept=" + isAccept
				+ ", instituteName=" + instituteName + ", institutePid=" + institutePid + ", className=" + className
				+ ", userId=" + userId + ", userName=" + userName + ", instituteId=" + instituteId + ", topicId="
				+ topicId + "]";
	}

	public static Timestamp stringToTimestamp(String dateStr) throws java.text.ParseException{
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Calendar cal = Calendar.getInstance();
	   try {
	    Date date = (Date)sdf.parse(dateStr);
	    date.getTime();
	    cal.setTime(date);
	    return new Timestamp(cal.getTimeInMillis());
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   cal.setTime(new Date());
	   return new Timestamp(cal.getTimeInMillis());
	}

	public Integer getSysUserId() {
		return sysUserId;
	}
	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
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
	public Date getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
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
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
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
	public Integer getTopicInfoId() {
		return topicInfoId;
	}
	public void setTopicInfoId(Integer topicInfoId) {
		this.topicInfoId = topicInfoId;
	}
	public String getTopicTitle() {
		return topicTitle;
	}
	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}
	public String getTopicContent() {
		return topicContent;
	}
	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}
	public String getTopicRequest() {
		return topicRequest;
	}
	public void setTopicRequest(String topicRequest) {
		this.topicRequest = topicRequest;
	}
	public Integer getTopicSurplus() {
		return topicSurplus;
	}
	public void setTopicSurplus(Integer topicSurplus) {
		this.topicSurplus = topicSurplus;
	}
	public Timestamp getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}
	public Integer getTableId() {
		return tableId;
	}
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}
	public String getIdealId() {
		return idealId;
	}
	public void setIdealId(String idealId) {
		this.idealId = idealId;
	}
	public String getIdealType() {
		return idealType;
	}
	public void setIdealType(String idealType) {
		this.idealType = idealType;
	}
	public Timestamp getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Timestamp selectDate) {
		this.selectDate = selectDate;
	}
	public Integer getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
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
	public Integer getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Integer getInstitutePid() {
		return institutePid;
	}

	public void setInstitutePid(Integer institutePid) {
		this.institutePid = institutePid;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getIntNelen() {
		return intNelen;
	}

	public void setIntNelen(Integer intNelen) {
		this.intNelen = intNelen;
	}
	
}
