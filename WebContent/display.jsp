<%@page import="java.util.Collections"%>
<%@page import="util.DisplayUtils"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="model.DateUsed"%>
<%@page import="model.Days"%>
<%@page import="java.util.Set"%>
<%@page import="model.Slot"%>
<%@page import="model.Timetable"%>
<%@page import="controller.database.DatabaseInterface"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Display - <% out.print(session.getAttribute("display_mode")); %></title>
</head>
<body>

<%
	DatabaseInterface dbi = DatabaseInterface.getInstance();
	Timetable timetable = dbi.getTimetableByGroup(session.getAttribute("group").toString());
	if (timetable != null) {
		Set<Slot> slots = timetable.getTimetable().keySet();

		out.print("<table>");
		// Initialize our map
		Map<Days, List<Slot>> dayMap = new HashMap<Days, List<Slot>>();
		dayMap.put(Days.LUNDI, new LinkedList<Slot>());
		dayMap.put(Days.MARDI, new LinkedList<Slot>());
		dayMap.put(Days.MERCREDI, new LinkedList<Slot>());
		dayMap.put(Days.JEUDI, new LinkedList<Slot>());
		dayMap.put(Days.VENDREDI, new LinkedList<Slot>());
		// fill our lists
		for (Slot slot : slots) {
			dayMap.get(slot.getBeginning().getDay()).add(slot);
		}
		// sort our lists
		for (Days day : dayMap.keySet()) {
			Collections.sort(dayMap.get(day));
		}
		
		out.print("<tr><td>Lundi</td>");
		for (Slot slot : dayMap.get(Days.LUNDI)) {
			out.print(DisplayUtils.printSlot(slot));
		}
		out.print("</tr>");
		
		out.print("<tr><td>Mardi</td>");
		for (Slot slot : dayMap.get(Days.MARDI)) {
			out.print(DisplayUtils.printSlot(slot));
		}
		out.print("</tr>");
		
		out.print("<tr><td>Mercredi</td>");
		for (Slot slot : dayMap.get(Days.MERCREDI)) {
			out.print(DisplayUtils.printSlot(slot));
		}
		out.print("</tr>");
		
		out.print("<tr><td>Jeudi</td>");
		for (Slot slot : dayMap.get(Days.JEUDI)) {
			out.print(DisplayUtils.printSlot(slot));
		}
		out.print("</tr>");
		
		out.print("<tr><td>Vendredi</td>");
		for (Slot slot : dayMap.get(Days.VENDREDI)) {
			out.print(DisplayUtils.printSlot(slot));
		}
		out.print("</tr>");
	
		out.print("</table>");

	} else {
		out.print("<p>Error</p>");
	}
%>

</body>
</html>