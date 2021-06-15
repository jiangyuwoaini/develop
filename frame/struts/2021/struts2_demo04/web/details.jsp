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
	ProductName: <s:property value="[0].productName" />
	<br><br>
	
	ProductDesc: <s:property value="[1].productDesc" />
	<br><br>

	ProductPrice:${productPrice}
	<br/><br/>
	ProductPrice: <s:property value="[0].productPrice" />
	<br><br>
	ProductPrice: <s:property value="productPrice" />
	<br><br>
	<p>----------------------------------获取对象值----------------------------------</p>
	productName1:${sessionScope.product.productName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	productName2:<s:property value="#session.product.productName"/>
	<br/><br/>
	productName1:${requestScope.test.productName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	productName2:<s:property value="#request.test.productName"/>
	<br/><br/>
	<p>----------------------------------使用OGNL调用public类的静态字段或者静态方法----------------------------------</p>
	<s:property value="@java.lang.Math@PI"/>
	<br/><br/>
	<s:property value="@java.lang.Math@cos(0)"/>
	<!-- 调用对象栈进行赋值 -->
	<s:property value="setProductName('寒窗')"/>
	<br/><br/>
	productName:<s:property value="productName" />
	<br/><br/>
	<!-- 调用对象属性-->
	<%
		String[] names = new String[]{"aa","bb","cc","dd"};
		request.setAttribute("names",names);
	%>
	length:<s:property value="#attr.names.length" />
	<br/><br/>
	names[2]:<s:property value="#attr.names[1]"/>
</body>
</html>