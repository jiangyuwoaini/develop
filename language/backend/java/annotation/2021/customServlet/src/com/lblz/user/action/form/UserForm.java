package com.lblz.user.action.form;
/**
 * @ClassName: UserForm
 * @Description: TODO(封装用户请求参数的表单实体类)
 * @author J y
 * @date 2021年3月7日
 *
 */
public class UserForm {
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
