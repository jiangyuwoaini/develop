package annotationExample;

import java.lang.reflect.Field;

import com.jiangyu.annotation.ORMAnnoHelper;
import com.jiangyu.bean.User;

public class TestTableAnno {
	public static void main(String[] args) {
		String tableName = ORMAnnoHelper.getTableName(User.class);
		System.out.println(tableName); 
		
		Field[] fieldArr = User.class.getDeclaredFields();
		for (Field f : fieldArr) {
			System.out.println(ORMAnnoHelper.getColumnName(f));
		}
	}
} 
