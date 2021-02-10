<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
%>

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
		
		Search for: <input type="text" name="searchTerm" >
		<input type="hidden" name="action" value="searchAccountsDAO" >
		<input type="submit" value="Search" >
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="cancel" >
		<input type="hidden" name="originPage" value=<%=originPage%> >
		<input type="hidden" name="searchType" value=<%=searchType%> >
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
		<input type="submit" value="Cancel">
	</form>

</body>
</html>