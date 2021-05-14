package com.lblz.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lblz.bean.User;
import com.lblz.orm.annotation.ORMAnnoHelper;

/**  
 * @ClassName: DBseesion
 * @Description: 操作数据库 增 删 查 改都在这里进行操作
 * @author Jy
 * @date 2021-02-12 04:53:19 
*/  
//定义泛型
//定义新的泛型<T>来声明,声明的位置有两个级别,第一种级别在为类名
//后面声明,即为类级别泛型,这种级别的泛型在整个类中是有效的,第二种级别
//是在方法上的访问权限是在	方法后面声明的,即只能在方法内部有效
//例:
//第一种 类级别
/* public intface Dao<T>{
 * 	public void save(T obj);
 * 	public void udpate(T obj); //类泛型全局可用
 * }
 * 第二种 
 * public class BaseDao{
 * 	public <T> T get(Class<T> cls,Long id){};
 * }
 * 
 * */
public class DBsession{
	private Connection conn; //数据库连接对象
	public DBsession(Connection conn) {this.conn = conn;}

	public <T> List<T> list(Class<T> cls) throws Exception{
		List<T> list = new ArrayList<T>();
		String sql = "SELECT %s FROM %s";
		//生成查询字段的列表		
		StringBuilder ColumsStr = new StringBuilder();
		Field[] fs = cls.getDeclaredFields();
		for(int i =0,len = fs.length;i<len; i++) {//实体类属性字段
			ColumsStr.append(ORMAnnoHelper.getColumnName(fs[i]));
			if(i!=len-1) {
				ColumsStr.append(",");
			}
		}
		//生成完整的sql语句
		sql = String.format(sql, ColumsStr.toString(),ORMAnnoHelper.getTableName(cls));
		//创建执行sql的语句对象(Statment,PreparedStatement)
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		T obj = null;
		while(rs.next()) {
			//实例化实体类对象
			obj = cls.newInstance();
			//读取指定字段的数据并注入对应对象的属性上
			for(Field f: fs) {
				f.setAccessible(true);
				Class<?> type = f.getType();	
				if(type == String.class) {
					//注入属性 
					f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
				}else if(type == int.class || type == Integer.class) {
					f.set(obj, rs.getInt(ORMAnnoHelper.getColumnName(f)));
				}else if(type == double.class || type == Double.class) {
					f.set(obj, rs.getDouble(ORMAnnoHelper.getColumnName(f)));
				}else if(type == Date.class) {
					f.set(obj, rs.getDate(ORMAnnoHelper.getColumnName(f)));
				}
			}
			//将实体类添加到list集合中去
			list.add(obj);
		}
		stmt.close();
		return list;
	}
	
