<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<form id="updateForm" action="/Test2/insertBuilding" method="post"
				enctype="multipart/form-data">
				<table class="mytable">
						<tr>
						<td>序号</td>
						<td><input type="text" name="id" /></td>
					</tr>
					<tr>
						<td>管桩号</td>
						<td><input type="text" name="bid" /></td>
					</tr>
				
					<tr>
						<td>长度(m)</td>
						<td><input type="text" name="bwidth" /></td>
					</tr>
					<tr>
						<td>所属桩扎号</td>
						<td><input type="text" name="bsid" /></td>
					</tr>
					<tr>
						<td>进场时间</td>
						<td><input type="text" name="btime"/></td>
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