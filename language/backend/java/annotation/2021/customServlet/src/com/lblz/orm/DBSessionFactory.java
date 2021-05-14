package com.lblz.orm;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import com.lblz.orm.annotation.ORMAnnoHelper;
import com.lblz.bean.User;

/**  
 * @ClassName: DBSessionFactory
 * @Description: 获取数据源Session工厂工具类
 * @author Jy
 * @date 2021-02-12 04:28:10 
*/  
public class DBSessionFactory {
	private DBSource dbSource; //数据源
	private Properties props; //数据源连接的属性
	public DBSessionFactory() {
		props = new Properties();
		try {
			//从属性资源文件中加载key-value
			props.load(DBSessionFactory.class.getClassLoader().getResourceAsStream("dbConfig.propertiees"));
			dbSource = new DBSource(props);
			// dbSource.openConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
	
	public DBsession openSession()throws Exception{
		return new DBsession(dbSource.openConnection());
	}
	public static void main(String[] args) {
		new DBSessionFactory();
	}

}

