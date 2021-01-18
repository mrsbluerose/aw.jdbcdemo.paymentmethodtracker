<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="aw.jdbcdemo.paymentmethodtracker.util.ConnectionUtil"%>
<%
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<html>
<head>
<meta charset="UTF-8">
<title>Payment Methods</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Payment Methods</h1>
	<a href="createPaymentMethod.jsp">Create New</a>
	<a href="searchPaymentMethod.jsp">Search</a>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Expiration Date</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			try {
				connection = ConnectionUtil.getConnection();
				statement = connection.createStatement();
				String sql = "SELECT * FROM payment_method ORDER BY payment_method_id";
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>

		<tr>
			<td><%=resultSet.getInt("payment_method_id")%></td>
			<td><%=resultSet.getString("payment_method_name")%></td>
			<td><%=resultSet.getString("payment_method_description")%></td>
			<td><%=resultSet.getString("payment_method_exp_date")%></td>
			<td><a href="editPaymentMethod.jsp?payment_method_id=<%=resultSet.getInt("payment_method_id")%>">Edit</a></td>
			<td><a href="deletePaymentMethod.jsp?payment_method_id=<%=resultSet.getInt("payment_method_id")%>">Delete</a></td>
		</tr>
		<%
			}
				connection.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		%>
	</table>
</body>
</html>