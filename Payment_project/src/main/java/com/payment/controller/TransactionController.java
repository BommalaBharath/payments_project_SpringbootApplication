package com.payment.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.beans.ResponsePage;
import com.payment.beans.Transaction;
import com.payment.beans.TransactionBody;
import com.payment.repo.Customerrepo;
import com.payment.services.BankBicService;
import com.payment.services.CustomerService;
import com.payment.services.MessageCodeService;
import com.payment.services.SdnCheckerService;
import com.payment.services.TransactionService;

@RestController
@CrossOrigin
public class TransactionController {

	@Autowired
	CustomerService customerService;

	@Autowired
	BankBicService bankBicService;

	@Autowired
	MessageCodeService mesaCodeService;


	@Autowired
	TransactionService transactionService;

	@PostMapping(value="transaction")
	public ResponseEntity<Object> processingTransaction(@RequestBody TransactionBody transactionBody)
	{
		try{
				SdnCheckerService sdnCheckerService = new SdnCheckerService();
				if(sdnCheckerService.checker(transactionBody.getReceiver_account_name()))
				{
						if(transactionService.checkBalance(transactionBody.getCustomer_id(), transactionBody.getAmount()))
						{
							double total_money_deduct = transactionBody.getAmount()+(0.25/100)*transactionBody.getAmount();
							customerService.updateCheckbalance(transactionBody.getCustomer_id(), total_money_deduct);
							Transaction transaction = new Transaction();
							transaction.setCustomer(customerService.findbyAcctnum(transactionBody.getCustomer_id()));
							if(customerService.findbyAcctnum(transactionBody.getCustomer_id()).isIsbank())
								transaction.setTransfer_type("O");
							else
								transaction.setTransfer_type("C");
							
							transaction.setMessagecode(mesaCodeService.findbyMsgcode(transactionBody.getMessage_code()));
							transaction.setBank(bankBicService.findByBIC(transactionBody.getReceiver_bic()));
							transaction.setAmount(total_money_deduct);
							transaction.setReceiver_name(transactionBody.getReceiver_account_name());
							transaction.setReceiver_account_number(transactionBody.getReceiver_account_number());
							transaction.setTimestamp(LocalDateTime.now());
							Transaction t1 = transactionService.insertTransaction(transaction);
							return new ResponseEntity<>(t1,HttpStatus.OK);
							
						}
						else
						{
							return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure","Hey buddy you dont have enough balance to do this transaction "));
						}
							
					}
				else
				{
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure","Danger!! Receiver account is blacklisted"));
				}
				}
				catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
				}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponsePage("failure","Transaction failed"));
	}
}
