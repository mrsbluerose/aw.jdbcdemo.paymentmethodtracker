<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
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
		Name: <input type="text" name="paymentMethodName" value="<%=paymentMethodItems[1]%>" />
		Description: <input type="text" name="paymentMethodDescription" value="<%=paymentMethodItems[2]%>" />
		Expiration: <input type="text" name="paymentMethodExpDate" value="<%=paymentMethodItems[3]%>" />
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodItems[0]%>">
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
		</pre>
	</form>

</body>
</html>

