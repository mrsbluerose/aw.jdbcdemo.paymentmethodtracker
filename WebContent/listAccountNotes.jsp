<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Account Notes</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<a href="index.html">Home</a>

	<h1>Account Notes</h1>
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Accounts">
		<a href="createAccountNote.jsp">Create New</a>
	<a href="searchAccount.jsp">Search</a>
	</form>

	
	<%
	String accountID = request.getParameter("id");
	
	ArrayList<String[]> accountNoteList = (ArrayList<String[]>) request.getAttribute("accountNoteList");
	String accountName = (String) request.getAttribute("accountName");
	String message = (String) request.getAttribute("message");
	%>
	<h2><%=message%></h2>

<p>Account ID:  <%=accountID%></p>
<p>Account Name: <%=accountName%></p>

	<table>
		<tr>
			<th>Note ID</th>
			<th>Date</th>
			<th>Note</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<%
		
		for (String[] s:accountNoteList){
		%>

		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td>
				<form action="accountNoteController" method="post">
					<input type="hidden" name="id" value=<%=s[0]%> />
					<input type="hidden" name="action" value="editSelectAccountNote" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
				<form action="accountNoteController" method="post">
					<input type="hidden" name="id" value=<%=s[0]%> />
					<input type="hidden" name="action" value="deleteSelectAccountNote" />
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