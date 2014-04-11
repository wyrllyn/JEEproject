<%@page import="database.DatabaseInterface"%>
<%@page import="model.Groups"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Index</title>
</head>
<body>
	<h1>Timetable index</h1>
	<jsp:useBean id="databaseInterface" class="database.DatabaseInterface" />
	<% databaseInterface = DatabaseInterface.getInstance(); %>
	<%
		out.println("<p>");
		out.println("Rooms: " + databaseInterface.getRooms());
		out.println("Slots: " + databaseInterface.getSlots());
		out.println("</p>");
	%>
	<form action="display" method="post">
		<select name="display_mode">
			<option value="compact">Compact</option>
			<option value="summary">Summary</option>
			<option value="full">Full</option>
		</select>
		<select name="group">
			<%
			for (String group : databaseInterface.getGroupList()) {
				out.println("<option value=\""+ group +"\">" + group + "</option>");
			}
		 	%>
		</select>
		<input type="submit" value="GO!" />
	</form>
</body>
</html>