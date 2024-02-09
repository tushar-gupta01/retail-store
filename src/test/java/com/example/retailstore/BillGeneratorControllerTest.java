package com.example.retailstore;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.retailstore.controller.BillGeneratorController;
import com.example.retailstore.service.IBillGeneratorService;

@SpringBootTest
@AutoConfigureMockMvc
class BillGeneratorControllerTest {

	@InjectMocks
	private BillGeneratorController billGeneratorController;

	@Mock
	private IBillGeneratorService billGeneratorService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCalculateNetPayable_Success() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/retail-store/calculateNetPayable")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"{\"billId\": 1, \"items\": [{\"id\": 1, \"name\": \"Item1\", \"type\": \"GROCERY\", \"price\": 10.0}], \"userDetails\": {\"name\": \"John\", \"contactNo\": \"1234567890\", \"role\": \"CUSTOMER\", \"firstPurchase\": \"2022-01-01\"}, \"totalAmount\": 100.0}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testCalculateNetPayable_Exception() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/retail-store/calculateNetPayable")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"billId\": 1, \"items\": [], \"userDetails\": {}, \"totalAmount\": 100.0}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isInternalServerError());
	}
}
