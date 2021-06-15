package com.lblz.struts.action;

import org.apache.struts2.interceptor.ApplicationAware;

import java.util.Map;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/30 9:44
 **/
public class UserAction implements ApplicationAware {
    private String name;
    public void setName(String name){
        this.name = name;
    }
    Map<String, Object> map;
    public String save(){
        map.put("name",name);
        return "save-success";
    }

    public String update(){
        map.put("name",name);
        return "update-success";
    }
    @Override
    public void setApplication(Map<String, Object> map) {
        this.map = map;
    }
}
