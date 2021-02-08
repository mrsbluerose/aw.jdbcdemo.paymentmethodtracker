<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
	
	//Data
	String[] accountItems = (String[]) request.getAttribute("accountItems");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete Account</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<h1>Delete Account</h1>
	
	<!-- Form to delete account (displays account information to confirm) -->
	<form action="accountController" method="post">
		<pre>
		ID: <%=accountItems[0]%>
		Name: <%=accountItems[1]%>
		Payment Method: <%=accountItems[2]%>	
		<input type="hidden" name="accountID" value="<%=accountItems[0]%>" /> 
		<input type="hidden" name="action" value="deleteAccountDAO" />
		<input type="submit" value="Delete" />
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="accountController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="submit" value="Cancel">
	</form>
	
</body>
</html>