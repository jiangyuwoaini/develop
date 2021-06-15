package com.lblz.struts.action;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lblz
 * @deacription
 * 通过实现ServletXxxAware接口的方式可以由Struts2注入需要的Servlet相关的对象
 *      ServletRequestAware:注入HttpServletRequest对象(比较常用)
 *      ServletContextAware:注入ServletContext对象(比较常用)
 *      ServletResponsAware:注入HttpServletResponse对象
 * @date 2021/5/29 18:06
 **/
public class TestServletAwareAction implements ServletRequestAware,ServletContextAware, ServletResponseAware {
    @Override
    public void setServletRequest(HttpServletRequest request) {
        request.setAttribute("get","得失");
        System.out.println(request);
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        System.out.println(response);
    }

    @Override
    public void setServletContext(ServletContext servlet) {
        System.out.println(servlet);
    }
    public String execute(){
        return "success";
    }
}
