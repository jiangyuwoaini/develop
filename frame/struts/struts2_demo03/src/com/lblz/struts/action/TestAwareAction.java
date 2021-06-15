package com.lblz.struts.action;

import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;

/**
 * @author lblz
 * @deacription 实现的方式去获取application的内容 不过要实现一些类,想要获取session则要实现SessionAware
 * ,如果request要实现RequestAware如果想要参数则要实现ParameterAware类,达到多实现。
 * @date 2021/5/29 17:41
 **/
public class TestAwareAction implements ApplicationAware {
    private Map<String,Object> applicationMap;
    public String execute(){
        //1.向application中加入属性:applicationKey2 -applicationValue2
        applicationMap.put("applicationKey2","applicationValue2");
        //2.从application中读取一个属性date,并打印
        System.out.println(applicationMap.get("date"));
        return "success";
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        this.applicationMap  = map;
    }
}
