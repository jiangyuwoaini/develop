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
<title>个人资料</title>
</head>
<base href="<%=basePath%>">
<style>
	* {
		color: green;
	}
	#jy{
		background-color: purple;
	}
</style>
<link rel="stylesheet" href="http://at.alicdn.com/t/font_368076_we3l46nomcp7gb9.css">
<link rel="stylesheet" href="../static/css/personal.css">
<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#subForm").click(function() {
			var form = document.getElementById("updateForm");
			form.submit();
		})
	})
</script>
<body>
	<div id="jy" class="platSafety">
		<div class="right">
			<div class="reght_top" align="center">用户注册</div>
			<div class="clear"></div>
			<form id="updateForm" action="/Test2/updateh1/${user.id}" method="post"
				enctype="multipart/form-data">
				<table class="mytable">
					<tr>
						<td>账号</td>
						<td><input type="text" name="username" value="${user.username}"/></td>
					</tr>
					<tr>
						<td>真实姓名</td>
						<td><input type="text" name="name" value="${user.name}"/></td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<input type="radio" name="sex" value="男" checked="${user.sex}"/>男 
							<input type="radio" name="sex" value="女"  />女
						</td>
					</tr>
					<tr>
						<td>身份证号</td>
						<td><input type="text" name="identity" value="${user.identity}"/></td>
					</tr>
					<tr>
						<td>邮箱号</td>
						<td><input type="text" name="email" value="${user.email}"/></td>
					</tr>
					<tr>
						<td>手机号</td>
						<td><input type="text" name="mobile" value="${user.mobile}"/></td>
					</tr>
					<tr>
						<td>照片</td>
						<td><img src="${user.picture}"><input type="file" name="file" /></td>
					</tr>
					<tr>
						<td colspan="2"><span id="subForm">提交</span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>