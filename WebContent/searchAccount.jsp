<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<input type="radio" id="id" name="searchType" value="id">
  			<label for="id">ID</label><br>
  		<input type="radio" id="name" name="searchType" value="name">
  			<label for="name">Name</label><br>
  		<input type="radio" id="paymentMethodID" name="searchType" value="paymentMethodID">
  			<label for="paymentMethodID">Payment Method ID</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>

</body>
</html>