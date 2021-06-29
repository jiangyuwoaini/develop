package com.jianyu.entity;
/**  
 * @ClassName: Person
 * @Description: 学生实体类
 * @author Jy
 * @date 2021-02-02 01:21:56 
*/

import java.time.LocalDateTime;

public class Person {
	private Integer id; //id
	private String name; //姓名
	private boolean sex; //性别
	private LocalDateTime birthday; //生日
	private Integer age;
	private String address; //地址
//	public Person(Integer id, String name, String sex, LocalDateTime birthday, String address) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.sex = sex;
//		this.birthday = birthday;
//		this.address = address;
//	}
	public Integer getId() {
		return id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public LocalDateTime getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", sex=" + sex + ", birthday=" + birthday + ", address="
				+ address + "]";
	}
}
