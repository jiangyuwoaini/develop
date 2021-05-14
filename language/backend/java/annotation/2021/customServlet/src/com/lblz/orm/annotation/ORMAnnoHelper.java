package com.lblz.orm.annotation;

import java.io.File;
import java.lang.reflect.Field;

/**  
 * @ClassName: ORMAnnoHelper
 * @Description: 实现ORM框架中解析相关注解的工具类
 * 				 1,获取类上的@Table注解信息
 * 				 2,获取字段上的@Column注解信息
 * @author Jy
 * @date 2021-02-12 03:55:38 
*/  
/**  
 * @ClassName: ORMAnnoHelper
 * @Description: TODO(描述)
 * @author Jy
 * @date 2021-02-12 04:10:07 
*/  
public class ORMAnnoHelper {
	/**  
	 * @Title: getTableName
	 * @Description: TODO(返回该类名上的表名)
	 * @param beanCls
	 * @return String
	 * @author Jy
	 * @date 2021-02-12 03:59:14 
	 */  
	public static String getTableName(Class<?> beanCls) {
		Table tableAnno = beanCls.getAnnotation(Table.class);
		if(tableAnno == null) //类上没有注解 代表类名与表名一致
			//return beanCls.getClass().getName();
			return beanCls.getSimpleName().toLowerCase();
		return tableAnno.value();
	}
	public static Field findIdField(Class<?> cls) {
		for(Field f: cls.getDeclaredFields()) {
			if(isId(f)) {
				return f;
			}
		}
		return null;
	}
	/**  
	 * @Title: getColumnName
	 * @Description: 返回指定字段上的列名
	 * @return
	 * @author Jy
	 * @date 2021-02-12 04:10:11 
	 */  
	public static String getColumnName(Field field) {
		Column cloumnAnnotation = field.getAnnotation(Column.class);
		if(cloumnAnnotation == null ) 
			//获取字段名称
			return field.getName().toLowerCase();
		return cloumnAnnotation.value();
	}
	public static boolean isId(Field field) {
		Column columnAnno = field.getAnnotation(Column.class);
		if(columnAnno!=null) {
			//获取字段是主键字段
			return columnAnno.isId();
		}
		return false;
	}
}
