<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Account Note Search Results</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

<%
ArrayList<String[]> accountNoteList = (ArrayList<String[]>) request.getAttribute("accountNoteList");
String accountID = request.getParameter("accountID");
String accountName = (String) request.getAttribute("accountName");
%>

	<h1>Account Note Search Results</h1>
	<form action="accountNoteController" method="post">
	<input type="hidden" name="accountID" value=<%=accountID%> />
		<input type="hidden" name="action" value="list" />
		<input type="submit" value="Back to Notes">
	</form>
	<form action="accountNoteController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="accountNoteID" name="searchType" value="accountNoteID">
  			<label for="accountNoteID">Account Note ID</label><br>
  		<input type="radio" id="date" name="searchType" value="date">
  			<label for="date">Year</label>
  		<input type="radio" id="noteText" name="searchType" value="noteText">
  			<label for="noteText">Note Text</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="accountID" value=<%=accountID%> />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>
	<form action="accountNoteController" method="post">
		<pre>
		Date: <input type="text" name="date" />
		Note: <input type="text" name="note" />
		<input type="hidden" name="accountNoteID" value=<%=accountID%> />
		<input type="hidden" name="action" value="create" />
		<input type="submit" value="Save">
		</pre>
	</form>
	

<p>Account ID:  <%=accountID%></p>
<p>Account Name: <%=accountName%></p>

	<table>
		<tr>
			<th>Note ID</th>
			<th>Date</th>
			<th>Note</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<%
		
		for (String[] s:accountNoteList){
		%>

		<tr>
			<td><%=s[0]%></td>
			<td><%=s[1]%></td>
			<td><%=s[2]%></td>
			<td>
				<form action="accountNoteController" method="post">
					<input type="hidden" name="accountNoteID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="editSelectAccountNote" />
					<input type="submit" value="Edit">
				</form>
			</td>
			<td>
				<form action="accountNoteController" method="post">
					<input type="hidden" name="accountNoteID" value=<%=s[0]%> />
					<input type="hidden" name="action" value="deleteSelectAccountNote" />
					<input type="submit" value="Delete">
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>