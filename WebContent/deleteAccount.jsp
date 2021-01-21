<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete Account</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<a href="index.html">Home</a>
	<h1>Delete Account</h1>
	<form action="accountController" method="post">
		<%
		String[] accountItems = (String[]) request.getAttribute("accountItems");
		%>
		<input type="hidden" name="id" 	value="<%=accountItems[0]%>" /> 
		<pre>
		ID: <%=accountItems[0]%>
		Name: <%=accountItems[1]%>
		Payment Method: <%=accountItems[2]%>
		<input type="submit" name="action" value="delete" />
		</pre>
	</form>

</body>
</html>