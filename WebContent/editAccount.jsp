<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Account</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<h1>Edit Account</h1>
	<form action="accountController" method="post">
		<%
		String[] accountItems = (String[]) request.getAttribute("accountItems");
		%>
		<input type="hidden" name="accountID" value="<%=accountItems[0]%>">
		<pre>
		ID: <%=accountItems[0]%>
		Name: <input type="text" name="name" value="<%=accountItems[1]%>" />
		Payment Method: <input type="text" name="paymentMethod"	value="<%=accountItems[2]%>" />
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
		</pre>
	</form>


</body>
</html>