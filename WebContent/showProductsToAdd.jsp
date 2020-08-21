<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona-kit Product Details</title>
</head>
<body>
<jsp:include page="orderHeader.jsp" />
<hr/>
<form action="addnewitem" method="post">
<%String pName=(String)session.getAttribute("Name");
out.print("Hello " + pName + " Welcome to our portal");%>
<br/><br/>
	<c:choose>
		<c:when test="${products == null || products.isEmpty() }">
			<p>No Product Available</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>ProductID</th>
					<th>Name</th>
					<th>Description</th>
					<th>AvailableQuantity</th>
					<th>Price</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${products }" var="product">
					<tr>
						<td>${product.productId }</td>
						<td>${product.productName }</td>
						<td>${product.productDescription }</td>
						<td>${product.productQuantity }</td>
						<td>${product.productPrice }</td>
						<td>
							<input type="checkbox" name="productid" value=${product.productId }>ADD TO CART</input>
						</td>						
					</tr> 
				</c:forEach>				
			</table>			
		</c:otherwise>
	</c:choose>	
	<c:if test="${msg != null }">
		<p><strong>${msg }</strong></p>
	</c:if>
	<input type="submit" id="submit" name="submit"/>
	</form>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>