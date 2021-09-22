package com.payment.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.beans.Transaction;
import com.payment.repo.Transactionrepo;

@Service
public class TransactionService {
	
	@Autowired
	Transactionrepo transactionrepo;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BankBicService bankBicService;
	
	@Autowired
	MessageCodeService messageCodeService;
	
	
	public boolean checkBalance(String act_num,double amt)
	{
		double transaction_fee = (0.25/100)*amt;
		double total_amt_transfer = amt + transaction_fee;
		double clear_balance=customerService.findbyAcctnum(act_num).getClear_balance();
		
		String check_overdraft = customerService.findbyAcctnum(act_num).getOverdraft();
		
		if(total_amt_transfer < clear_balance)
		{
			return true;
		}
		else
		{
			if(check_overdraft.equals("yes"))
				return true;
			else
				return false;
		}
	}
		
	
	public Transaction insertTransaction(Transaction transaction)
	{
		return transactionrepo.save(transaction);
	}
}
