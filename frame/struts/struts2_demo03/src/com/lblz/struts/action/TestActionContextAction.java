package com.lblz.struts.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.Map;

/**
 * @author lblz
 * @deacription
 * @date 2021/5/29 11:05
 **/
public class TestActionContextAction {
    public String execute(){
        //0.获取ActionContext对象
        //ActionContext是Action的上下问对象,可以从中获取到当往Action需要的一切
        ActionContext actionContext = ActionContext.getContext();
        //1. 获取 application 对应的 Map, 并向其中添加一个属性
        //通过调用 ActionContext 对象的 getApplication() 方法来获取 application 对象的 Map 对象
        Map<String, Object> applicationMap = actionContext.getApplication();
        applicationMap.put("applicationKey", "applicationValue");//设置属性
        //获取属性
        Object data = applicationMap.get("data");
        System.out.println("data:"+data);
        //2.session
        Map<String, Object> sessionMap = actionContext.getSession();
        sessionMap.put("sessionKey", "sessionValue");
        System.out.println(sessionMap.getClass());
//        if(sessionMap instanceof SessionMap){
//            SessionMap sm =(SessionMap)sessionMap;
//            sm.invalidate();;
//            System.out.println("session失效咯");
//        }
        //3.request*
        //ActionContext中并没有提供getRequest方法获取request对应Map
        //需要手工调用get()方法,传request字符串获取
//        Map<String,Object> requestMap = (Map<String,Object>)applicationMap.get("request");
//        requestMap.put("requestKey","requestValue");

        //4. 获取请求参数对应的 Map, 并获取指定的参数值.
        //键: 请求参数的名字, 值: 请求参数的值对应的字符串数组
        //注意: 1. getParameters 的返回值为在 Map<String, Object>, 而不是 Map<String, String[]>
        //     2. parameters 这个 Map 只能读, 不能写入数据, 如果写入, 但不出错, 但也不起作用!
        Map<String, Object> parameters = actionContext.getParameters();
        System.out.println(((String[])parameters.get("name"))[0]);
        parameters.put("age",100);
        return "success";
    }
}
