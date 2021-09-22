package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.beans.Employee;

public interface Employeerepo extends JpaRepository<Employee, String> {

}
