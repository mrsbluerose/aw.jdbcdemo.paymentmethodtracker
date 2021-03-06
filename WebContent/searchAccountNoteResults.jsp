<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
	//Forwarding information	
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
	
	//Data
	ArrayList<String[]> accountNoteList = (ArrayList<String[]>) request.getAttribute("accountNoteList");
	String accountID = request.getParameter("accountID");
	String accountName = (String) request.getAttribute("accountName");
	String originPage = "searchAccountNoteResults";
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Account Note Search Results</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<!-- button to go back to list of account notes -->
	<form action="accountNoteController" method="post">
		<input type="hidden" name="accountID" value=<%=accountID%> >
		<input type="hidden" name="action" value="list" >
		<input type="submit" value="Back to Notes">
	</form>

	<h1>Account Notes Search Results</h1>
	<p>Account ID:  <%=accountID%></p>
	<p>Account Name: <%=accountName%></p>
	
	<!-- search type and term form -->
	<!-- search type and term form -->
	<form action="accountNoteController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="accountNoteID" name="searchType" value="accountNoteID">
  			<label for="accountNoteID">Account Note ID</label><br>
  		<input type="radio" id="accountNoteDate" name="searchType" value="accountNoteDate">
  			<label for="accountNoteDate">Year</label>
  		<input type="radio" id="accountNoteText" name="searchType" value="accountNoteText">
  			<label for="accountNoteText">Note Text</label>
		
		Search for: <input type="text" name="searchTerm" >
		<input type="hidden" name="accountID" value=<%=accountID%> >
		<input type="hidden" name="action" value="searchAccountNoteDAO" >
		<input type="submit" value="Search" >
		</pre>
	</form>
	
	<!-- new note form -->
	<form action="accountNoteController" method="post">
		<pre>
		New Note
		Date: <input type="text" name="accountNoteDate" >
		Note: <input type="text" name="accountNoteText" >
		<input type="hidden" name="accountID" value=<%=accountID%> >
		<input type="hidden" name="action" value="createAccountNoteDAO" >
		<input type="submit" value="Save" >
		</pre>
	</form>

	<!-- Table of account note results -->
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
			<!-- Edit button -->
				<form action="accountNoteController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage%> >
					<input type="hidden" name="accountNoteID" value=<%=s[0]%> >
					<input type="hidden" name="searchType" value=<%=searchType%> >
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
					<input type="hidden" name="action" value="editAccountNoteJSP" >
					<input type="submit" value="Edit" >
				</form>
			</td>
			<td>
			<!-- Delete button -->
				<form action="accountNoteController" method="post">
					<input type="hidden" name="originPage" value=<%=originPage%> >
					<input type="hidden" name="accountID" value=<%=accountID%> >
					<input type="hidden" name="accountNoteID" value=<%=s[0]%> >
					<input type="hidden" name="searchType" value=<%=searchType%> >
					<input type="hidden" name="searchTerm" value=<%=searchTerm%> >
					<input type="hidden" name="action" value="deleteAccountNoteJSP" >
					<input type="submit" value="Delete" >
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>

</body>
</html>