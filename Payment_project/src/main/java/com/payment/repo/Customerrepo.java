package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.beans.Customer;

public interface Customerrepo extends JpaRepository<Customer, String> {

}
