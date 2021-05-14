package com.jiangyu.bean;


import java.io.Serializable;

/**
 * 
 * @ClassName: JsonValueExtendEliminate 
 * @Description: TODO 方案违背病例列表
 * @author qiuj qj@runtrial.com 
 * @date 2020-3-18 下午12:49:40 
 *
 */
public class JsonValueExtendViolation implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -4728844949354172243L;
	private String violationSubNo; //受试者编号
	private String violationEvent;
	private String treatment;
	private String subNumber;
	private String violationReported;
	

	
	public JsonValueExtendViolation(String violationSubNo, String violationEvent, String treatment, String subNumber, String violationReported) {
		this.violationSubNo = violationSubNo;
		this.violationEvent = violationEvent;
		this.treatment = treatment;
		this.subNumber = subNumber;
		this.violationReported = violationReported;
	}



	public String getViolationSubNo() {
		return violationSubNo;
	}



	public void setViolationSubNo(String violationSubNo) {
		this.violationSubNo = violationSubNo;
	}



	public String getViolationEvent() {
		return violationEvent;
	}



	public void setViolationEvent(String violationEvent) {
		this.violationEvent = violationEvent;
	}



	public String getTreatment() {
		return treatment;
	}



	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}



	public String getSubNumber() {
		return subNumber;
	}



	public void setSubNumber(String subNumber) {
		this.subNumber = subNumber;
	}



	public String getViolationReported() {
		return violationReported;
	}



	public void setViolationReported(String violationReported) {
		this.violationReported = violationReported;
	}


	

	
}
