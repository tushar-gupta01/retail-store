package com.example.retailstore;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.retailstore.bean.Item;
import com.example.retailstore.bean.UserDetails;
import com.example.retailstore.enums.ItemType;
import com.example.retailstore.enums.UserRole;

public class BillGeneratorHelper {

	public static UserDetails createUserDetails(UserRole role) {
		UserDetails userDetails = new UserDetails();
		userDetails.setRole(role);
		userDetails.setFirstPurchase(LocalDate.now().minusYears(1)); // Assuming one year ago
		return userDetails;
	}

	public static Item createItem(ItemType type, double price) {
		Item item = new Item();
		item.setType(type);
		item.setPrice(BigDecimal.valueOf(price));
		return item;
	}

}
