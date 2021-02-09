<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.ArrayList"%>
	
<%
	String message = (String) request.getAttribute("message");
	ArrayList<String[]> paymentMethodList = (ArrayList<String[]>) request.getAttribute("paymentMethodList");
	String originPage = "listPaymentMethods";
%>
		
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Payment Methods</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- Confirmation message (if there is one) -->
	<h2><%=message%></h2>

	<!-- Back to accounts button -->
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Accounts">
	</form>
	
	<h1>Payment Methods</h1>
	
	<!-- create new account button -->
	<form action="PaymentMethodController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage %> />
		<input type="hidden" name="action" value="createPaymentMethodJSP" />
		<input type="submit" value="Create New">
	</form>
	
	<!-- search account button -->
	<form action="PaymentMethodController" method="post">
		<input type="hidden" name="originPage" value=<%=originPage %> />
		<input type="hidden" name="action" value="searchPaymentMethodJSP" />
		<input type="submit" value="Search">
	</form>
	
	<!-- Table of payment methods -->
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
					<input type="hidden" name="originPage" value=<%=originPage %> />
					<input type="hidden" name="paymentMethodID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="editPaymentMethodJSP" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
			
			<!-- Delete button -->
				<form action="paymentMethodController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage %> />
					<input type="hidden" name="paymentMethodID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="deletePaymentMethodJSP" />
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