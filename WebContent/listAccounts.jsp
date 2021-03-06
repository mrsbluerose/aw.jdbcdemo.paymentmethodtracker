<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>

<%
	String message = (String) request.getAttribute("message");
	ArrayList<String[]> accountList = (ArrayList<String[]>) request.getAttribute("accountList");
	String originPage = "listAccounts";
%>
	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Accounts</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- Confirmation message (if there is one) -->
	<h2><%=message%></h2>
	
	<h1>Accounts</h1>
	
	<!-- create new account button -->
	<form action="accountController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage %> >
		<input type="hidden" name="action" value="createAccountJSP" >
		<input type="submit" value="Create New">
	</form>
	
	<!-- search account button -->
	<form action="accountController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage %> >
		<input type="hidden" name="action" value="searchAccountsJSP" >
		<input type="submit" value="Search">
	</form>
	
	<!-- List payment methods button -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="list" >
		<input type="submit" value="Payment Methods">
	</form>

	<!-- Table of accounts -->
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
					<input type="hidden" name="accountID" value=<%=s[0]%> >
					<input type="hidden" name="action" value="list" >
					<input type="submit" value="Notes">
				</form>
			</td>
			<td>
			
			<!-- Edit button -->
				<form action="accountController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage %> >
					<input type="hidden" name="accountID" value=<%=s[0]%> >
					<input type="hidden" name="action" value="editAccountJSP" >
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
			
			<!-- Delete button -->
				<form action="accountController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage %> >
					<input type="hidden" name="accountID" value=<%=s[0]%> >
					<input type="hidden" name="action" value="deleteAccountJSP" >
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