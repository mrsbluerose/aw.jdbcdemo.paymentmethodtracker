<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Search Payment Methods</title>
</head>

<body>

	<h1>Search Payment Methods</h1>
	
	<!-- Form to search payment method by id, name or expiration year -->
	<form action="paymentMethodController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="paymentMethodID" name="searchType" value="paymentMethodID">
  			<label for="paymentMethodID">ID</label><br>
  		<input type="radio" id="paymentMethodName" name="searchType" value="paymentMethodName">
  			<label for="paymentMethodName">Name</label><br>
  		<input type="radio" id="paymentMethodDescription" name="searchType" value="paymentMethodDescription">
  			<label for="paymentMethodDescription">Description</label><br>
  		<input type="radio" id="paymentMethodExpYear" name="searchType" value="paymentMethodExpYear">
  			<label for="paymentMethodExpYear">Expiration Year</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>
	
</body>
</html>