<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	//Forwarding information	
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");	

	//Data
	ArrayList<String[]> accountList = (ArrayList<String[]>) request.getAttribute("accountList");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Account Search Results</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- button to go back to list of accounts -->
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Accounts">
		<a href="createAccount.jsp">Create New</a>
		<a href="searchAccount.jsp">Search</a>
	</form>

	<h1>Account Search Results</h1>
	
	<!-- Table of accounts in search result -->
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
		for (String[] s:accountList){
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			
			<!-- button to see list of notes for specified account -->
			<td><form action="accountNoteController" method="post">
					<input type="hidden" name="accountID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="list" />
					<input type="submit" value="Notes">
				</form>
			</td>
			<td>
			
			<!-- Edit button -->
				<form action="accountController" method="post">
					<input type="hidden" name="originPage" value="searchAccountResults" />
					<input type="hidden" name="accountID" value=<%=s[0]%> />
					<input type="hidden" name="searchType" value=<%=searchType%> />
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
					<input type="hidden" name="action" value="editSelectAccount" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
			
			<!-- Delete button -->
				<form action="accountController" method="post">
					<input type="hidden" name="originPage" value="searchAccountResults" />
					<input type="hidden" name="accountID" value=<%=s[0]%> />
					<input type="hidden" name="searchType" value=<%=searchType%> />
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
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