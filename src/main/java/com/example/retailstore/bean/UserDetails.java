package com.example.retailstore.bean;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.retailstore.enums.UserRole;

public class UserDetails {

	@NotEmpty(message = "Name cannot be blank")
	private String name;

	@NotEmpty(message = "Contact number cannot be blank")
	private String contactNo;

	@NotNull(message = "User role cannot be null")
	private UserRole role;

	@NotNull(message = "First purchase date cannot be null")
	private LocalDate firstPurchase;

	public UserDetails() {
		super();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public LocalDate getFirstPurchase() {
		return firstPurchase;
	}

	public void setFirstPurchase(LocalDate firstPurchase) {
		this.firstPurchase = firstPurchase;
	}

}
