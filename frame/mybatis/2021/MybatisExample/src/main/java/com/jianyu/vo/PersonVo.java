package com.jianyu.vo;

import com.jianyu.param.CustomPerson;
/**  
 * @ClassName: PersonVo
 * @Description: 以Vo的方式去做CRUD操作
 * @author Jy
 * @date 2021-02-04 10:12:51 
*/  
public class PersonVo {
	private CustomPerson customPerson;

	public CustomPerson getCustomPerson() {
		return customPerson;
	}

	public void setCustomPerson(CustomPerson customPerson) {
		this.customPerson = customPerson;
	}
	
}
