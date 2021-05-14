package com.lblz.mvc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lblz.dao.base.Dao;
import com.lblz.mvc.action.SupportAction;
import com.lblz.mvc.servlet.ActionXmlParser.Action;
import com.lblz.mvc.servlet.ActionXmlParser.Result;
import com.lblz.orm.DBSessionFactory;
import com.lblz.orm.DBsession;

/**  
 * @ClassName: DispatcherServlet
 * @Description: TODO(自定义MVC框架的核心控制器)
 * @author Jy
 * @date 2021-02-24 11:11:28 
*/  
/*4,自定义MVC框架
1)核心控制的实现基于Servlet,实现核心控制器
(DispatcherServlet),其功能如下:
	a.获取请求的路径
	b.解析控制器配置中心
	c.获取请求对应的处理类
	d.执行处理类的方法,实现业务处理
	e.根据处理的结果响应给页面
复习Servlet的技术点
1) Serlvet的工作原理
1,根据请求从Web容器中获取到对应的Servlet处理类。
web.xml
-------------------------------
<servlet>
	<servlet-name>xxx</..>
	<servlet-class>com..</..> 
</servlet>
<servlet-mapping>
	<servlet-name>xxx</..>
	<url-pattern>/login</..>
</servlet-mapping>
2,第一次请求会实例化Servlet类,同时会调用init(ServletConfig),在init方法中可以通过SerlvetConfig来获取初始化参数值。
//扩展:在HttpServlet的init(ServletConfig)方法中会调用它的init()方法。
3,2如果第一次调用会在此执行这一步,如果是非第一次调用,则直接调用service(ServletRequest , ServletResponse)
方法在这方法中,通过request对象的getMethod()来获取请求的方法(GET, POST, DELETE,HEAD,PUT..)根据请求方法来执行对应的doxxx ()来处理具体的业务功能
4,在doxxx()方法中可以通过request来获取请求数据,再调用某-Model的业务处理方法,将处理的结果通过Reponse对象返回给请求端(响应)
5,在Servlet结束时,会调用destroy()来回收具体业务处理过程中使用资源。

自定义MVC中的核心控制器DispatcherServlet
确定请求处理的路径( *.do )
业务控制器的基类SupportAction
		实现至少几个字段:
		 	request, session, xxForm
		至少一个方法: String execute()
解析业务控制器的配置文件actions.xml
	前提:将自定义的业务控制器注册到actions.xml
从配置文件中获取指定path路径业务控制器类a.实例化控制器
	a.实例化控制器
	b.将request和session注入到它的父类对应的字段上面
	c.执行execute()方法
	d.根据execute()返回的结果视图的名称,跳转页面(forward/redirect)*/
public class DispatcherServlet extends HttpServlet{
	private String charset = "";
	HashMap<String, Action> actions = null;


	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//super.init(config); //他会调用父类的init
		charset = config.getInitParameter("charset");
		System.out.println("--init-config--");
		String configPath = config.getInitParameter("config");
		if(configPath.startsWith("classpath:")) { //测试字符串是否以此前缀开始
			configPath = configPath.replace("classpath:", "");
			//web项目获取class目录下的资源时，需要使用ClassLoader的成员getResourcesXxx();方法
			InputStream is = DispatcherServlet.class.getClassLoader().getResourceAsStream(configPath);
			//解析业务控制器的配置中心xml文件
			actions = new ActionXmlParser().parse(is);
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
//	@Override
//	public void init() throws ServletException {
//		System.out.println("--init--");
//	}

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--service--http--"+req.getMethod());
		String requestURI = req.getRequestURI();//获取全路径
		String contextPath = req.getContextPath(); //获取项目上下文路径
		requestURI = requestURI.replace(contextPath, "");
		Action action = actions.get(requestURI); //获取请求路径
		if(action == null) {
			req.getRequestDispatcher("/error.html").forward(req, resp);
			return;
		}
		try {
			DBsession session = new DBSessionFactory().openSession();
			Class<? extends SupportAction> actionClz = (Class<SupportAction>) Class.forName(action.getName());
			SupportAction sAction = actionClz.newInstance(); //业务控制器对象
			//向业务控制器中注入request和session
			Field reqField = actionClz.getSuperclass().getDeclaredField("request"); //从父类中拿到字段
			reqField.setAccessible(true);
			reqField.set(sAction,req);
			sAction.getParams();//将request的参数都给存储起来
			Field sessionField = actionClz.getSuperclass().getDeclaredField("session"); //从父类中拿到字段
			sessionField.setAccessible(true);
			sessionField.set(sAction, req.getSession());
			//获取业务控制中的字段
			Field[] fs = actionClz.getDeclaredFields();
			for (Field field : fs) {
				field.setAccessible(true); 
				//判断字段的类型是否实现了Dao接口
				if(field.getType().getInterfaces()!=null 
						&& field.getType().getInterfaces().length>0 
						&&field.getType().getInterfaces()[0] == Dao.class) {
					//获取Dao的构造方法,方法参数类型是DBsession类
					Constructor<?> constructor = field.getType().getConstructor(DBsession.class);
					//通过构造方法实例化类对象
					Object dao = constructor.newInstance(session);
					field.setAccessible(true);
					field.set(sAction, dao);
				}else if(field.getType() == String.class){
					//普通字段
					//从请求中获取数据
					field.set(sAction, req.getParameter(field.getName()));
				}else {
					//Form实体类的字段
					field.set(sAction, sAction.parseParams4Form(field.getType() ));
				}
			}
			//执行业务控制器类的方法 -execute() 
			String resultName = sAction.execute();
			//根据处理方法返回的结果名称,获取到result的path中
			Result result = action.getResults().get(resultName);
			String resultPath = result.getPath();
			if(result.isRedirect()) { //相应方式 是否为重定向
				resp.sendRedirect(resultPath);
			}else {
				req.getRequestDispatcher(resultPath).forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		if("/home.do".equals(requestURI)) {
//			req.getRequestDispatcher("index.html").forward(req, resp);
//		}
////		if(!requestURI.endsWith(".do")) {
////			return;
////		}
//		System.out.println("--service--http--"+req.getMethod());
//		resp.setContentType("text/html;charset="+charset);
//		resp.getWriter()
//			.append("<h3>hello DispatcherServlet</h3>")
//			.append("<ol>")
//			.append("<li>屡败屡战</li>")
//			.append("<li>坚持自我</li>")
//			.append("</ol>");
	}
}
