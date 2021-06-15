package com.lblz.struts.action;

import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/29 17:58
 **/
public class TestServletActionContextAction {
    public String execute(){
        /**
         *  ServletActionContext:可以从中获取到当前Action,对象需要的一切Servlet API相关的对象.
         **/
        javax.servlet.http.HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("date",new Date());
        ServletContext servletContext = request.getServletContext();
        System.out.println("execute...");
        return "success";
    }
}
