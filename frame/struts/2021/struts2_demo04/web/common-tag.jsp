<%@ page import="com.lblz.struts.action.ognl.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lblz.struts.action.ognl.PersonComparator" %>
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
	<!-- 查看struts的值栈情况 -->
	<s:debug></s:debug>
	<h1>struts的s:property标签的使用</h1>
	<br/><br/>
	productName:<s:property value="productName" />
	<br/><br/>
	对于Map栈,打印request,session.application的某个属性值或某个请求参数的值
	<br/><br/>
	date:<s:property value="#session.date" />
	<br/><br/>
	参数值:<s:property value="#parameters.name[0]"/>

	<h1>struts的s:url标签的使用</h1>
	<br/><br/>
	<s:url value="/getProduct" var="url">
		<s:param name="productId" value="2002"></s:param>
	</s:url>
	s:url:创建一个url字符串:${url}
	<br/><br/>
	<s:url value="/getProduct" var="url2">
		<!-- 关于value值会自动的尽心OGNL解析 -->
		<s:param name="productId" value="productId"></s:param>
	</s:url>
	s:url:创建二个url字符串:${url2}
	<br/><br/>
	<s:url value="/getProduct" var="url3">
		<!--对于value值会自动的进行OGNL解析,若不希望进行OGNL解析,则使用单引号引起来!-->
		<s:param name="productId" value="'productId'"></s:param>
	</s:url>
	s:url:创建三个url字符串:${url3}
	<br/><br/>
	<!--构建一个请求action的地址-->
	<s:url action="testAction" namespace="/helloworld" method="save" var="url4"></s:url> $(ur14}
	s:url:创建四个url字符串:${url4}
	<br/><br/>
	<s:url value="testUrL" var="url5" includeParams= "all"></s:url>
	s:url:创建五个url字符串:${url5}
	<br/><br/>
	<h1>s:set:向page, request, session, application域对象中加入一个属性值</h1>
	<s:set name="productName" value="productName" scope="page"></s:set>
	productName: ${pageScope.productName}
	<br/><br/>
	<%
		Person person = new Person();
		person.setName("jy");
		person.setAge(10);
		request.setAttribute("person",person);
	%>
	<!-- 将person字段压入栈中 -->
	<s:push value="#request.person">
		${name}
	</s:push>
	<br/><br/>
	<s:if test="#request.person.age > 10">
		大于10
	</s:if>
	<s:else>
		小于或等于10.
	</s:else>
	<%
		List<Person> personArray = new ArrayList<>();
		personArray.add(new Person("aa",10));
		personArray.add(new Person("bb",20));
		personArray.add(new Person("cc",30));
		personArray.add(new Person("dd",40));
		request.setAttribute("persons",personArray);
	%>
	<br/><br/>
	迭代器排序
	<s:iterator value="#request.persons">
		${name} -${age}<br/>
	</s:iterator>
	<br/><br/>
	s:sort 可以对集合中的元素进行排序<br/>
	<%
		PersonComparator pc = new PersonComparator();
		request.setAttribute("comparator",pc);
	%>
	<s:sort comparator="#request.comparator" source="person" var="person2"></s:sort>
	<s:iterator value="#attr.person2">
		${name} -${age} <br/>
	</s:iterator>
	<br/><br/>
	s:date 可以对Date对象进行排版
	<s:date name="#session.date" format="yyyy-MM-dd hh:mm:ss" var="date2" />
	<br/><br/>
	date:${date2}
	<br/><br/>
	s:a标签的使用
	<s:iterator value="person">
		<!-- %{}强制OGNL解析 -->
		<s:a href="getPerson.action?name=%{name}">${name}</s:a>
	</s:iterator>
</body>
</html>