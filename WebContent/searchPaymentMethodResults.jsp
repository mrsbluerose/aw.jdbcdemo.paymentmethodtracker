<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	//Forwarding information	
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");	

	//Data
	String originPage = "searchPaymentMethodResults";
	ArrayList<String[]> paymentMethodList = (ArrayList<String[]>) request.getAttribute("paymentMethodList");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Payment Method Search Results</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- button to go back to list of accounts -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Payment Methods">
	</form>
	
	<!-- create new payment method button -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="hidden" name="action" value="createPaymentMethodJSP" />
		<input type="submit" value="Create New">
	</form>
	
	<!-- search payment Method button -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="hidden" name="action" value="searchPaymentMethodJSP" />
		<input type="submit" value="Search">
	</form>	

	<h1>Payment Method Search Results</h1>
	
	<!-- Table of payment methods search result -->
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Expiration Date</th>
			<th>Notes</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
		for (String[] s:paymentMethodList){
		%>

		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td><%=s[3]%></td>
			
			<!-- button to see list of notes for specified account -->
			<td><form action="paymentMethodNoteController" method="post">
					<input type="hidden" name="paymentMethodID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="list" />
					<input type="submit" value="Notes">
				</form>
			</td>
			<td>
			
			<!-- Edit button -->
				<form action="paymentMethodController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage%> />
					<input type="hidden" name="searchType" value=<%=searchType%> />
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
					<input type="hidden" name="paymentMethodID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="editSelectPaymentMethod" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
			
			<!-- Delete button -->
				<form action="paymentMethodController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage%> />
					<input type="hidden" name="searchType" value=<%=searchType%> />
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
					<input type="hidden" name="paymentMethodID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="deleteSelectPaymentMethod" />
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