package com.jiangyu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**  
 * @ClassName: Column
 * @Description: TODO(列的注解,可用于该别名注解)
 * @author Jy
 * @date 2021-02-23 10:02:09 
*/  
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	String value() default "";
	boolean isNull() default true;
	boolean isId() default false; //是否为主键
}
