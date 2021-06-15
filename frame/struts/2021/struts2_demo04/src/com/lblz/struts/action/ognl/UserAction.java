package com.lblz.struts.action.ognl;

import com.opensymphony.xwork2.ActionContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author lblz
 * @deacription
 * @date 2021/6/2 21:51
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAction {
    private String userId;
    private String userName;
    private String password;
    private String desc;
    private Boolean married;
    private String gender;
    private List<String> city;
    private String age;
    public String save(){
        UserAction user = new UserAction();
        user.setDesc("Oracle!");
        user.setPassword("12345");
        user.setUserId("1001");
        user.setUserName("ATGUIGU");
//        ActionContext.getContext().getValueStack().push(user); //压入栈值
        System.out.println(this); //回显
        return "input";
    }
}
