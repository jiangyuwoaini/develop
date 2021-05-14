package com.lblz.mvc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import com.sun.xml.internal.stream.events.EndElementEvent;

/**
 * @ClassName: ActionXmlParser
 * @Description: TODO(业务控制器配置中心xml解析器)
 * @author J y
 * @date 2021年3月6日
 *
 */
public class ActionXmlParser {
	private HashMap<String, Action> actions;
	//解析actions.xml
	public HashMap<String, Action> parse(InputStream is){
		actions = new HashMap<String, Action>();
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(is, new DefaultHandler() {
				private Action action = null;
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					// TODO Auto-generated method stub
					if("action".equals(qName)) {
						action = new Action();
						action.setPath(attributes.getValue("path"));
						action.setName(attributes.getValue("name"));
						action.setResults(new HashMap<>());
					}else if("result".equals(qName)) {
						String redirect = attributes.getValue("redirect");
						action.getResults()
							.put(attributes.getValue("name"),
									new Result(attributes.getValue("name"),
											attributes.getValue("path"),
											redirect == null? false : Boolean.parseBoolean(attributes.getValue("redirect"))));
					}
				}
				
				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					// TODO Auto-generated method stub
					if("action".equals(qName)) {
						actions.put(action.getPath(), action);
						action = null;//gc
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actions;
	};
	
	
	
	
	//对应的是<action>
	public static class Action{
		private String path;
		private String name;
		private HashMap<String, Result> results;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public HashMap<String, Result> getResults() {
			return results;
		}
		public void setResults(HashMap<String, Result> results) {
			this.results = results;
		}
	}
	//对应的是<result>
	public static class Result{
		private String name;
		private String path;
		private boolean redirect;
		
		public Result(String name, String path, boolean redirect) {
			super();
			this.name = name;
			this.path = path;
			this.redirect = redirect;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public boolean isRedirect() {
			return redirect;
		}
		public void setRedirect(boolean redirect) {
			this.redirect = redirect;
		}
	}
}
