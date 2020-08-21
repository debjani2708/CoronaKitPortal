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
<h3>Admin Login</h3>
<form action="login" name="admin">
		<label>UserName:</label>
		<input type="text" name="Username" required/>
		<br/><br/>
		<label>Password:</label>
		<input type="password" name="Password" required/>
		<br/><br/>
		<input type="submit" value="Login"/>
</form>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>