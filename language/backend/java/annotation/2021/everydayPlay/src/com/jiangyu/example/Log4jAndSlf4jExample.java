package com.jiangyu.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * @ClassName: Log4jAndSlf4jExample
 * @Description: logger和slf4j日志记录器的用法
 * @author Jy
 * @date 2021-01-18 11:09:16 
*/  
//每个Logger都被了一个日志级别（log level），用来控制日志信息的输出。日志级别从高到低分为：
//A：off         最高等级，用于关闭所有日志记录。
//B：fatal       指出每个严重的错误事件将会导致应用程序的退出。
//C：error      指出虽然发生错误事件，但仍然不影响系统的继续运行。
//D：warm     表明会出现潜在的错误情形。
//E：info      一般和在粗粒度级别上，强调应用程序的运行全程。
//F：debug     一般用于细粒度级别上，对调试应用程序非常有帮助。
//G：all           最低等级，用于打开所有日志记录。
public class Log4jAndSlf4jExample {
    private static final Logger logger = LoggerFactory.getLogger(Log4jAndSlf4jExample.class.getName());// slf4j日志记录器
    public static void main(String[] args) {
		try {
			logger.debug("线程开始");
			System.out.println("hello world");
			Thread.sleep(1 *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test1();
	}
    public static void test1() {
    	logger.info("test方法");
    	for (int i = 0; i < 3; i++) {
			logger.debug("这是第{}条记录",i);
		}
    	//用\转义
    	logger.debug("Set \\{} differs from{}","3");
    	//多个参数(可变参数)
    	logger.info("info:多个占位符{},{},{},{}",1,2,3,4);
    	//多个参数(可变参数)
    	logger.error("error:多个参数,{},{},{},{}",1,2,3,4);
    }
}
