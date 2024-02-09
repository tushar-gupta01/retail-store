package com.example.retailstore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.retailstore.bean.Bill;
import com.example.retailstore.bean.Item;
import com.example.retailstore.enums.ItemType;
import com.example.retailstore.enums.UserRole;
import com.example.retailstore.service.impl.BillGeneratorServiceImpl;

@SpringBootTest
class BillGeneratorServiceTest {

	@InjectMocks
	private BillGeneratorServiceImpl billGeneratorService;

	@Mock
	private Bill bill;

	@Test
	void testCalculateGroceryAmount() {
		List<Item> items = Arrays.asList(BillGeneratorHelper.createItem(ItemType.GROCERY, 10),
				BillGeneratorHelper.createItem(ItemType.OTHERS, 20));
		when(bill.getItems()).thenReturn(items);
		assertEquals(BigDecimal.valueOf(10.0), billGeneratorService.calculateGroceryAmount(bill));
	}

	@Test
     void testCalculatePercentDiscountForEmployee() {
        when(bill.getTotalAmount()).thenReturn(BigDecimal.valueOf(100));
        when(bill.getUserDetails()).thenReturn(BillGeneratorHelper.createUserDetails(UserRole.EMPLOYEE));
        assertEquals(BigDecimal.valueOf(30.0), billGeneratorService.calulatePercentDiscount(bill, BigDecimal.ZERO));
    }

	@Test
     void testCalculatePercentDiscountForAffiliate() {
        when(bill.getTotalAmount()).thenReturn(BigDecimal.valueOf(100));
        when(bill.getUserDetails()).thenReturn(BillGeneratorHelper.createUserDetails(UserRole.AFFILIATE));
        assertEquals(BigDecimal.valueOf(10.0), billGeneratorService.calulatePercentDiscount(bill, BigDecimal.ZERO));
    }

	@Test
	void testCalculateStandardDiscount() {
		assertEquals(BigDecimal.valueOf(5),
				billGeneratorService.calulateStandardDiscount(bill, BigDecimal.valueOf(100)));
	}

	@Test
	void testCalculateNetPayableAmount() {
		List<Item> items = Arrays.asList(BillGeneratorHelper.createItem(ItemType.GROCERY, 10),
				BillGeneratorHelper.createItem(ItemType.OTHERS, 20));
		when(bill.getTotalAmount()).thenReturn(BigDecimal.valueOf(100));
		when(bill.getUserDetails()).thenReturn(BillGeneratorHelper.createUserDetails(UserRole.CUSTOMER));
		when(bill.getItems()).thenReturn(items);

		assertEquals(BigDecimal.valueOf(95), billGeneratorService.calulateNetPayableAmount(bill));
	}
}
