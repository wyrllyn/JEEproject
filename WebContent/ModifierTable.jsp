<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <!--java package import-->
<%@ page import="java.sql.*,java.util.*,model.*,controller.database.*" %>  
<%@ page import="model.DateUsed" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier Table</title>
<style type="text/css">
#customers
  {
  font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  width:100%;
  border-collapse:collapse;
  }

#customers td, #customers th 
  {
  font-size:1em;
  border:1px solid #98bf21;
  padding:3px 7px 2px 7px;
  }

#customers th 
  {
  font-size:1.1em;
  text-align:left;
  padding-top:5px;
  padding-bottom:4px;
  background-color:#A7C942;
  color:#ffffff;
  }

#customers tr.alt td 
  {
  color:#000000;
  background-color:#EAF2D3;
  }
</style>
</head>
<body>


<center>



<h1>Modifier l'emploi du temps</h1>



<table id="customers">

<tr>
	<th>id</th>
	<th>name</th>
	<th>Beginning</th>
	<th>duration</th>
	<th>teacher</th>
	<th>type</th>
	<th>modifier</th>
	<th>delete</th>
</tr>

<%
	
	//Slot[] slots = new Slot[100];
	//DatabaseInterface di = new DatabaseInterface();
	DatabaseInterface di = DatabaseInterface.getInstance();
	
	di.connect();
	List<Slot> slots = di.getSlots();
	String[] type={null,"alt"};
	for(int i=0;i<slots.size();i++){
		 Slot slot = slots.get(i);
		%>
		<tr class="<%=type[i%2]%>">
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

</table>
<hr>
<a href="AddSlot.jsp">Add slot</a>
<br/>
<a href="login.jsp">Back to login</a>
<br/>
<a href="index.jsp">Show result</a>
<br/>
</center>
</body>
</html>