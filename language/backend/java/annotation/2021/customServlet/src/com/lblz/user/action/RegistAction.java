package com.lblz.user.action;

import java.util.UUID;

import com.lblz.bean.User;
import com.lblz.dao.UserDao;
import com.lblz.mvc.action.SupportAction;

/**
 * @ClassName: RegistAction
 * @Description: TODO(注册的业务控制器)
 * @author J y
 * @date 2021年3月7日
 *
 */
public class RegistAction extends SupportAction{
	UserDao dao;
	User user;
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		if(dao == null || user ==null) {
			session.setAttribute("error", "请求失败,请与管理员联系！");
		}
		user.setId(UUID.randomUUID().toString().replace("-", ""));
		//注册之前判断用户名是否存在?
		if(dao.save(user) >0) {
			return "login";
		}
		return "regist";
	}
}
