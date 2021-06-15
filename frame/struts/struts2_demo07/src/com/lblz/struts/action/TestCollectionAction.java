package com.lblz.struts.action;

import java.util.Collection;

import com.lblz.struts.module.Manager;
import com.opensymphony.xwork2.ActionSupport;
//类型转换
public class TestCollectionAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<Manager> mgrs = null;
	
	public Collection<Manager> getMgrs() {
		return mgrs;
	}

	public void setMgrs(Collection<Manager> mgrs) {
		this.mgrs = mgrs;
	}

	public String execute() throws Exception {
		System.out.println(mgrs);
		return SUCCESS;
	}
}
