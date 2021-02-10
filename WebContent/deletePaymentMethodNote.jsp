<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");

	//Data
	String[] paymentMethodNoteItems = (String[]) request.getAttribute("paymentMethodNoteItems");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete PaymentMethod Note</title>
</head>

<body>

	<h1>Delete PaymentMethod Note</h1>
	
	<!-- Form to delete paymentMethod note (displays paymentMethod note information to confirm) -->
	<form action="paymentMethodNoteController" method="post">
		<pre>
		ID: <%=paymentMethodNoteItems[0]%>
		PaymentMethod: <%=paymentMethodNoteItems[1]%>
		Date: <%=paymentMethodNoteItems[2]%>
		Note: <%=paymentMethodNoteItems[3]%>
		<input type="hidden" name="paymentMethodNoteID" value="<%=paymentMethodNoteItems[0]%>" />
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodNoteItems[1]%>">
		<input type="hidden" name="action" value="deletePaymentMethodNoteDAO" />
		<input type="submit" value="Delete" /> 
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="paymentMethodNoteController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodNoteItems[1]%>" />
		<input type="submit" value="Cancel">
	</form>

</body>
</html>