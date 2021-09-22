package com.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.payment.beans.Customer;
import com.payment.beans.ResponsePage;
import com.payment.services.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@GetMapping(value="customer/{customerid}")
	public ResponseEntity<Object> findCustomerById(@PathVariable("customerid") String customerid)
	{
		Customer cust = customerservice.findbyAcctnum(customerid);
		
		if(cust!=null)
			return new ResponseEntity<>(cust,HttpStatus.OK);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure","Customer Not Found"));
	}

}
