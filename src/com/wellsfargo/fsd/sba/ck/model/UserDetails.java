package com.wellsfargo.fsd.sba.ck.model;

import java.time.LocalDate;

public class UserDetails {

	private String personName;
	private String email;
	private Long contactNumber;
	private String deliveryAddress;
	private boolean orderFinalized;
	
	public UserDetails() {
		//left unimplemented
	}

	public UserDetails(String personName, String email, Long contactNumber, String deliveryAddress
			, boolean orderFinalized) {
		super();
		this.personName = personName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.deliveryAddress = deliveryAddress;
		this.orderFinalized = orderFinalized;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public boolean isOrderFinalized() {
		return orderFinalized;
	}

	public void setOrderFinalized(boolean orderFinalized) {
		this.orderFinalized = orderFinalized;
	}

	@Override
	public String toString() {
		return "UserDetails [personName=" + personName + ", email=" + email + ", contactNumber=" + contactNumber
				+ ", deliveryAddress=" + deliveryAddress + ", orderFinalized="+ orderFinalized + "]";
	}
	
}