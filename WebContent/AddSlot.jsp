<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     	 <!--java package import-->
<%@ page import="java.sql.*,java.util.*,model.*,java.util.Date" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="lightblue">
<center>

<!-- import image -->
<img src="img/JspPage.png">
<hr>

<h1><font color=yellow size=4>Please insert class information</font></h1>

	<!--
	private int id;
	private String name;
	
	private DateUsed beginning;
		private int hours;
		private int minutes;
		private Days day;
	
	private int duration;
	private Person teacher;
	private String type; //CM, TD etc.
	-->

<form action="SlotServlet?flag=addSlot" method="post">
<%
Date d = new Date();
int beginhours = d.getHours();
int beginminutes = d.getMinutes();
%>
	
	<table>
		<tr>
			<td bgcolor=pink>Class name</td>
			<td><input type="text" name="name" value="<%="Cour" %>"></td>			
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
			<td><input type="text" name="teachername" value="<%="Goeffon" %>"></td>			
		</tr>
		<tr>
			<td bgcolor=pink>type</td>
			<td><input type="text" name="type" value="<%="cc" %>"></td>			
		</tr>
		<tr>
			<td><input type="submit" value="Add Cours"/></td>
			<td><input type="reset" value="Reset"/></td>
		</tr>
	</table>
	
</form>

</center>
<hr>
</body>
</html>