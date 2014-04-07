<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body bgcolor="#11E8FA">
<center>
<hr>
<font color=orange size=4>User Login</font><br/>
<form action="LoginServlet" method="post">
<font color=red size=4>UserName：</font><input type="text" name="username"><br/>
<font color=red size=4>PassWord：</font><input type="password" name="password"><br/>
<input type="submit" value="login">
<input type="reset" value="reset">
</center>
</body>
</html>