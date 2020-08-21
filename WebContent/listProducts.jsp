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
<jsp:include page="productHeader.jsp" />
<hr/>
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
							<a href="deleteproduct?id=${product.productId }">DELETE</a>
							<span>|</span>
							<a href="editproduct?id=${product.productId }">EDIT</a>
						</td>
					</tr> 
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>	
	<c:if test="${msg != null }">
		<p><strong>${msg }</strong></p>
	</c:if>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>