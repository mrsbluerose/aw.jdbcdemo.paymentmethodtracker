<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Payment Method</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Create Payment Method</h1>
	<form action="paymentMethodController" method="post">
		<pre>
Name: <input type="text" name="name" />
Description: <input type="text" name="description" />
Expiration: <input type="text" name="expDate" />
<input type="hidden" name="action" value="create" />
<input type="submit" value="Save">
</pre>
	</form>
</body>
</html>