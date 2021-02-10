<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Create Payment Method</title>
</head>

<body>

	<h1>Create Payment Method</h1>
	
	<!-- Form to enter payment method name, description, expiration date -->
	<form action="paymentMethodController" method="post">
		<pre>
		Name: <input type="text" name="paymentMethodName" >
		Description: <input type="text" name="paymentMethodDescription" >
		Expiration: <input type="text" name="paymentMethodExpDate" >
		<input type="hidden" name="action" value="createPaymentMethodDAO" >
		<input type="submit" value="Save">
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="cancel" >
		<input type="hidden" name="originPage" value=<%=originPage%> >
		<input type="hidden" name="searchType" value=<%=searchType%> >
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
		<input type="submit" value="Cancel">
	</form>
	
</body>
</html>