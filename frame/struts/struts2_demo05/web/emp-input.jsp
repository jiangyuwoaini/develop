<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% 
		request.setAttribute("theme", "xhtml");
	%>
	
	<s:form action="emp-save">
		
		<s:textfield name="name" label="Name"></s:textfield>
		<s:password name="password" label="Password"></s:password>
	
		<s:radio name="gender" list="#{'1':'Male','0':'Female' }" label="Gender"></s:radio>
	
		<s:select list="#request.depts" listKey="deptId"
			listValue="deptName" name="dept" label="Department"></s:select>
		<s:checkboxlist list="#request.roles" listKey="roleId"
			listValue="roleName" name="roles" label="Role"></s:checkboxlist>
	
		<s:textarea name="desc" label="Desc"></s:textarea>
		<s:submit></s:submit>
		
	</s:form>
	
</body>
</html>