<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Search Accounts</title>
</head>

<body>

	<h1>Search Accounts</h1>
	
	<!-- Form to search accounts by id, name or payment method id -->
	<form action="accountController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="accountID" name="searchType" value="accountID">
  			<label for="accountID">ID</label><br>
  		<input type="radio" id="accountName" name="searchType" value="accountName">
  			<label for="accountName">Name</label><br>
  		<input type="radio" id="paymentMethodID" name="searchType" value="paymentMethodID">
  			<label for="paymentMethodID">Payment Method ID</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>
		<form action="accountController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="originPage" value="listAccounts" />
		<input type="submit" value="Cancel">
	</form>

</body>
</html>