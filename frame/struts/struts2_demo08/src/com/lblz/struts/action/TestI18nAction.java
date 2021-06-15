package com.lblz.struts.action;

import java.util.Arrays;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @Author J y
 * @Description struts国际化
 * @Date 2021/6/13 13:36
 **/
public class TestI18nAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date date = null;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String execute() throws Exception {
		
		date = new Date();
		
		//1. 在 Action 中访问国际化资源文件的 value 值
		String username = getText("username");
		System.out.println(username);
		
		//2. 带占位符的
		String time = getText("time", Arrays.asList(date));
		System.out.println(time);
		
		return SUCCESS;
	}
}
