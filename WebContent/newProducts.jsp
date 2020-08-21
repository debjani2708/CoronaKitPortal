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
<jsp:include page="productHeader.jsp" />
	<hr/>
	<h3>${isNew?'New Product':'Edit Product'}</h3>
	
	<form action="${isNew?'insertproduct':'saveproduct'}" method="post">
		<div>
			<label>ProductId</label>
			<input type="number" name="productId" value="${product.productId}" required ${isNew?'':'readonly' }/>
		</div>	
		<div>
			<label>Name</label>
			<input type="text" name="productName" value="${product.productName}" required />
		</div>	
		<div>
			<label>Description</label>
			<input type="text" name="productDescription" value="${product.productDescription}" required />
		</div>	
		<div>
			<label>AvailableQuantity</label>
			<input type="number" name="productQuantity" value="${product.productQuantity}" required />
		</div>	
		<div>
			<label>Price</label>
			<input type="decimal" name="productPrice" value="${product.productPrice}" required />
		</div>	
		<button>SAVE</button>
	</form>
	<hr/>
	<jsp:include page="footer.jsp" />
</body>
</html>