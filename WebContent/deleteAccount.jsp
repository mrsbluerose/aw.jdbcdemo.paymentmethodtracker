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

	<h1>Delete Account</h1>
	<form action="accountController" method="post">
		<%
		String[] accountItems = (String[]) request.getAttribute("accountItems");
		%>
		
		<pre>
		ID: <%=accountItems[0]%>
		Name: <%=accountItems[1]%>
		Payment Method: <%=accountItems[2]%>
		</pre>
		
		<input type="hidden" name="id" 	value="<%=accountItems[0]%>" /> 
		<input type="submit" name="action" value="delete" />
		
	</form>

</body>
</html>