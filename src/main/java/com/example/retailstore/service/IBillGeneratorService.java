package com.example.retailstore.service;

import java.math.BigDecimal;

import com.example.retailstore.bean.Bill;

public interface IBillGeneratorService {

	BigDecimal calculateGroceryAmount(Bill bill);

	BigDecimal calulatePercentDiscount(Bill bill, BigDecimal amount);

	BigDecimal calulateStandardDiscount(Bill bill, BigDecimal amount);

	BigDecimal calulateNetPayableAmount(Bill bill);

}
