package com.example.retailstore.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Bill {
	@NotNull(message = "Bill ID cannot be null")
	private Long billId;

	@Valid
	@NotNull(message = "Items cannot be null")
	private List<Item> items;

	@Valid
	@NotNull(message = "User details cannot be null")
	private UserDetails userDetails;

	@NotNull(message = "Total amount cannot be null")
	private BigDecimal totalAmount;

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
