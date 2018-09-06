package com.gdts.selecting.entity;

import java.sql.Timestamp;

/**
 * 志愿实体
 * @author 陆建宁
 * @date 2018 5-31
 */
public class Ideal {
	private Integer id;
	private String idealId;
	private String idealType;//志愿类型。1-第一志愿。2-第二志愿
	private String topicId;//关联题目id
	private String userId;
	private Timestamp selectDate;//填报时间
	private Integer instituteId;
	private Integer isAccept;//是否录取。0-未录取。1-录取。2-退档
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(Timestamp selectDate) {
		this.selectDate = selectDate;
	}
	public Integer getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}
	public Integer getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(Integer isAccept) {
		this.isAccept = isAccept;
	}
	@Override
	public String toString() {
		return "Ideal [id=" + id + ", idealId=" + idealId + ", idealType=" + idealType + ", topicId=" + topicId
				+ ", userId=" + userId + ", selectDate=" + selectDate + ", instituteId=" + instituteId + ", isAccept="
				+ isAccept + "]";
	}
	
}
