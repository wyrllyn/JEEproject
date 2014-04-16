<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!--java package import-->
<%@ page import="java.sql.*,java.util.*,model.*,database.*" %>  
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
	<td>Beginning</td>
	<td>duration</td>
	<td>teacher</td>
	<td>type</td>
	<td>modifier</td>
	<td>delete</td>
</tr>

<%
	String[] color={"silver","pink"};
	//Slot[] slots = new Slot[100];
	//DatabaseInterface di = new DatabaseInterface();
	DatabaseInterface di = DatabaseInterface.getInstance();
	
	di.connect();
	List<Slot> slots = di.getSlots();
	for(int i=0;i<slots.size();i++){
		 Slot slot = slots.get(i);
		%>
		<tr bgcolor="<%=color[i%2]%>">
			<td><%=slot.getId() %></td>
			<td><%=slot.getName() %></td>
			<td><%=slot.getBeginning().getDay()+" "
			+slot.getBeginning().getHours()+"H "
			+slot.getBeginning().getMinutes()+"Min"%></td>
			<td><%=slot.getDuration()+"Min" %></td>
			<td><%=slot.getTeacher().getName() %></td>
			<td><%=slot.getType() %></td>	
			<td><a href="ModifierSlot.jsp?id=<%=slot.getId() %>
			&name=<%=slot.getName() %>
			&beginday=<%=slot.getBeginning().getDay() %>
			&beginhours=<%=slot.getBeginning().getHours() %>
			&beginminutes=<%=slot.getBeginning().getMinutes() %>
			&duration=<%=slot.getDuration() %>
			&teacher=<%=slot.getTeacher() %>
			&teachername=<%=slot.getTeacher().getName() %>
			&type=<%=slot.getType() %>">Modifier Slot</a></td>		
			
			<td><a href="SlotServlet?flag=delSlot&id=<%=slot.getId()%>">Delete slot</a></td>			
		</tr>
		
		<% 
	}
	di.disconnect();
%>
<a href="AddSlot.jsp">Add slot</a>
</table>



</body>
</html>