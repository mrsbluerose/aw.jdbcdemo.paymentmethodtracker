<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
	
	//Data
	String[] paymentMethodItems = (String[]) request.getAttribute("paymentMethodItems");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Payment Method</title>
</head>

<body>

	<h1>Edit Payment Method</h1>
	
	<!-- Form to enter new payment method name, description or expiration date (pre-filled with existing data) -->
	<form action="paymentMethodController" method="post">
		<pre>
		ID: <%=paymentMethodItems[0]%>
		Name: <input type="text" name="paymentMethodName" value="<%=paymentMethodItems[1]%>" >
		Description: <input type="text" name="paymentMethodDescription" value="<%=paymentMethodItems[2]%>" >
		Expiration: <input type="text" name="paymentMethodExpDate" value="<%=paymentMethodItems[3]%>" >
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodItems[0]%>" >
		<input type="hidden" name="action" value="editPaymentMethodDAO" >
		<input type="submit" value="Save">
		</pre>
	</form>
	
	<!-- Form to delete payment method -->
	<form action="paymentMethodController" method="post">	
		<pre>
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodItems[0]%>" > 
		<input type="hidden" name="action" value="deletePaymentMethodDAO" >
		<input type="submit" value="Delete Payment Method" >
		</pre>
	</form>
	
	<!-- Form to cancel action -->
		<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="cancel" >
		<input type="hidden" name="originPage" value=<%=originPage%> >
		<input type="hidden" name="searchType" value=<%=searchType%> >
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
		<input type="submit" value="Cancel" >
	</form>

</body>
</html>

