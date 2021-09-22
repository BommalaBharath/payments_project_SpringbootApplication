package com.payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.beans.Customer;
import com.payment.repo.Customerrepo;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class CustomerService {
	
	@Autowired
	Customerrepo customerrepo;
	
	public Customer findbyAcctnum(String acc_num)
	{
		java.util.Optional<Customer> optcust = customerrepo.findById(acc_num);
		if(optcust.isPresent())
		{
			return optcust.get();
		}
		else
			return null;
		
	}
	
	
	public void updateCheckbalance(String acc_num,double amt) {
		
		Customer customer = findbyAcctnum(acc_num);
		customer.setClear_balance(customer.getClear_balance() - amt);  
		Customer c1 = customerrepo.save(customer);
		
	}
}
