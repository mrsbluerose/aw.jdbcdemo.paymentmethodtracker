<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Search Accounts</title>
</head>

<body>
	<a href="index.html">Home</a>
	<h1>Search Accounts</h1>
	<form action="accountController" method="post">
		<pre>
		Search By: 
		<select name="searchType" multiple size="1">
			<option value="id">ID</option>
			<option value="name">Name</option>
			<option value="paymentMethodID">Payment Method ID</option>
		</select>
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>

</body>
</html>