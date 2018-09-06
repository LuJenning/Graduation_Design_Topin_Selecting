package com.gdts.selecting.entity;

import java.sql.Timestamp;

public class TopicInfo {
	private Integer id;
	private String topicId;
	private String topicTitle;//课题标题
	private String topicContent;//课题内容
	private String topicRequest;// 课题要求
	private Integer topicSurplus;// 课题总量
	private Timestamp issueDate;// 课题发布时间
	private String userId;// 关联教师id
	private Integer instituteId;
	private Integer  intNelen;//已选课题数量：已选课题数量<=课题总量
	
	@Override
	public String toString() {
		return "TopicInfo [id=" + id + ", topicId=" + topicId + ", topicTitle=" + topicTitle + ", topicContent="
				+ topicContent + ", topicRequest=" + topicRequest + ", topicSurplus=" + topicSurplus + ", issueDate="
				+ issueDate + ", userId=" + userId + ", instituteId=" + instituteId + ", intNelen=" + intNelen + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}


	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public Integer getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
	}

	public Integer getIntNelen() {
		return intNelen;
	}

	public void setIntNelen(Integer intNelen) {
		this.intNelen = intNelen;
	}
}
