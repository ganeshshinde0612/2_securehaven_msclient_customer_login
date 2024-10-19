package com.securehaven.securehaven_msclient_customer_login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securehaven.securehaven_msclient_customer_login.model.Customer_Login;
@Repository
public interface Customer_Login_Repository extends JpaRepository<Customer_Login, Integer>{

	
	//Custom Finder Method
	public Customer_Login findByUsername(String username);

	
}
