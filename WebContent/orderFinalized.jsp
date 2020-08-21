<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona-Kit Details</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr/>	
<%String pName=(String)session.getAttribute("Name");
out.print("Hello " + pName + " Your order Status" );%>
<center>
<p><strong>Thank you for Shopping With Us</strong></p>
<p><strong>Your order has been finalized</strong></p>
<p><strong>Visit Again !</strong></p>
</center>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>