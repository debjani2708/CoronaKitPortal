<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona-Kit Portal</title>
</head>
<body>
<jsp:include page="header.jsp" />
	<hr/>
<h3>User Details</h3>
<form action="newuser" name="user">
		<label>Name:</label>
		<input type="text" name="PersonName" required/>
		<br/><br/>
		<label>Email_ID:</label>
		<input type="text" name="emailId" required/>
		<br/><br/>
		<label>Contact_No:</label>
		<input type="number" name="contactNo" required/>
		<br/><br/>
		<input type="submit" value="Enter"/>
</form>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>