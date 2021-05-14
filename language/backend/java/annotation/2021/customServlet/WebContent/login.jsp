		<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>屡败屡战的首页</title>
</head>
<body>
	<h1>login page</h1>
	<form action="login.do">
		<label>用户名</label>
		<input type="text" name="userName" value="${param.userName}"/>
		<br/>
		<label>口令</label>
		<input type="password" name="password" />
		<button>登陆</button>
	</form>
	<div style="color:red">
		${error}
	</div>
</body>
</html>