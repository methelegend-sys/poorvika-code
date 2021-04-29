<%@page import="java.io.Console"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page import=" javax.servlet.http.HttpSession"%>
<% 
	String username  = (String)session.getAttribute("username");
	if(username!=null ){
		response.sendRedirect(request.getContextPath()+"/upload.jsp");
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>USER LOGIN</title>
</head>
<body>
<h1> LOGIN </h1>
<hr>

<form action="Validate" method="post">
Username : <input type="text" name = "username" >
<br>
<br>
Password : <input type="password" name="password" >
<br>
<br>
<hr>
<input type = "submit" value="Login">
</form>
</body>
</html>