package com.payment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.beans.BankBic;
import com.payment.repo.BankBicrepo;

@Service
public class BankBicService {
	@Autowired
	BankBicrepo bankBICRepository;
	
	public BankBic findByBIC(String bankbic) {
		Optional<BankBic> optBIC=bankBICRepository.findById(bankbic);
		if(optBIC.isPresent()) {
			return optBIC.get();
		}
		else {
			return null;
		}
	}
}
