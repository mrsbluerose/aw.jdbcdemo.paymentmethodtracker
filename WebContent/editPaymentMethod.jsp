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
	String id = request.getParameter("id");
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<%
	try {
		connection = ConnectionUtil.getConnection();
		statement = connection.createStatement();
		String sql = "SELECT * FROM payment_method WHERE id=" + id;
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
%>



<html>
<head>
<meta charset="UTF-8">
<title>Edit Payment Method</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Edit Payment Method</h1>
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="id" value="<%=resultSet.getString("id")%>">
		<pre>
ID: <%=resultSet.getInt("id")%>
Name: <input type="text" name="name"
				value="<%=resultSet.getString("name")%>" />
Description: <input type="text" name="description"
				value="<%=resultSet.getString("description")%>" />
Expiration: <input type="text" name="expDate"
				value="<%=resultSet.getString("expiration_date")%>" />
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

