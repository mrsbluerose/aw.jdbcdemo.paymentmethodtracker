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
String name = request.getParameter("account_name");
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<html>
<head>
<meta charset="UTF-8">
<title>Account Notes</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<a href="index.html">Home</a>
	<a href='listAccounts.jsp'>Back to Accounts</a>
	<h1>Account Notes</h1>
	<a href="createAccountNote.jsp?account_id=<%=id%>&account_name=<%=name%>">Create New</a>

<p>Account ID:  <%=id%></p>
<p>Account Name: <%=name%></p>

	<table>
		<tr>
			<th>Date</th>
			<th>Note</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			try {
				connection = ConnectionUtil.getConnection();
				statement = connection.createStatement();
				String sql = "SELECT account_note_date, account_note "
						+ "FROM account_note WHERE account_id=" + id;
				resultSet = statement.executeQuery(sql);
				while (resultSet.next()) {
		%>

		<tr>
			<td><%=resultSet.getString("account_note_date")%></td>
			<td><%=resultSet.getString("account_note")%></td>
			<td><a href="editAccountNote.jsp?account_note_id=<%=resultSet.getInt("account_note_id")%>">Edit</a></td>
			<td><a href="deleteAccountNote.jsp?account_note_id=<%=resultSet.getInt("account_note_id")%>">Delete</a></td>
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