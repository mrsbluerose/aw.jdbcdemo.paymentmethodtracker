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
<%@page import="aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod"%>
<%
	PaymentMethod paymentMethod = new PaymentMethod();
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
				String sql = "SELECT * FROM payment_method";
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>

		<tr>
			<td><%=resultSet.getInt("id")%></td>
			<td><%=resultSet.getString("name")%></td>
			<td><%=resultSet.getString("description")%></td>
			<td><%=resultSet.getString("expiration_date")%></td>
			<td><a href="editPaymentMethod.jsp?id=<%=resultSet.getInt("id")%>">Edit</a></td>
			<td><a
				href="deletePaymentMethod.jsp?id=<%=resultSet.getInt("id")%>">Delete</a></td>
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