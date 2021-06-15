<%@ page import="com.lblz.struts.action.ognl.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lblz.struts.action.ognl.PersonComparator" %>
<%@ page import="com.lblz.struts.action.ognl.City" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:debug />
	<!--
	表单标签:
		1,使用和html的form标签的感觉差不多
		2.struts2的form标签会生成一个table,以进行自动的排版
		3,可以对表单提交的值进行回显!!,从栈顶对象开始匹配属性,并把匹配的属性值赋到对应的标签的value中,若栈顶对象没有对应的属性,则依次向下找相对应的属性
	-->
	<%
		List<City> cities = new ArrayList<>();
		cities.add(new City(1001,"河南"));
		cities.add(new City(1002,"周口"));
		cities.add(new City(1003,"项城市"));
		request.setAttribute("cities",cities);
	%>
	<s:form action="save">
		<s:textfield name="userName" label="userName" />
		<s:password name="password" label="password" />
		<s:textarea name="desc" label="Desc" />
		<s:checkbox name="married" label="Married"/>
		<s:radio name="gender" list="#{'1':'Male','0':'Female'}" label="Gender"/>
		<s:checkboxlist list="#request.cities" listKey="seq"  listValue="cityName" label="City" name="city"/>
		<s:select list="{11,12,13,14,15,16,17,18,19,20}"
			headerKey=""
			headerValue="请选择"
			name="age"
			label="Age">
			<!--
				s:optgroup可以用作s:select的子标签,用于显示更多的下拉框注意:必须有键值对,而不能使用一个集合,让其值即作为键,又作为值.
				-->
			<s:optgroup label="21-31" list="#{21:21,222:333}" />
			<s:optgroup label="31-40" list="#{31:31}" />
		</s:select>
		<s:submit></s:submit>
	</s:form>
</body>
</html>