package com.example.retailstore.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.retailstore.bean.Bill;
import com.example.retailstore.service.IBillGeneratorService;

@RestController
@Validated
@RequestMapping("/api/retail-store")
public class BillGeneratorController {

	@Autowired
	IBillGeneratorService billGeneratorService;

	@PostMapping("/calculateNetPayable")
	public ResponseEntity<BigDecimal> calculateNetPayable(@RequestBody @Valid Bill bill) {
		try {
			BigDecimal netPayableAmount = billGeneratorService.calulateNetPayableAmount(bill);
			return ResponseEntity.ok(netPayableAmount);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
