<%@page import="java.util.Set"%>
<%@page import="model.Slot"%>
<%@page import="model.Timetable"%>
<%@page import="database.DatabaseInterface"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display - </title>
</head>
<body>

<%
	DatabaseInterface dbi = DatabaseInterface.getInstance();
	Timetable timetable = dbi.getTimetableByGroup(session.getAttribute("group").toString());
	if (timetable != null) {
		out.print(timetable.getTimetable().size());
		Set<Slot> slots = timetable.getTimetable().keySet();
		
		for (Slot slot : slots) {
			out.print(slot + "<br />");
		}
	} else {
		out.print("<p>Error</p>");
	}
%>

</body>
</html>