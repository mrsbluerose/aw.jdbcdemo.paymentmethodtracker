<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
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

	<%
	String message = (String) request.getAttribute("message");
	%>
	<p><h2><%=message%></p></h2>

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
		ArrayList<String[]> accountList = (ArrayList<String[]>) request.getAttribute("accountList");
		for (String[] s:accountList){
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td><a href="listAccountNotes.jsp?account_id=<%=s[0]%>&account_name=<%=s[1]%>">Notes</a></td>
			<td>
				<form action="accountController" method="post">
					<input type="hidden" name="id" value=<%=s[0]%> />
					<input type="hidden" name="action" value="editSelectAccount" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
				<form action="accountController" method="post">
					<input type="hidden" name="id" value=<%=s[0]%> />
					<input type="hidden" name="action" value="deleteSelectAccount" />
					<input type="submit" value="Delete">
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>