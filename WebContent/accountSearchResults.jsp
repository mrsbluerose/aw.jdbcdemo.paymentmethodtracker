<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Account Search Results</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<a href="index.html">Home</a>
	<h1>Account Search Results</h1>
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Accounts">
		<a href="createAccount.jsp">Create New</a>
	<a href="searchAccount.jsp">Search</a>
	</form>
	
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
			<td><a href="editAccount.jsp?account_id=<%=s[0]%>">Edit</a></td>
			<td><a href="deleteAccount.jsp?account_id=<%=s[0]%>">Delete</a></td>
		</tr>
		<%
		}
		%>
		
	</table>
</body>
</html>