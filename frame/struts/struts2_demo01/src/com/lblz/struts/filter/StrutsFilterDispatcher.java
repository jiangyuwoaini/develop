package com.lblz.struts.filter;

import com.lblz.struts.pojo.Product;
import com.sun.deploy.net.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author lblz
 * @deacription filter控制器
 * @date 2021/5/27 23:21
 **/
public class StrutsFilterDispatcher implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrutsFilterDispatcher.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String path = "";
        //1、获取servletPath
        String servletPath = request.getServletPath();
        LOGGER.info("servlet路径 = {}",servletPath);
        //2.判断servletPath,若其等于"/product-input.action",则转发/WEB-INF/pages/input.jsp
        if("/product-input.action".equals(servletPath)){
            path = "/WEB-INF/pages/input.jsp";
        }
        //3.若其等于"/product-save.action",则
        if("/product-save.action".equals(servletPath)){
            //1).获职请求参数1
            String productName = request.getParameter("productName");
            String productDesc = request.getParameter("productDesc");
            String productPrice = request.getParameter("productPrice");
            //2)．把请求信息封装为一个Product对象
            Product product = new Product(null, productName, productDesc, Double.parseDouble(productPrice));
            //3).执行保存操作1
            LOGGER.info("Save Product: {}",product);
            product.setProductId(1001);
            //4)、把Product对象保存到request 中. . ${param.productName} -> ${requestScope.product.productName}
            request.setAttribute("product", product);
            path = "/WEB-INF/pages/details.jsp";
        }
        if(path != null){
            request.getRequestDispatcher(path).forward(request,servletResponse);
            return ;
        }
    }

    @Override
    public void destroy() {

    }
}
