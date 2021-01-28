<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
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
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>">
		<input type="submit" name="action" value="Delete" /> 
		</pre>
		
	</form>

</body>
</html>