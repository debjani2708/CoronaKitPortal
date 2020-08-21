<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona-Kit Portal</title>
</head>
<body>
<jsp:include page="orderHeader.jsp" />
	<hr/>
<form action="placeorder" method="post">
<%String pName=(String)session.getAttribute("Name");
out.print("Hello " + pName + "Welcome to our portal");%>
<br/><br/>
<label>Order ID : </label>
<%int randomOrderID=(int)(Math.random()*9000)+1000; %>
<%String orderID=String.valueOf(randomOrderID); 
session.setAttribute("OrderID",orderID);%>

	<input type="text" name="orderID"  value=<%=orderID %> readonly/>
	<br/><br/>
	<c:choose>
		<c:when test="${product == null}">
			<p>No order has been placed yet! Place new order.</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px" id="myTable">
				<tr>
					<th>ProductID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>AvailableQuantity</th>
					<th>Order Quantity</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${product }" var="order">
					<tr>
						 
						<td>${order.productId }</td>
						<td>${order.productName }</td>
						<td>${order.productDescription }</td>
						<td>${order.productPrice }</td>
						<td>${order.productQuantity }</td>
						
						<td>
						<input type="number" name="quantity" required/>
						
						</td>
						<%--<td>
							<input type="checkbox"  name="Delete" value=${order.productId }></input>
						</td> --%>
						<td>
							<a href="deleteitem?id=${order.productId }">DELETE</a>
						</td>
						<td>
						<input type="hidden" name="productID" value =${order.productId }></input>
						</td>
						
					</tr> 
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
	<br/><br/>
	<c:if test="${msg != null }">
		<p><strong>${msg }</strong></p>
	</c:if>
	<br/><br/>
	<label>Delivery Address</label>
			<input type="text" name="address"  required />
	<input type="submit" value="CheckOut"/>
</form>
<%--<form action="deleteitem" method="post">
	<input type="submit" id="submit" name="DeleteItem"/>
</form> --%>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>