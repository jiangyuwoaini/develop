package com.lblz.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBSource {
	private String driver; 
	private String url;
	private String username;
	private String password;
	public DBSource() {}
	
	/**  
	 * @Title: DBSource
	 * @Description: DBSource构造函数
	 * @param props
	 * @author Jy
	 * @date 2021-02-23 10:05:32 
	 */  
	public DBSource(Properties props) {
		this.driver = props.getProperty("driver");
		this.url = props.getProperty("url");
		this.username = props.getProperty("username");
		this.password = props.getProperty("password");
	}
	
	/**  
	 * @Title: openConnection
	 * @Description: TODO(打开数据库连接)
	 * @return
	 * @throws Exception
	 * @author Jy
	 * @date 2021-02-23 10:07:47 
	 */  
	public Connection openConnection() throws Exception{
		Class.forName(driver);
		return DriverManager.getConnection(url,username,password);//如果已经存在该对象呢?
	}
}
