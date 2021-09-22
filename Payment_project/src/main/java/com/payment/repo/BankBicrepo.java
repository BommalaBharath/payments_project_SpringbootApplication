package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.beans.BankBic;

public interface BankBicrepo extends JpaRepository<BankBic, String> {

}
