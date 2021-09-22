package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.payment.beans.BankBic;
import com.payment.beans.ResponsePage;
import com.payment.services.BankBicService;

@RestController
@CrossOrigin
public class BankBicController {
	
	@Autowired
	BankBicService bankbicservice;
	
	@GetMapping(value="bank/{bic}")
	public ResponseEntity<Object> findBankNamebyBic(@PathVariable("bic") String bic)
	{
		BankBic bbic = bankbicservice.findByBIC(bic);
		
		if(bbic!=null)
			return new ResponseEntity<>(bbic, HttpStatus.OK);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure","No banks found"));
	}
}
