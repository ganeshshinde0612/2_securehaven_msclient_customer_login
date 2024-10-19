package com.securehaven.securehaven_msclient_customer_login.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.securehaven.securehaven_msclient_customer_login.exceptions.Data_Not_Found_Exception;
import com.securehaven.securehaven_msclient_customer_login.exceptions.Data_Not_Saved_Exception;
import com.securehaven.securehaven_msclient_customer_login.exceptions.Invalid_Login;
import com.securehaven.securehaven_msclient_customer_login.model.Customer_Login;
import com.securehaven.securehaven_msclient_customer_login.repository.Customer_Login_Repository;
import com.securehaven.securehaven_msclient_customer_login.servicei.Customer_Login_ServiceI;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class Customer_Login_ServiceImpl implements Customer_Login_ServiceI {

	@Autowired
	Customer_Login_Repository customer_Login_Repository;

	@Override
	public void registerCustomer(Customer_Login customer) {
		
		log.info("register method start in serviceimpl");
		customer_Login_Repository.save(customer);
		log.info("register method end in serviceimpl");
		
		Customer_Login c1=customer_Login_Repository.findById(customer.getId()).get();
		if(c1 == null)
		{
			log.error("data not saved in exception");
			throw new Data_Not_Saved_Exception("Registration Failed !");
		}
		
	}

	@Override
	public List<Customer_Login> getCustomer() {
		
		log.info("getcustomer method  start in serviceimpl");
		log.info("getcustomer method  ended in serviceimpl");
		List<Customer_Login>list=customer_Login_Repository.findAll();
		
		if(list.isEmpty())
		{
			throw new Data_Not_Found_Exception("Data Not Found");
		}
		else
		{
			return list;
		}
	}

	
	@Override
	public void updateCustomer(Customer_Login customer,int id) {
		log.info("updateCustomer method  start in serviceimpl");
		customer_Login_Repository.save(customer);
		log.info("updateCustomer  method  ended in serviceimpl");
		
	}

	@Override
	public Customer_Login check_Login(String username, String password) {
		
		Customer_Login customer=customer_Login_Repository.findByUsername(username);
		
		if(customer != null)
		{
			System.out.println("in exception");
			if(customer.getUsername().equals(username) && customer.getPassword().equals(password))
			{
				return customer;
			}
			else
			{
				throw new Invalid_Login("Invalid Credentials");
			}
		}
		else
		{
			System.out.println("in throw exception");
			throw new Invalid_Login("Invalid Credentials");
		}
		
		
		
	}

	@Override
	public Customer_Login getSingleCustomer(int id) {
		// TODO Auto-generated method stub
		return customer_Login_Repository.findById(id).get();
	}


	
	
	
}
