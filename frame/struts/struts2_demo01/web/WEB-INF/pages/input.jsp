<%--
  Created by IntelliJ IDEA.
  User: J y
  Date: 2021/5/27
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
      <form action="product-save.action" method="post">
        ProductName: <input type="text" name="productName"/>
        <br><br>
        ProductDesc: <input type="text" name="productDesc"/>
        <br><br>
        ProductPrice: <input type="text" name="productPrice" />
        <br><br>
        <input type="submit" value="Submit"/>
        <br><br>
      </form>
  </body>
</html>
