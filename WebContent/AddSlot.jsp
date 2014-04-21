<%@page import="util.DisplayUtils"%>
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
<img src="resources/img/JspPage.png">
<hr>

<h1><font color=green size=4>Please insert class information</font></h1>

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
			<td bgcolor=silver>Day</td>
			<td><select>
				<% out.print(DisplayUtils.getDaysSelectOptions()); %>
			</select></td>			
		</tr>
		<tr>
			<td bgcolor=pink>Hour</td>
			<td><input type="text" name="beginhours" value="<%=beginhours %>"></td>			
		</tr>
		<tr>
			<td bgcolor=silver>Minutes</td>
			<td><input type="text" name="beginminutes" value="<%=beginminutes %>"></td>			
		</tr>
		<tr>
			<td bgcolor=pink>duration</td>
			<td><input type="text" name="duration" value="<%=60 %>"></td>			
		</tr>
		<tr>
			<td bgcolor=silver>teacher</td>
			<td><select name="teachername">
				<% out.print(DisplayUtils.getTeacherSelectOptions()); %>
			</select></td>			
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