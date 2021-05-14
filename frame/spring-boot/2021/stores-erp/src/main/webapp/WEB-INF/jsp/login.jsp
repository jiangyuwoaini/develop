<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="http://at.alicdn.com/t/font_368076_we3l46nomcp7gb9.css">
<link rel="stylesheet" href="../static/css/personal.css">
<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#subForm").click(function(){
			var form = document.getElementById("updateForm");
			form.submit();
		})
	})
</script>
<body>
<div align="center"> 
	<form id="updateForm" action="/Test1/login" method="post">
		<div>${errorMsg}</div>
		<table class="mytable">
			<tr>
				<td>账号</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><span id="subForm">提交</span></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>