<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: J y
  Date: 2021/5/30
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <a href="UserAction-save.do?name=save">save</a>
    <br/><br/>
    <a href="UserAction-update.do?name=update">update</a>
    <br/><br/>
    <a href="login-ui.do">栈值</a>
    <br/><br/>
    <a href="testTag.do?name='姜煜'">通用标签的使用</a>
    <%
      session.setAttribute("date",new Date());
    %>
    <br/><br/>
    <form action="testTag.action">
      <input type="text" name="username"/>
      <input type="submit" value="submit" />
    </form>
  </body>
</html>
