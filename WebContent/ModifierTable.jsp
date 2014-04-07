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




<a href="login.jsp">Back to login</a>
<br/>

<a href="display.jsp">Show result</a>
<br/>
</center>
<h1>Modifier l'emploi du temps</h1>
<%

	
	
	
	
	//调用UserBeanCl的方法，完成分页显示
	//UserBeanCl ubc=new UserBeanCl();
	//ArrayList al=ubc.getUsersByPage(pageNow);
	//要现实的用户信息从request中取
	//ArrayList al=(ArrayList)request.getAttribute("result");
%>


<table border="1">

<tr bgcolor="pink">
	<td>time</td>
	<td>duration</td>
	<td>id</td>
	<td>name</td>
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
			<td><%=s.getBeginning() %></td>
			<td><%=s.getDuration() %></td>
			<td><%=s.getId() %></td>
			<td><%=s.getName() %></td>
			<td><%=s.getType() %></td>
					
		</tr>
		<% 
	}
%>

</table>


<% 
//int date=Integer.parseInt((String)request.getAttribute("date"));
%>

</body>
</html>