package com.jiangyu.xmlparser;
/**  
 * @ClassName: FactoryBuilder
 * @Description: 1.设计工厂(容器类)构造器 
 * 				 2.创建 获取Bean的工厂类对象
 * 				 2.1)根据id来获取bean的实例(反射)
 * 				 2.2)注入实体的属性值
 * @author Jy	
 * @date 2021-01-31 02:52:07 
*/

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FactoryBuilder {
	//存放所有bean标签信息
	public  HashMap<String, BeanInfo> beanMap;
	private static FactoryBuilder factoryBuilder;
	public  Factory newFactory() {
		return new Factory();
	}
	public static FactoryBuilder getInstance(String xmlPath) {
		if(factoryBuilder == null) {
			return new FactoryBuilder(xmlPath);
		}
		return null;
	}
	
	private FactoryBuilder(String xmlPath) {
		try {
			//SAX解析中核心的类
			//SAXparserFactory 解析器的工厂类
			//SAXParser 解析器类
			//DefaultHandler 解析过程中的事件回调类
			// XML解析事件(5个)
			//文档开始:startDocument()
			//文档结束: endDocument()
			//标签开始: startElement()
			//标签结束: endElement(...)
			//文本标签: characters(char[] ch,int start,int length)
			
			File xmlFile = new File(xmlPath);
			if(!xmlFile.exists())
				throw new RuntimeException("文件不存在,请核实xml文件的路径");
			//1.创建 解释器工厂类对象
			SAXParserFactory factory = SAXParserFactory.newInstance();
			//2.创建Saxparser解释器类对象
			SAXParser parser = factory.newSAXParser();
			//3.开始解析xml文件
			parser.parse(new FileInputStream(xmlPath), new DefaultHandler() {
				private BeanInfo beanInfo;
				@Override
				public void startDocument() throws SAXException {
					//文档开始解析
					beanMap = new HashMap<>();
				}
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					//标签开始
					if("bean".equals(qName)) {
						beanInfo = new BeanInfo();
						beanInfo.setId(attributes.getValue("id"));
						beanInfo.setScope(attributes.getValue("class"));
						beanInfo.setClsName(attributes.getValue("scope"));
						beanInfo.setProps(new ArrayList<PropsInfo>());
					}else if("property".equals(qName)) {
						//读取property标签的属性值,并添加到bean标签对应的对象中
						beanInfo.getProps().add(new PropsInfo(attributes.getValue("name"), attributes.getValue("value"), attributes.getValue("type")));
					}
				}
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					//标签结束
					if("bean".equals(qName)) {//bean结束标签
						//将解析完的bean标签对象添加到map中
						beanMap.put(beanInfo.getId(), beanInfo);
						beanInfo = null; //如果不置为null会有一个强引用
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class Factory{
		//根据id获取bean类对象
		public Object getBean(String id)  {
			try {
				BeanInfo beanInfo = beanMap.get(id);
				if(beanInfo == null ) return null;
				//通过java反射实体化Bean标签的class属性指定的类对象(字节码对象)
				Class<?> cls = Class.forName(beanInfo.getScope());//beanInfo.getClsName();
				//实例化Class
				Object object = cls.newInstance();
				//读取property所有的标签,将标签中的属性值注入到实例化class的对象中
				for (PropsInfo propsInfo : beanInfo.getProps()) {
					//property标签的属性名作为object对象的字段名称
					Field field = cls.getDeclaredField(propsInfo.getName());
					field.setAccessible(true); //可访问性为true,才可以注入新的值
					//向字段中注入属性值
					if(propsInfo.getType().equals("long"))
						field.set(object,Long.parseLong(propsInfo.getValue()));
					else if(propsInfo.getType().equals("string"))
						field.set(object,propsInfo.getValue());
				}
				return object; //返回对象
			} catch (Exception e) { 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
