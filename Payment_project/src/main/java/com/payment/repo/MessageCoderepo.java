package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.beans.MessageCode;

public interface MessageCoderepo extends JpaRepository<MessageCode, String> {

}
