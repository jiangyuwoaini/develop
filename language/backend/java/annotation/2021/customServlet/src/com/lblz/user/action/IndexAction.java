package com.lblz.user.action;

import com.lblz.mvc.action.SupportAction;

public class IndexAction  extends  SupportAction{

	@Override
	public String execute() {
		// TODO Auto-generated method stub
		//判断用户是否存在
		if(session.getAttribute("username") == null) {
			return FAIL;
		}
		return super.execute();
	} 
	
}
