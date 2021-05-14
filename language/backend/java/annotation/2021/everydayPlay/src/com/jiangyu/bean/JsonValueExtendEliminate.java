package com.jiangyu.bean;



import java.io.Serializable;

/**
 * 
 * @ClassName: JsonValueExtendEliminate 
 * @Description: TODO 剔除病例列表
 * @author qiuj qj@runtrial.com 
 * @date 2020-3-18 下午12:49:40 
 *
 */
public class JsonValueExtendEliminate implements Serializable{
	
	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6966073883659401539L;
	private String eliminateSubNo; //受试者编号
	private String eliminateReason;

	
	public JsonValueExtendEliminate(String eliminateSubNo, String eliminateReason) {
		this.eliminateSubNo = eliminateSubNo;
		this.eliminateReason = eliminateReason;
	}


	public String getEliminateSubNo() {
		return eliminateSubNo;
	}


	public void setEliminateSubNo(String eliminateSubNo) {
		this.eliminateSubNo = eliminateSubNo;
	}


	public String getEliminateReason() {
		return eliminateReason;
	}


	public void setEliminateReason(String eliminateReason) {
		this.eliminateReason = eliminateReason;
	}

	
}
