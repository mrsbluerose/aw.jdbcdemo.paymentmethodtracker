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
<%@page import="aw.jdbcdemo.paymentmethodtracker.model.Account"%>

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
<title>Edit Account</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Edit Account</h1>
	<form action="accountController" method="post">
		<input type="hidden" name="id" value="<%=resultSet.getInt("account_id")%>">
		<pre>
		ID: <%=resultSet.getInt("account_id")%>
		Name: <input type="text" name="name" value="<%=resultSet.getString("account_name")%>" />
		Payment Method: <input type="text" name="paymentMethod"	value="<%=resultSet.getInt("payment_method_id")%>" />
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
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