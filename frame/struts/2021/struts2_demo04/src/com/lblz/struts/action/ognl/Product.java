package com.lblz.struts.action.ognl;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author lblz
 * @deacription
 * @date 2021/5/30 11:09
 **/

public class Product implements RequestAware, SessionAware {
    private Integer productId;
    private String productName;
    private String productDesc;
    private double productPrice;

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public double getProductPrice() {
        return productPrice;
    }

    private List<Person> person = new ArrayList<>();
    public List<Person> getPerson() {
        return person;
    }

    public String testTag(){
        this.productId = 1001;
        this.productDesc = "123";
        this.productName = "productName";
        this.productPrice = 1000.0;
        person.add(new Person("ccc",33));
        person.add(new Person("aaa",22));
        person.add(new Person("bbb",11));
        return "success";
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
        sessionMap.put("product",this);
        requestMap.put("test",object);
        //值栈异常
        int i = 10 / 0;
        return "success";
    }

    Map<String, Object> requestMap;
    Map<String, Object> sessionMap;
    @Override
    public void setRequest(Map<String, Object> map) {
        this.requestMap = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }
}
