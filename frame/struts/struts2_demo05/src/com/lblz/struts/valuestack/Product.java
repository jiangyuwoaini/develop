package com.lblz.struts.valuestack;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class Product {
	
	private Integer productId;
	private String productName;
	private String productDesc;
	
	private double productPrice;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productDesc=" + productDesc
				+ ", productPrice=" + productPrice + "]";
	}
	
	public String save(){
		System.out.println("save: " + this);
		
		//1. 获取值栈
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//2. 创建 Test 对象, 并为其属性赋值
		Test object = new Test();
		object.setProductDesc("AABBCCDD");
		object.setProductName("ABCD");
		
		//3. 把 Test 对象压入到值栈的栈顶!
		valueStack.push(object);
		
		return "success";
	}
	
}
