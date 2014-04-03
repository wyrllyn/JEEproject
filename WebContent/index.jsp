<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Index</title>
</head>
<body>
	<h1>Timetable index</h1>
	<jsp:useBean id="groups" class="model.Groups" />
	
	<s:form action="display" method="post">
		<s:select name="display_mode" list="{compact, summary, full}" />
		<s:select name="group" list="<% 
			for (String group : groups.getGroups()) {
				out.println("<s:option value=\""+ group +"\">" + group + "/>");
			}
			%>" />
		<input type="submit" value="GO!" />
	</s:form>
</body>
</html>