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
		<input type="hidden" name="action" value="searchPaymentMethodJSP" />
		<input type="submit" value="Search" />
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="paymentMethodController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="submit" value="Cancel">
	</form>
	
</body>
</html>