package com.wellsfargo.fsd.sba.ck.model;

public class KitDetails {

	private Integer orderId;
	private Integer productId;
	private String productName;
	private String productDescription;
	private Integer productQuantity;
	private Double productPrice;
	private Integer orderQuantity;
	private Double productOrderAmount;
	private Double 	totalAmount;
	
	
	public KitDetails() {
		//left unimplemented
	}

	public KitDetails(Integer orderId, Integer productId, String productName, String productDescription,
			Integer productQuantity, Double productPrice, Integer orderQuantity, Double productOrderAmount,
			Double totalAmount) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.orderQuantity = orderQuantity;
		this.productOrderAmount = productOrderAmount;
		this.totalAmount = totalAmount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getProductOrderAmount() {
		return productOrderAmount;
	}

	public void setProductOrderAmount(Double productOrderAmount) {
		this.productOrderAmount = productOrderAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "KitDetails [orderId=" + orderId + ", productId=" + productId + ", productName=" + productName
				+ ", productDescription=" + productDescription + ", productQuantity=" + productQuantity
				+ ", productPrice=" + productPrice + ", orderQuantity=" + orderQuantity + ", productOrderAmount="
				+ productOrderAmount + ", totalAmount=" + totalAmount + "]";
	}



}
