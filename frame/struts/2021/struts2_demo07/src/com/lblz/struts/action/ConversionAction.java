package com.lblz.struts.action;

import java.util.Date;

import com.lblz.struts.module.Customer;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ConversionAction extends ActionSupport implements ModelDriven<Customer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute(){
		System.out.println("model: " + model);
		return "success";
	}
	
	private Customer model;

	@Override
	public Customer getModel() {
		model = new Customer();
		return model;
	}
	
}
