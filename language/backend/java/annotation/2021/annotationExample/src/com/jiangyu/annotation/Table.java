package com.jiangyu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**  
 * @ClassName: Table
 * @Description: 
 * 	注解的基本语法:
	//必须声明元注解(有效性、位置)
	public @interface Table{
		String value(); //属性方法
	}
	必用的元注解:
		@Retention(RetentionPolicy.RUNTIMEI|SOURCE|CLASS)
			//注解的有效性
			RUNTIMEI:运行时注解有效[常用]
			SOURCE:源代码有效
			CLASS:字节码有效
		@Target(ElementType.Type|METHOD|FIELD|...)
			//注解可被使用的位置
			TYPE: 类上可用
			METHOD:方法上可用
			FELD: 属性字段上可用
 * @author Jy
 * @date 2021-02-12 03:28:36 
*/  
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	//声明注解属性-结构:
	//数据类型 属性名() default 默认值
	//String name() default "";
	String value(); //value是默认属性名,在赋值时默认给value
}
