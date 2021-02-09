<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	//Forwarding information	
	String originPage = request.getParameter("originPage");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
	
	//Data
	String[] accountNoteItems = (String[]) request.getAttribute("accountNoteItems");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Delete Account Note</title>
</head>

<body>

	<h1>Delete Account Note</h1>
	
	<!-- Form to delete account note (displays account note information to confirm) -->
	<form action="accountNoteController" method="post">
		<pre>
		ID: <%=accountNoteItems[0]%>
		Account: <%=accountNoteItems[1]%>
		Date: <%=accountNoteItems[2]%>
		Note: <%=accountNoteItems[3]%>
		<input type="hidden" name="accountNoteID" value="<%=accountNoteItems[0]%>" />
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>" />
		<input type="hidden" name="action" value="deleteAccountNoteDAO" />
		<input type="submit" value="Delete" /> 
		</pre>
	</form>
	
	
	
	<form action="accountNoteController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="originPage" value=<%=originPage%> />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>" />
		<input type="submit" value="Cancel">
	</form>

</body>
</html>