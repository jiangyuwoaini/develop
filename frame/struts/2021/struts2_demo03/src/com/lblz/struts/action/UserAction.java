package com.lblz.struts.action;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * @author lblz
 * @deacription 做一个统计在线人数的小案例。
 * @date 2021/5/29 19:32
 **/
public class UserAction implements SessionAware , ParameterAware , ApplicationAware {
    Map<String,Object> sessionMap;
    Map<String,String[]> parametersMap;
    Map<String,Object> applicationMap;
    private String username;
    public void setUsername(String username){
        this.username = username;
    }
    @Override
    public void setApplication(Map<String, Object> map) {
        this.applicationMap = map;
    }
    @Override
    public void setSession(Map<String, Object> map) {
        this.sessionMap = map;
    }
    @Override
    public void setParameters(Map<String, String[]> map) {
        this.parametersMap = map;
    }

    public String loginOut(){
        //1.在线人数-1:获取在线人数,若数量还>0,则 - 1
        Integer count = (Integer)applicationMap.get("count");
        if(count != null && count > 0){
            count --;
            applicationMap.put("count",count);
        }
        //2.使session失效
        ((SessionMap)sessionMap).invalidate();
        return "logout-success";

    }
    public String execute(){
        //将用户信息存入session域中
        //1.获取session 通过实现sessionAware接口。

        //2.获取登陆信息:通过Action中添加

        //3.将用户存入session域中
//        sessionMap.put("username",parametersMap.get("username"));
        sessionMap.put("username",username);
        //在线人数 +1
        //1.获取当前的在线人数,从application中获取
        Integer count = (Integer)applicationMap.get("count");
        if(count == null){
            count = 0;
        }
        //使用人数+1
        count ++;
        applicationMap.put("count",count);
        return "logout-success";
    }
}
