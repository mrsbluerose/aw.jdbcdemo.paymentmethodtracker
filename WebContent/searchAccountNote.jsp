<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Search Account Notes</title>
</head>

<body>
	<h1>Search Account Notes</h1>
	<form action="accountNoteController" method="post">
		<pre>
		Search By: 
		<input type="radio" id="accountNoteID" name="searchType" value="accountNoteID">
  			<label for="accountNoteID">Account Note ID</label><br>
  		<input type="radio" id="accountNoteDate" name="searchType" value="accountNoteDate">
  			<label for="accountNoteDate">Year</label>
  		<input type="radio" id="accountNoteText" name="searchType" value="accountNoteText">
  			<label for="accountNoteText">Note Text</label>
		
		Search for: <input type="text" name="searchTerm" />
		<input type="hidden" name="action" value="search" />
		<input type="submit" value="Search" />
		</pre>
	</form>

</body>
</html>