	/**  
	 * @Title: save
	 * @Description: TODO(对记录进行保存)
	 * @param obj
	 * @return
	 * @author Jy
	 * @throws SQLException 
	 * @date 2021-02-23 09:40:42 
	 */  
	public int save(Object obj) throws Exception {
		String sql = "INSERT INTO %S(%S) value(%s)";
		StringBuilder columns = new StringBuilder();
		StringBuilder params = new StringBuilder();
		Field[] fs = obj.getClass().getDeclaredFields();//获取实体对象中的所有字段
		for(int i =0,len = fs.length; i<len; i++) {
			columns.append(ORMAnnoHelper.getColumnName(fs[i]));//获取列名
			params.append("?");
			if(i!=len-1) {
				columns.append(",");
				params.append(",");
			}
		}
		sql = String.format(sql, ORMAnnoHelper.getTableName(obj.getClass())
				,columns.toString()
				,params.toString());//生成最终sql
		//创建预处理SQL语句
		PreparedStatement pstmt = conn.prepareStatement(sql);//--将公共的代码进行重构
		sqlParamUitls(pstmt, sql, obj, fs);
		int rows = pstmt.executeUpdate();
		return rows;
	}
	/**  
	 * @Title: listResultHandler
	 * @Description: TODO(单行数据处理)
	 * @param <T>
	 * @param cls
	 * @param rs
	 * @return
	 * @throws Exception
	 * @author Jy
	 * @date 2021-02-24 09:35:39 
	 */  
	private <T> T oneResultHandler(Class<T> cls,ResultSet rs) throws Exception{
		List<T> list = new ArrayList<T>();
		T obj = null;
		Field[] fs = cls.getDeclaredFields();
		if(rs.next()) {
			//实例化实体类对象
			obj = cls.newInstance();
			//读取指定字段的数据并注入对应对象的属性上
			for(Field f: fs) {
				f.setAccessible(true);
				Class<?> type = f.getType();	
				if(type == String.class) {
					//注入属性 
					f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
				}else if(type == int.class || type == Integer.class) {
					f.set(obj, rs.getInt(ORMAnnoHelper.getColumnName(f)));
				}else if(type == double.class || type == Double.class) {
					f.set(obj, rs.getDouble(ORMAnnoHelper.getColumnName(f)));
				}else if(type == Date.class) {
					f.set(obj, rs.getDate(ORMAnnoHelper.getColumnName(f)));
				}
			}
			
		}
		return obj;
	}
	/**  
	 * @Title: listResultHandler
	 * @Description: TODO(多行数据处理)
	 * @param <T>
	 * @param cls
	 * @param rs
	 * @return
	 * @throws Exception
	 * @author Jy
	 * @date 2021-02-24 09:35:39 
	 */  
	private <T> List<T> listResultHandler(Class<T> cls,ResultSet rs) throws Exception{
		List<T> list = new ArrayList<T>();
		T obj = null;
		Field[] fs = cls.getDeclaredFields();
		while(rs.next()) {
			//实例化实体类对象
			obj = cls.newInstance();
			//读取指定字段的数据并注入对应对象的属性上
			for(Field f: fs) {
				f.setAccessible(true);
				Class<?> type = f.getType();	
				if(type == String.class) {
					//注入属性 
					f.set(obj, rs.getString(ORMAnnoHelper.getColumnName(f)));
				}else if(type == int.class || type == Integer.class) {
					f.set(obj, rs.getInt(ORMAnnoHelper.getColumnName(f)));
				}else if(type == double.class || type == Double.class) {
					f.set(obj, rs.getDouble(ORMAnnoHelper.getColumnName(f)));
				}else if(type == Date.class) {
					f.set(obj, rs.getDate(ORMAnnoHelper.getColumnName(f)));
				}
			}
			//将实体类添加到list集合中去
			list.add(obj);
		}
		return list;
	}
	
	/**  
	 * @Title: update
	 * @Description: TODO(自定义ORM框架之修改功能实现)
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author Jy
	 * @date 2021-02-24 09:57:35 
	 */  
	public int update(Object obj) throws Exception {
		String sql ="update %s set %s where %s";
		StringBuilder updateColumns = new StringBuilder();
		String where ="";
		Field[] fs = obj.getClass().getDeclaredFields();//获取所有的对象字段(包含主键的字段)
		Field[] fst = new Field[fs.length-1]; //更新字段的数组
		Field field = null;
		for(int i = 0,len = fs.length;i<len; i++) {
			field = fs[i];
			if(ORMAnnoHelper.isId(field)) {//判断是否为主键
				field.setAccessible(true); //设置访问权限
				where = ORMAnnoHelper.getColumnName(field)+"=";
				if(field.getType() == String.class) {	
					where += "'"+String.valueOf(field.get(obj))+"'";
				}else {
					where += field.get(obj);
				}
			continue;
			}
			//非逐渐的话
			updateColumns.append(ORMAnnoHelper.getColumnName(field)+"=?");
			fst[i-1] = fs[i];
			if(i != len-1) {
				updateColumns.append(",");
			}
			
		}
		sql = String.format(sql, ORMAnnoHelper.getTableName(obj.getClass())
				,updateColumns.toString(),where);
		//执行预处理更新语句,参数设置
		//创建预处理SQL语句
		PreparedStatement pstmt = conn.prepareStatement(sql);//--将公共的代码进行重构
		sqlParamUitls(pstmt, sql, obj, fst);
		int rows = pstmt.executeUpdate();//执行预处理语句
		return rows;
	}
	
