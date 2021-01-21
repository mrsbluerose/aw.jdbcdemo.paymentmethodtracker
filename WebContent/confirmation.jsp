<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Confirmation</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<%
	String message = (String) request.getAttribute("message");
	%>
	<h1><%=message%></h1>
	<form action="accountController" method="post">
			<input type="hidden" name="action" value="list" />
			<input type="submit" value="OK">
	</form>


</body>
</html>