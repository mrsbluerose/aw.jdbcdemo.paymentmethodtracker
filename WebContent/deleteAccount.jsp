<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil"%>


<%
	String id = request.getParameter("account_id");
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<%
	try {
		connection = ConnectionUtil.getConnection();
		statement = connection.createStatement();
		String sql = "SELECT * FROM account WHERE account_id=" + id;
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
%>

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
		<input type="hidden" name="id" 	value="<%=resultSet.getString("account_id")%>" /> 
		<pre>
			ID: <%=id%>
			Name: <%=resultSet.getString("account_name")%>
			Payment Method: <%=resultSet.getInt("payment_method_id")%>
		<input type="submit" name="action" value="delete" />
		</pre>
	</form>


	<%
		}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

</body>
</html>