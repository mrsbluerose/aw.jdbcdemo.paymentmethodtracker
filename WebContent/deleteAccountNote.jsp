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
	String id = request.getParameter("account_note_id");
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<%
	try {
		connection = ConnectionUtil.getConnection();
		statement = connection.createStatement();
		String sql = "SELECT * FROM account_note WHERE account_note_id=" + id;
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
%>

<html>
<head>
<meta charset="UTF-8">
<title>Delete Account Note</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Delete Account Note</h1>
	<pre>
Date: <%=resultSet.getString("account_note_date")%>
Note: <%=resultSet.getInt("account_note_text")%>
</pre>
	<form action="accountNoteController" method="post">
		<input type="hidden" name="id" value="<%=resultSet.getString("account_note_id")%>" />
		<input type="submit" name="action" value="delete" /> 
		<input type="submit" name="action" value="cancel" />

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