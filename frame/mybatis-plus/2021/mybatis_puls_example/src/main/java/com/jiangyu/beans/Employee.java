package com.jiangyu.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * javaBean
 * 
 * 定义JavaBean中成员变量时所使用的类型: 
 * 	 因为每个基本类型都有一个默认值:  
 * 	   int ==> 0
 * 	   boolean ==> false
 *
 */

/*
 * MybatisPlus会默认使用实体类的类名到数据中找对应的表. 
 * 
 */
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@TableName(value="tbl_employee")
public class Employee {
	/*
	 * @TableId:
	 * 	 value: 指定表中的主键列的列名， 如果实体属性名与列名一致，可以省略不指定. 
	 *   type: 指定主键策略. 
	 */
	@TableId(value="id" , type =IdType.AUTO)
	private Integer id ;   //  int 
	
	@TableField(value = "last_name")
	private String  lastName; 
	private String  email ;
	private Integer gender; 
	private Integer age ;
	@TableField(exist=false)
	private Double salary ;
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", age="
				+ age + ", salary=" + salary + "]";
	} 
}
