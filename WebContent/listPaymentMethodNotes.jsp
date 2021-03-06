<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

	<%
	ArrayList<String[]> paymentMethodNoteList = (ArrayList<String[]>) request.getAttribute("paymentMethodNoteList");
	String paymentMethodID = request.getParameter("paymentMethodID");
	String paymentMethodName = (String) request.getAttribute("paymentMethodName");
	String message = (String) request.getAttribute("message");
	String originPage = "listPaymentMethodNotes";
	%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Payment Method Notes</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- Confirmation message (if there is one) -->
	<h2><%=message%></h2>

	<!-- Back to paymentMethods button -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="list" >
		<input type="submit" value="Back to Payment Methods">
	</form>
	
	<h1>Payment Method Notes</h1>
	<p>Payment Method ID:  <%=paymentMethodID%></p>
	<p>Payment Method Name: <%=paymentMethodName%></p>
	
	<!-- search type and term form -->
	<form action="paymentMethodNoteController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="paymentMethodNoteID" name="searchType" value="paymentMethodNoteID">
  			<label for="paymentMethodNoteID">PaymentMethod Note ID</label><br>
  		<input type="radio" id="paymentMethodNoteDate" name="searchType" value="paymentMethodNoteDate">
  			<label for="paymentMethodNoteDate">Year</label>
  		<input type="radio" id="paymentMethodNoteText" name="searchType" value="paymentMethodNoteText">
  			<label for="paymentMethodNoteText">Note Text</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="paymentMethodID" value=<%=paymentMethodID%> >
		<input type="hidden" name="action" value="searchPaymentMethodNoteDAO" >
		<input type="submit" value="Search" />
		</pre>
	</form>
	
	<!-- new note form -->
	<form action="paymentMethodNoteController" method="post">
		<pre>
		New Note
		Date: <input type="text" name="paymentMethodNoteDate" >
		Note: <input type="text" name="paymentMethodNoteText" >
		<input type="hidden" name="paymentMethodID" value=<%=paymentMethodID%> >
		<input type="hidden" name="action" value="createPaymentMethodNoteDAO" >
		<input type="submit" value="Save">
		</pre>
	</form>

	<!-- Table of paymentMethod notes -->
	<table>
		<tr>
			<th>Note ID</th>
			<th>Date</th>
			<th>Note</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
		for (String[] s:paymentMethodNoteList){
		%>
		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td>
			
			<!-- Edit button -->
				<form action="paymentMethodNoteController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage%> >
					<input type="hidden" name="paymentMethodNoteID" value=<%=s[0]%> >
					<input type="hidden" name="action" value="editPaymentMethodNoteJSP" >
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
			
			<!-- Delete button -->
				<form action="paymentMethodNoteController" method="post">
					<input type="hidden" name="paymentMethodID" value=<%=paymentMethodID%> >
					<input type="hidden" name="originPage" value=<%=originPage%> >
					<input type="hidden" name="paymentMethodNoteID" value=<%=s[0]%> >
					<input type="hidden" name="action" value="deletePaymentMethodNoteJSP" >
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