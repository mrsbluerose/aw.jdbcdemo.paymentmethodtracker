<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Account Note</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>
	<h1>Edit Account Note</h1>
	<form action="accountNoteController" method="post">
		<%
		String[] accountNoteItems = (String[]) request.getAttribute("accountNoteItems");
		%>
		<pre>
		Account Note ID: <%=accountNoteItems[0]%>
		Account ID: <%=accountNoteItems[1]%>
		Date: <input type="text" name="date" value="<%=accountNoteItems[2]%>" />
		Note: <input type="text" name="accountNoteText"	value="<%=accountNoteItems[3]%>" />
		<input type="hidden" name="accountNoteID" value="<%=accountNoteItems[0]%>">
		<input type="hidden" name="accountID" value="<%=accountNoteItems[1]%>">
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
		</pre>
	</form>

</body>
</html>