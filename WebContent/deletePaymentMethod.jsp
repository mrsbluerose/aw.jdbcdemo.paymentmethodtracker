<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	String[] paymentMethodItems = (String[]) request.getAttribute("paymentMethodItems");
%>	
	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete Payment Method</title>
</head>

<body>

	<h1>Delete Payment Method ?</h1>
	
	<form action="paymentMethodController" method="post">
		<pre>
		ID: <%=paymentMethodItems[0]%>
		Name: <%=paymentMethodItems[1]%>
		Description: <%=paymentMethodItems[2]%>
		Expiration Date: <%=paymentMethodItems[3]%>
		<input type="hidden" name="paymentMethodID" value="<%=paymentMethodItems[0]%>" />
		<input type="hidden" name="action" value="delete" />
		<input type="submit" value="Delete" />
		</pre>
	</form>

</body>
</html>