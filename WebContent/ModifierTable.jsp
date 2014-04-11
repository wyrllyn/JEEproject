<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!--java package import-->
<%@ page import="java.sql.*,java.util.*,model.*" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier Table</title>
</head>
<body>


<center>

<a href="login.jsp">Back to login</a>
<br/>

<a href="display.jsp">Show result</a>
<br/>
</center>
<h1>Modifier l'emploi du temps</h1>



<table border="1">

<tr bgcolor="pink">
	<td>id</td>
	<td>name</td>
	<td>time</td>
	<td>duration</td>
	<td>teacher</td>
	<td>type</td>
	<td>modifier</td>
	<td>delete</td>
</tr>

<%
	String[] color={"silver","pink"};
	for(int i=0;i<5;i++){
		 Slot s = new Slot();
		%>
		<tr bgcolor="<%=color[i%2]%>">
			<td><%=s.getId() %></td>
			<td><%=s.getName() %></td>
			<td><%=s.getBeginning() %></td>
			<td><%=s.getDuration() %></td>
			<td><%=s.getTeacher().getName() %></td>
			<td><%=s.getType() %></td>			
			<td><a href="ModifierSlot.jsp?id=<%=s.getId() %>&name=<%=s.getName() %>&beginning=<%=s.getBeginning() %>&duration=<%=s.getDuration() %>&teacher=<%=s.getTeacher() %>&type=<%=s.getType() %>">Modifier Slot</a></td>
			<td><a href="SlotServlet?flag=delSlot&id=<%=s.getId()%>">Delete slot</a></td>			
		</tr>
		<% 
	}
%>

</table>



</body>
</html>