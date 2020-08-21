<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona-Kit Portal</title>
</head>
<body>
<h1> ORDER SUMMARY PAGE</h1>
<hr/>
<form action="ordersummary" method="post">
<%String pName=(String)session.getAttribute("Name");
out.print("Hello " + pName + " Welcome to our portal");%>
<br/><br/>
<label>Order ID : </label>
	<input type="text" name="orderID"  value=<%=session.getAttribute("OrderID") %> readonly/>
<br/><br/>
<% LocalDate dt = LocalDate.now(); %>
<label>Order Date : <strong><%=dt %></strong> </label>    
 <c:choose>
		<c:when test="${order == null}">
			<p>No order has been placed yet! Place new order.</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ProductID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Order Quantity</th>
					<th>Order Price</th>
				</tr>
				<c:forEach items="${order }" var="order">
					<tr>						 
						<td>${order.productId }</td>
						<td>${order.productName }</td>
						<td>${order.productDescription }</td>
						<td>${order.productPrice }</td>
						<td>${order.orderQuantity }</td>
						<td>${order.productOrderAmount}</td>
					</tr>
				</c:forEach>				
			</table>
		</c:otherwise>
	</c:choose>		
	<br/><br/>	 
  <label>Total Amount : </label>
   <input type="text" name="TotalAmount"  value=<%=session.getAttribute("Amount") %> readonly/>
  </form>
  <a href="orderfinalized">CHECK ORDER STATUS HERE!</a>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>