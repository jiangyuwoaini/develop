<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
div table tr td img{
	width: 50px;
	height: 50px;
}
h1.jy{
	color:#00F;
}
#xh{
	background-color: yellow;
}
#gzh{
	background-color: red;
}
#cd{
	background-color: green;
}
#ss{
	background-color: blue;
}
#jtime{
	background-color: red;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<base href="<%=basePath%>">
<body>

	<!-- Table goes in the document BODY -->
	<div  align="center">
	<h1 id="jy">用户信息</h1>
	<h1>${msg}</h1>
		<table class="gridtable">
			<tr>
				<th id="xh">序号</th>
				<th id="gzh">管桩号</th>
				<th id="cd">长度(m)</th>
				<th id="ss">所属桩扎号</th>
				<th id="jtime">进场时间</th>
			</tr>
			<c:forEach items="${list}" var="list" varStatus="status">
				<tr>
					<td>${list.id}</td>
					<td>${list.bid}</td>
					<td>${list.bwidth}</td>
					<td>${list.bsid}</td>
					<td>${list.btime}</td>
<%-- 					<td><a href="/Test2/deleteUser/${}">删除用户</a></td> --%>
<%-- 					<td><a href="/Test2/updateUser/${}">修改用户</a></td> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>