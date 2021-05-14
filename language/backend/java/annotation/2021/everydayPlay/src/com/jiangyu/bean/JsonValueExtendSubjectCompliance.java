package com.jiangyu.bean;


import java.io.Serializable;

/**
 * 
 * @ClassName: JsonValueExtendSubjectCompliance 
 * @Description: TODO 方案违背中的受试者信息
 * @author qiuj qj@runtrial.com 
 * @date 2020-3-18 下午4:48:21 
 *
 */
public class JsonValueExtendSubjectCompliance implements Serializable{
	

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 

	private static final long serialVersionUID = -756538096375433317L;
	private String comSubNo; //受试者编号
	private String comType; //方案违背类型
	private String comContent; //事件描述
	private String comReason; //事件发生的原因
	private String comImpactOnSubjects; //对受试者的影响
	private String comImpactOnResearchResults; //对研究结果的影响
	private String comTreatment; //处理措施

	
	public JsonValueExtendSubjectCompliance(String comSubNo, String comType, String comContent, 
			String comReason, String comImpactOnSubjects, String comImpactOnResearchResults, String comTreatment) {
		this.comSubNo = comSubNo;
		this.comType = comType;
		this.comContent = comContent;
		this.comReason = comReason;
		this.comImpactOnSubjects = comImpactOnSubjects;
		this.comImpactOnResearchResults = comImpactOnResearchResults;
		this.comTreatment = comTreatment;
	}


	public String getComSubNo() {
		return comSubNo;
	}


	public void setComSubNo(String comSubNo) {
		this.comSubNo = comSubNo;
	}


	public String getComType() {
		return comType;
	}


	public void setComType(String comType) {
		this.comType = comType;
	}


	public String getComContent() {
		return comContent;
	}


	public void setComContent(String comContent) {
		this.comContent = comContent;
	}


	public String getComReason() {
		return comReason;
	}


	public void setComReason(String comReason) {
		this.comReason = comReason;
	}


	public String getComImpactOnSubjects() {
		return comImpactOnSubjects;
	}


	public void setComImpactOnSubjects(String comImpactOnSubjects) {
		this.comImpactOnSubjects = comImpactOnSubjects;
	}


	public String getComImpactOnResearchResults() {
		return comImpactOnResearchResults;
	}


	public void setComImpactOnResearchResults(String comImpactOnResearchResults) {
		this.comImpactOnResearchResults = comImpactOnResearchResults;
	}


	public String getComTreatment() {
		return comTreatment;
	}


	public void setComTreatment(String comTreatment) {
		this.comTreatment = comTreatment;
	}


	
	

	
}
