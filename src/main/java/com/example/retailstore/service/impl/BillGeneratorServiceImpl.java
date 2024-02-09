package com.example.retailstore.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.example.retailstore.bean.Bill;
import com.example.retailstore.bean.Item;
import com.example.retailstore.enums.ItemType;
import com.example.retailstore.enums.UserRole;
import com.example.retailstore.service.IBillGeneratorService;
import com.example.retailstore.util.Constants;

@Service
public class BillGeneratorServiceImpl implements IBillGeneratorService {

	@Override
	// Checks and returns the total amount of grocery items
	public BigDecimal calculateGroceryAmount(Bill bill) {
		return bill.getItems().stream().filter(item -> ItemType.GROCERY.equals(item.getType())).map(Item::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	// Returns the discount offered on non-grocery items based on user role
	public BigDecimal calulatePercentDiscount(Bill bill, BigDecimal amount) {
		BigDecimal newTotalAmount = bill.getTotalAmount().subtract(amount);
		BigDecimal percentDiscount = BigDecimal.ZERO;
		UserRole userRole = bill.getUserDetails().getRole();

		switch (userRole) {
		case EMPLOYEE:
			percentDiscount = newTotalAmount.multiply(Constants.EMPLOYEE_DISCOUNT_PERCENTAGE);
			break;
		case AFFILIATE:
			percentDiscount = newTotalAmount.multiply(Constants.AFFILIATE_DISCOUNT_PERCENTAGE);
			break;
		case CUSTOMER:
			if (Period.between(bill.getUserDetails().getFirstPurchase(), LocalDate.now()).getYears() >= 2) {
				percentDiscount = newTotalAmount.multiply(Constants.CUSTOMER_DISCOUNT_PERCENTAGE);
			}
			break;
		}

		return percentDiscount;
	}

	@Override
	// Returns the standard discount offered on the bill after percent discount
	public BigDecimal calulateStandardDiscount(Bill bill, BigDecimal amount) {
		return BigDecimal.valueOf(((int) (amount.divide(BigDecimal.valueOf(100)).intValue()) * 5));
	}

	@Override
	// Returns the net payable amount after applying the discounts
	public BigDecimal calulateNetPayableAmount(Bill bill) {
		BigDecimal netPayableAmount = bill.getTotalAmount();
		BigDecimal totalGroceryAmount = calculateGroceryAmount(bill);
		netPayableAmount = netPayableAmount.subtract(calulatePercentDiscount(bill, totalGroceryAmount));
		netPayableAmount = netPayableAmount.subtract(calulateStandardDiscount(bill, netPayableAmount));
		return netPayableAmount;
	}
}
