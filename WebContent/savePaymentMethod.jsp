<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Payment Method</title>
</head>
<body>
<h1>Save Payment Method</h1>
<form action="paymentMethod" method="post">
<pre>
Name: <input type="text" name="name" />
Description: <input type="text" name="description" />
Expiration: <input type="text" name="expDate" />
<input type="hidden" name="action" value="save"/>
<input type="submit" value="Save">
</pre>
</form>
</body>
</html>