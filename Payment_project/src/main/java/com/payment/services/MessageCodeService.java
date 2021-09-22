package com.payment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.beans.Customer;
import com.payment.beans.MessageCode;
import com.payment.repo.Customerrepo;
import com.payment.repo.MessageCoderepo;

@Service
public class MessageCodeService {
	
	@Autowired
	MessageCoderepo messagecoderepo;
	
	public MessageCode findbyMsgcode(String msgcode)
	{
		java.util.Optional<MessageCode> optmsgcode = messagecoderepo.findById(msgcode);
		if(optmsgcode.isPresent())
		{
			return optmsgcode.get();
		}
		else
			return null;
		
		
		
	}

}
