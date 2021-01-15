<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
	<a href="index.html">Home</a>
	<h1>Create Account</h1>
	<form action="accountController" method="post">
		<pre>
Name: <input type="text" name="name" />
Payment Method: <input type="text" name="paymentMethod" />
<input type="hidden" name="action" value="create" />
<input type="submit" value="Save">
</pre>
	</form>
</body>
</html>