<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
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
		Name: <input type="text" name="paymentMethodName" />
		Description: <input type="text" name="paymentMethodDescription" />
		Expiration: <input type="text" name="paymentMethodExpDate" />
		<input type="hidden" name="action" value="create" />
		<input type="submit" value="Save">
		</pre>
	</form>
	
</body>
</html>