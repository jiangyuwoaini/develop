package com.jiangyu.xmlparser;

import java.util.List;

/**  
 * @ClassName: BeanInfo
 * @Description: beans.xml 文件中的bean标签的属性结构
 * @author Jy
 * @date 2021-01-31 12:22:49 
*/  
public class BeanInfo {
	private String id;
	private String clsName; //对应的是cls属性
	private String scope;
	private List<PropsInfo> props;
	
	public List<PropsInfo> getProps() {
		return props;
	}
	public void setProps(List<PropsInfo> props) {
		this.props = props;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClsName() {
		return clsName;
	}
	public void setClsName(String clsName) {
		this.clsName = clsName;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "BeanInfo [id=" + id + ", clsName=" + clsName + ", scope=" + scope + "]";
	}
}
