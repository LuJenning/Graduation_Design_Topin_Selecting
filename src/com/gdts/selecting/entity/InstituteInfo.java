package com.gdts.selecting.entity;
/**
 * 学院，专业信息实体
 * @author 秦松
 *
 */

public class InstituteInfo {
	private Integer instituteId;//学院，专业编号
	private String instituteName;//学院，专业名称
	private Integer institutePid;//父id、如果institute_id是专业则该字段为该专业所在院系的id
	
	
	@Override
	public String toString() {
		return "InstituteInfo [instituteId=" + instituteId + ", instituteName=" + instituteName + ", institutePid="
				+ institutePid + "]";
	}
	
	public Integer getInstituteId() {
		return instituteId;
	}
	
	public void setInstituteId(Integer instituteId) {
		this.instituteId = instituteId;
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

}
