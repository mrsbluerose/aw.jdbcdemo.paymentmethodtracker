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
	String id = request.getParameter("payment_method_id");
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<%
	try {
		connection = ConnectionUtil.getConnection();
		statement = connection.createStatement();
		String sql = "SELECT * FROM payment_method WHERE payment_method_id=" + id;
		resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
%>

<html>
<head>
<meta charset="UTF-8">
<title>Delete Payment Method</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Delete Payment Method ?</h1>
	<pre>
ID: <%=resultSet.getInt("payment_method_id")%>
Name: <%=resultSet.getString("payment_method_name")%>
Description: <%=resultSet.getString("payment_method_description")%>
Expiration Date: <%=resultSet.getString("payment_method_exp_date")%>
</pre>
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="id" value="<%=resultSet.getString("payment_method_id")%>" />
		<input type="submit" name="action" value="delete" /> <input
			type="submit" name="action" value="cancel" />

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