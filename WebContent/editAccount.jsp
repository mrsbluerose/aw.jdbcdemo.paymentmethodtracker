<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="aw.jdbcdemo.paymentmethodtracker.model.PaymentMethod"%>
<%
	String[] accountItems = (String[]) request.getAttribute("accountItems");
	ArrayList<PaymentMethod> paymentMethodList = (ArrayList<PaymentMethod>) request.getAttribute("paymentMethodList");
	String searchType = request.getParameter("searchType");
	String searchTerm = request.getParameter("searchTerm");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Edit Account</title>
<link rel="stylesheet" href="styles.css">
</head>

<body>

	<h1>Edit Account</h1>
	
	<!-- Form to enter new account name or payment method id (pre-filled with existing data) -->
	<form action="accountController" method="post">
		<pre>
		ID: <%=accountItems[0]%>
		Name: <input type="text" name="accountName" value="<%=accountItems[1]%>" />
		Payment Method: 
		<select name="paymentMethod">
		<%for (PaymentMethod pm : paymentMethodList) {%>
            <option value="<%=String.valueOf(pm.getID())%>"><%=pm.getName()%></option>
		<%} %>
    	</select>
		<input type="hidden" name="accountID" value="<%=accountItems[0]%>">
		<input type="hidden" name="action" value="edit" />
		<input type="submit" value="Save">
		</pre>
	</form>
	</form>
		<form action="accountController" method="post">
		<input type="hidden" name="action" value="cancel" />
		<input type="hidden" name="page" value="searchAccountResults.jsp" />
		<input type="hidden" name="searchType" value=<%=searchType%> />
		<input type="hidden" name="searchTerm" value=<%=searchTerm%> />
		<input type="submit" value="Cancel">
	</form>

</body>
</html>