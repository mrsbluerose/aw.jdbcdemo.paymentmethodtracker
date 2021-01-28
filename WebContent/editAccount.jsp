<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String[] accountItems = (String[]) request.getAttribute("accountItems");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Account</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<h1>Edit Account</h1>
	
	<!-- Form to enter new account name or payment method id (pre-filled with existing data) -->
	<form action="accountController" method="post">
		<pre>
		ID: <%=accountItems[0]%>
		Name: <input type="text" name="accountName" value="<%=accountItems[1]%>" />
		Payment Method: <input type="text" name="paymentMethodID"	value="<%=accountItems[2]%>" />
		<input type="hidden" name="accountID" value="<%=accountItems[0]%>">
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
		</pre>
	</form>

</body>
</html>