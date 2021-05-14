package com.lblz.bean;

import com.lblz.orm.annotation.Column;
import com.lblz.orm.annotation.Table;

/**  
 * @ClassName: User
 * @Description: 与sys_user对应的实体类
 * 1,如果注解属性只存在一个,且名为value,则在设置属性值时不需要写value=(不需要指定属性名)
 * 如:@Table("sys_user")
 * 2,如果注解中的属性有多个,必须指定属性名,多个属性之间用逗号分隔
 * @author Jy
 * @date 2021-02-12 03:30:36 
*/ 
@Table("sys_user")
public class User {
	@Column(value ="user_id",isId = true)
	private String id;
	private String userName;
	@Column("name")
	private String niceName;
	private String password;
	private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNiceName() {
		return niceName;
	}
	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", niceName=" + niceName + ", password=" + password
				+ ", phone=" + phone + "]";
	}
	
}