	public int delete(Class<?> cls,Object id) throws SQLException {
		Field idfField = ORMAnnoHelper.findIdField(cls);
		String where =  ORMAnnoHelper.getColumnName(idfField) +"=";
		if(idfField.getType() == String.class) {
			where += "'"+id+"'";
		}else {
			where += id;
		}
		String sql = String.format("DELETE  FROM %s WHERE %s", ORMAnnoHelper.getTableName(cls),where);
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(sql);
		stmt.close();
		return rows;
	}
	
	/**  
	 * @Title: getById
	 * @Description: TODO(获取一条数据)
	 * @param <T>
	 * @param cls
	 * @param id
	 * @return
	 * @throws Exception
	 * @author Jy
	 * @date 2021-02-24 09:41:45 
	 */  
	public <T> T getById(Class<T> cls,Object id) throws Exception {
		Field idfField = ORMAnnoHelper.findIdField(cls);
		String where = ORMAnnoHelper.getColumnName(idfField) +"=";
		if(idfField.getType() == String.class) {
			where += "'"+id+"'";
		}else {
			where += id;
		}
		String sql = String.format("SELECT * FROM %s WHERE %s", ORMAnnoHelper.getTableName(cls),where);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		//结果处理
		T t = oneResultHandler(cls, rs);
		return  t;
	}
	
	public void close() { //关闭数据库连接
		if(null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	
	/**
	 * @Title: find
	 * @Description: TODO(查询单个对象)
	 * @param  <T>
	 * @return T 返回类型
	 * @throws
	 */
	public <T> T find(T object) {
		//根据情况查询数据
		String sql ="SELECT * FROM %s where %s";
		StringBuilder wheres = new 	StringBuilder();
		Field[] fieldArray = new Field[object.getClass().getDeclaredFields().length];
		int i = 0;
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if(field.get(object) == null) //判断该字段是否为空
					continue;
				wheres.append(field.getName()).append("=? AND ");
				fieldArray[i] = field;
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		wheres.append("1 = 1");
		sql = String.format(sql, ORMAnnoHelper.getTableName(object.getClass()),wheres);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//解析预处理语句中?参数并赋值
			sqlParamUitls(pstmt, sql, object,fieldArray);
			return (T) oneResultHandler(object.getClass(), pstmt.executeQuery());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**  
	 * @Title: sqlParamUitls
	 * @Description: TODO(设置预处理SQL语句的每个参数值  --原本反射的字段我是用数组的 不过因为局限性太高改成了数组
	 * 						只是预编译 其他处理留给调用方法的人自己做)
	 * @param sql
	 * @param obj
	 * @param fs
	 * @return
	 * @author Jy
	 * @date 2021-02-24 10:19:17 
	 */  
	public void sqlParamUitls(PreparedStatement pstmt,String sql,Object obj,Field[] fs) {
		int rows = 0;
		try {
			Field f = null;
			for (int i=0,len =fs.length; i< len; i++) {
				f = fs[i];
				if(f==null) {
					continue;
				}
				f.setAccessible(true);//设置私有可访问
				Class<?> type = f.getType();
				if(type == String.class) {
					pstmt.setString(i+1, String.valueOf(f.get(obj)));
				}else if(type == int.class || type == Integer.class) {
					pstmt.setInt(i+1,f.getInt(obj));
				}else if(type == double.class || type == Double.class) {
					pstmt.setDouble(i+1, f.getDouble(obj));
				}else if(type == Date.class) {
					Date date = (Date)f.get(obj);//java.util.Date
					pstmt.setDate(i+1,new java.sql.Date(date.getTime()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		DBSessionFactory factory = new DBSessionFactory();
		try {
			DBsession session = factory.openSession();
			List<User> list = factory.openSession().list(User.class);
			System.out.println(list.size());
			User us = new User();
			us.setId("9fb02a39b60347c7819bebf68db8e7d0");
			us.setUserName("jacker1");
			us.setPassword("1231515qweqr213");
			us.setNiceName("姜小鱼2121");
			us.setPhone("13523024606211211111111111");
			session.save(us);
//			session.update(us);
//			User user = session.getById(User.class, "9fb02a39b60347c7819bebf68db8e7d0");
//			System.out.println(user);
//			System.out.println(session.delete(User.class, "9fb02a39b60347c7819bebf68db8e7d0"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}