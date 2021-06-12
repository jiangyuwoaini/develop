package com.jiangyu.xmlparser;
/**  
 * @ClassName: PropsInfo
 * @Description: 对应beans.xml property属性标签的结构
 * @author Jy
 * @date 2021-01-31 12:24:17 
*/  
public class PropsInfo {
	private String name;
	private String value;
	private String type;
	
	public PropsInfo(String name, String value, String type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PropsInfo [name=" + name + ", value=" + value + ", type=" + type + "]";
	}
}
