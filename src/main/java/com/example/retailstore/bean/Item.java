package com.example.retailstore.bean;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.retailstore.enums.ItemType;

public class Item {

	@NotNull(message = "Item ID cannot be null")
	private Long id;

	@NotEmpty(message = "Item name cannot be blank")
	private String name;

	@NotNull(message = "Item type cannot be null")
	private ItemType type;

	@NotNull(message = "Item price cannot be null")
	private BigDecimal price;

	public Item() {
		super();

	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
