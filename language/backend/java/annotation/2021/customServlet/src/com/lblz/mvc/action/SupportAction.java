package com.lblz.mvc.action;
/**
 * @ClassName: SupportAction
 * @Description: TODO(业务控制器类)
 * @author J y
 * @date 2021年3月6日
 *
 */

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SupportAction {
	protected HttpServletRequest request;
	protected HttpSession session;
	
	protected final String SUCCESS ="SUCCESS";
	protected final String FAIL = "fail";
	public HashMap<String, String> paramsMap;
	//业务控制器执行方法
	public String execute() {
		return SUCCESS;
	}
	
	
	public HashMap<String, String> getParams(){
		paramsMap = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			paramsMap.put(name, request.getParameter(name));
		}
		return paramsMap;
	}
	
	/**
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @Title: parseParams4Form
	 * @Description: TODO(将请求参数封装到Form实体类中)
	 * @param  formCls
	 * @param  参数
	 * @return Object 返回类型
	 * @throws
	 */
	public Object parseParams4Form(Class<?> formCls) throws InstantiationException, IllegalAccessException {
		if(paramsMap == null || paramsMap.isEmpty()) 
			return null;
		Object object = formCls.newInstance();
		for (Field f : formCls.getDeclaredFields()) {
			f.setAccessible(true);
			f.set(object, paramsMap.get(f.getName()));
		}
		return object;
	}
}
