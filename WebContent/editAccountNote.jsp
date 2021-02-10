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
<title>Edit Account Note</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<h1>Edit Account Note</h1>
	
	<!-- Form to enter new account note date or text (pre-filled with existing data) -->
	<form action="accountNoteController" method="post">
		<pre>
		Account Note ID: <%=accountNoteItems[0]%>
		Account ID: <%=accountNoteItems[1]%>
		Date: <input type="text" name="accountNoteDate" value="<%=accountNoteItems[2]%>" >
		Note: <input type="text" name="accountNoteText"	value="<%=accountNoteItems[3]%>" >
		<input type="hidden" name="accountNoteID" value="<%=accountNoteItems[0]%>" >
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>" >
		<input type="hidden" name="action" value="editAccountNoteDAO" >
		<input type="submit" value="Save" >
		</pre>
	</form>
	
	<!-- Form to delete account note -->
	<form action="accountNoteController" method="post">	
		<pre>
		<input type="hidden" name="accountNoteID" value="<%=accountNoteItems[0]%>" >
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>" >
		<input type="hidden" name="action" value="deleteAccountNoteDAO" >
		<input type="submit" value="Delete" > 
		</pre>
	</form>
	
	<!-- Form to cancel action -->
	<form action="accountNoteController" method="post">
		<input type="hidden" name="action" value="cancel" >
		<input type="hidden" name="originPage" value=<%=originPage%> >
		<input type="hidden" name="searchType" value=<%=searchType%> >
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>" >
		<input type="submit" value="Cancel" >
	</form>

</body>
</html>