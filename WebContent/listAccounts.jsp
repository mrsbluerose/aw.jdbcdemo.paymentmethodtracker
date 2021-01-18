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
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<html>
<head>
<meta charset="UTF-8">
<title>Accounts</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Accounts</h1>
	<a href="createAccount.jsp">Create New</a>
	<a href="searchAccount.jsp">Search</a>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Payment Method</th>
			<th>Notes</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			try {
				connection = ConnectionUtil.getConnection();
				statement = connection.createStatement();
				String sql = "SELECT a.account_id, a.account_name, pm.payment_method_name "
						+ "FROM account as a "
						+ "INNER JOIN payment_method as pm ON a.payment_method_id=pm.payment_method_id;"; 
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>

		<tr>
			<td><%=resultSet.getInt("account_id")%></td>
			<td><%=resultSet.getString("account_name")%></td>
			<td><%=resultSet.getString("payment_method_name")%></td>
			<td><a href="listAccountNotes.jsp?account_id=<%=resultSet.getInt("account_id")%>&account_name=<%=resultSet.getString("account_name")%>">Notes</a></td>
			<td><a href="editAccount.jsp?account_id=<%=resultSet.getInt("account_id")%>">Edit</a></td>
			<td><a href="deleteAccount.jsp?account_id=<%=resultSet.getInt("account_id")%>">Delete</a></td>
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