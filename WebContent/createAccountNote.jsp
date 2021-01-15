<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
String id = request.getParameter("account_id");
String name = request.getParameter("account_name");
%>

<html>
<head>
<meta charset="UTF-8">
<title>Create Account Note</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Create Account Note</h1>
	<form action="accountController" method="post">
		<pre>
Account ID:  <%=id%>
Account Name: <%=name%>
Date: <input type="text" name="date" />
Note: <input type="text" name="note" />
<input type="hidden" name="action" value="create" />
<input type="submit" value="Save">
</pre>
	</form>
</body>
</html>