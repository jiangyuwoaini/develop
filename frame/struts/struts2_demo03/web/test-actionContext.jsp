<%--
  Created by IntelliJ IDEA.
  User: J y
  Date: 2021/5/29
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <h4>ActionContext获取值</h4>
      application:${applicationScope.applicationKey}
      <br><br>
      session: ${sessionScope.sessionKey}
      <br><br>
      request: ${requestScope.requestKey}
      <br><br>
      age:${parameters.age}
      name:${parameters.name}
  </body>
</html>
