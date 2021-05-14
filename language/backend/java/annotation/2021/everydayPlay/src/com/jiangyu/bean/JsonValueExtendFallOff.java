package com.jiangyu.bean;



import java.io.Serializable;

/**
 * 
 * @ClassName: JsonValueExtendFallOff 
 * @Description: TODO 脱落信息
 * @author qiuj qj@runtrial.com 
 * @date 2020-3-18 下午12:49:40 
 *
 */
public class JsonValueExtendFallOff implements Serializable{
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4308896764259605234L;
	private String fallSubNo; //受试者编号
	private String fallOffReason;

	
	public JsonValueExtendFallOff(String fallSubNo, String fallOffReason) {
		this.fallSubNo = fallSubNo;
		this.fallOffReason = fallOffReason;
	}


	public String getFallSubNo() {
		return fallSubNo;
	}


	public void setFallSubNo(String fallSubNo) {
		this.fallSubNo = fallSubNo;
	}


	public String getFallOffReason() {
		return fallOffReason;
	}


	public void setFallOffReason(String fallOffReason) {
		this.fallOffReason = fallOffReason;
	}


	
}
