<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>

<body>

	<h1>Create Account</h1>
	
	<!-- Form to enter account name and payment method id -->
	<form action="accountController" method="post">
		<pre>
		Name: <input type="text" name="accountName" />
		Payment Method: <input type="text" name="paymentMethodID" />
		<input type="hidden" name="action" value="create" />
		<input type="submit" value="Save">
		</pre>
	</form>

</body>
</html>