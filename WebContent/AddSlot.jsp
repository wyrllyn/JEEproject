<%@page import="controller.database.DatabaseInterface"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     	 <!--java package import-->
<%@ page import="java.sql.*,java.util.*,model.*,java.util.Date" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Slot</title>
</head>
<body bgcolor="lightblue">
<center>

<!-- import image -->
<img src="img/JspPage.png">
<hr>

<h1><font color=yellow size=4>Please insert class information</font></h1>

<form action="SlotServlet?flag=addSlot" method="post">
<%
DatabaseInterface dbi = DatabaseInterface.getInstance();
DateUsed d = new DateUsed();
int beginhours = d.getHours();
int beginminutes = d.getMinutes();

Person teacher = dbi.getTeacherById(1);
%>
	
	<table>
		<tr>
			<td bgcolor=pink>Class name</td>
			<td><input type="text" name="name" value="<%="NAME" %>"></td>			
		</tr>
		<tr>
			<td bgcolor=silver>Begin day</td>
			<td><input type="text" name="beginday" value="<%=Days.LUNDI %>"></td>			
		</tr>
		<tr>
			<td bgcolor=pink>Begin hour</td>
			<td><input type="text" name="beginhours" value="<%=beginhours %>"></td>			
		</tr>
		<tr>
			<td bgcolor=silver>Begin minutes</td>
			<td><input type="text" name="beginminutes" value="<%=beginminutes %>"></td>			
		</tr>
		<tr>
			<td bgcolor=pink>duration</td>
			<td><input type="text" name="duration" value="<%=0 %>"></td>			
		</tr>
		<tr>
			<td bgcolor=silver>teacher</td>
			<td><input type="text" name="teachername" value="<%=teacher.getName() %>"></td>			
		</tr>
		<tr>
			<td bgcolor=pink>type</td>
			<td><input type="text" name="type" value="<%="CM" %>"></td>			
		</tr>
		<tr>
			<td><input type="submit" value="Add Cours"/></td>
			<td><input type="reset" value="Reset"/></td>
		</tr>
	</table>	
</form>
<a href="ModifierTable.jsp">Back to modifier page</a>
</center>
<hr>
</body>
</html>