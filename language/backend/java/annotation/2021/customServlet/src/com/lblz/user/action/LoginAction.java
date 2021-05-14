package com.lblz.user.action;

import com.lblz.bean.User;
import com.lblz.dao.UserDao;
import com.lblz.mvc.action.SupportAction;
import com.lblz.user.action.form.UserForm;

/**
 * @ClassName: LoginAction
 * @Description: TODO(登陆页 控制器)
 * @author J y
 * @date 2021年3月7日
 *
 */
public class LoginAction extends SupportAction{
	UserDao dao;
	UserForm userFrom;
	
	String userName;
	String password;
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		if(dao == null || userFrom == null) {
			return "no";
		}
		User loginUser = dao.login(userName,password);
//		String username = paramsMap.get("username");
//		String password = paramsMap.get("password");
//		username.equals("阿巴阿巴") && password.equals("lblz")
		if(loginUser!=null) {
			session.setAttribute("username", userName);
			return "ok";
		}
		request.setAttribute("error", "用户名或口令错误！");
		return "no";
	}
	
}
