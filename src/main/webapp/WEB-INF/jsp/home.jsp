<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<base href="<%=basePath%>">
<body>
	<h1>欢迎来到用户首页</h1>
	<a href="Test1/hello2">登陆页面</a>
	<a href="Test1/hello1">注册页面</a>
	
<%-- 	<a href="${pageContext.request.contextPath }upPersonal">注册页面</a> --%>
<!-- 	<input type="button" value="注册" onclick="window.location.href='upPersonal.jsp';"/> -->
</body>
</html>