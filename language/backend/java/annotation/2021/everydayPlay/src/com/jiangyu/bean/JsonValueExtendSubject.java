package com.jiangyu.bean;


import java.io.Serializable;

/**
 * 
 * @ClassName: JsonValueExtendSubject 
 * @Description: TODO 受试者信息
 * @author qiuj qj@runtrial.com 
 * @date 2020-3-18 下午12:20:28 
 *
 */
public class JsonValueExtendSubject implements Serializable{
	

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -756538096375433317L;
	private String subNo; //受试者编号
	private String subSaeName;
	private String reportType;
	private String eventOutcome;
	private String relevanceJudgement;
	private String reported;

	
	public JsonValueExtendSubject(String subNo, String subSaeName, String reportType, String eventOutcome, String relevanceJudgement, String reported) {
		this.subNo = subNo;
		this.subSaeName = subSaeName;
		this.reportType = reportType;
		this.eventOutcome = eventOutcome;
		this.relevanceJudgement = relevanceJudgement;
		this.reported = reported;
	}


	public String getSubNo() {
		return subNo;
	}


	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}

	public String getSubSaeName() {
		return subSaeName;
	}


	public void setSubSaeName(String subSaeName) {
		this.subSaeName = subSaeName;
	}


	public String getReportType() {
		return reportType;
	}


	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


	public String getEventOutcome() {
		return eventOutcome;
	}


	public void setEventOutcome(String eventOutcome) {
		this.eventOutcome = eventOutcome;
	}


	public String getRelevanceJudgement() {
		return relevanceJudgement;
	}


	public void setRelevanceJudgement(String relevanceJudgement) {
		this.relevanceJudgement = relevanceJudgement;
	}


	public String getReported() {
		return reported;
	}


	public void setReported(String reported) {
		this.reported = reported;
	}
	
	

	
}
