package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.beans.Transaction;

public interface Transactionrepo extends JpaRepository<Transaction, Integer> {
	

}
