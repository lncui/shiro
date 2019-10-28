<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h4>Login Page</h4>
 <form action="/passport/signin" method="post">
     username：<input type="text" name="username">
     <br><br>
     password：<input type="password" name="password">
     <br><br>
     <input type="submit" value="Submit">
 </form>
<h4 class="text-center" style="color:red">${errMsg}</h4>
</body>
</html>
