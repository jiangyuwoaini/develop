package com.lblz.struts.valuestack.app;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

public class Employee implements RequestAware{
	
	private Map<String, Object> requestMap = null;
	
	private Dao dao = new Dao();
	
	private String name;
	private String password;
	
	private String gender;
	private String dept;
	
	private List<String> roles;
	private String desc;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String save(){
		System.out.println("save: " + this);
		return "save";
	}
	
	public String input(){
		requestMap.put("depts", dao.getDepartments());
		requestMap.put("roles", dao.getRoles());
		return "input";
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.requestMap = request;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", password=" + password
				+ ", gender=" + gender + ", dept=" + dept + ", roles=" + roles
				+ ", desc=" + desc + "]";
	}
	
}
