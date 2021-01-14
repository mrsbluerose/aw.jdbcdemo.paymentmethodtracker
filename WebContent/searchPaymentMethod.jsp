<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Payment Methods</title>
</head>
<body>
	<h1>Search Payment Methods</h1>
	<form action="paymentMethodController" method="post">
		<pre>
		Search By: 
		<select name="searchType" multiple size="1">
			<option>ID</option>
			<option>Name</option>
			<option>Expiration Year</option>
		</select>
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>
</body>
</html>