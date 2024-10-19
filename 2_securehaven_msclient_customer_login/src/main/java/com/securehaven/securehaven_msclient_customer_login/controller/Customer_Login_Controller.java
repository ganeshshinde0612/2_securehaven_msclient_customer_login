package com.securehaven.securehaven_msclient_customer_login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securehaven.securehaven_msclient_customer_login.emailutility.Customer_Login_Email_Sender;
import com.securehaven.securehaven_msclient_customer_login.exceptions.Invalid_Login;
import com.securehaven.securehaven_msclient_customer_login.model.Customer_Login;
import com.securehaven.securehaven_msclient_customer_login.model.Success_Customer_Login;
import com.securehaven.securehaven_msclient_customer_login.servicei.Customer_Login_ServiceI;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/customer/login")
public class Customer_Login_Controller {
	//Piyush
	
	@Autowired
	Customer_Login_ServiceI customer_Login_ServiceI;
	
	@Autowired
	Customer_Login_Email_Sender send_Login_Email;
	
	@PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Success_Customer_Login> registerCustomer(@RequestBody @Valid  Customer_Login customer) {
		
		log.info("Register Method in controller started");
		
		customer_Login_ServiceI.registerCustomer(customer);
		log.info("Register method in service class called");
		
		send_Login_Email.send_Customer_Registration_Email(customer);
		
		log.info("register email method in controller class called");
		
		Success_Customer_Login s=new Success_Customer_Login();
		s.setMessage("Registration Successful");
		
		log.info("Register email method in controller ended");
		return new ResponseEntity<Success_Customer_Login>(s,HttpStatus.CREATED);
		
	}
	@GetMapping(value = "/getdata", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Customer_Login> getCustomer(){
		log.info("getmethod in controller started");
		
		log.info("getmethod in controller ended");
		
		return customer_Login_ServiceI.getCustomer();	
	}
	
	@GetMapping(value = "/getSingleCustomer/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Customer_Login> getSingleData(@PathVariable("id") int id)
	{
		Customer_Login cl=customer_Login_ServiceI.getSingleCustomer(id);
		return new ResponseEntity<Customer_Login> (cl,HttpStatus.OK);
	}
	
	@PutMapping(value = "/update_details/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Success_Customer_Login> updateCustomer(@RequestBody Customer_Login customer,@PathVariable ("id")int id) {
		log.info("putmapping in controller started");
		customer_Login_ServiceI.updateCustomer(customer,id);
		log.info("putmapping in controller ended");
		send_Login_Email.send_Customer_Registration_Email(customer);
		log.info("putmethod  email method in controller class called");
		Success_Customer_Login s=new Success_Customer_Login();
		s.setMessage("Registration update Successful");
		log.info("putmethod in email  in controller class ended");
		return new ResponseEntity<Success_Customer_Login>(s,HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/check_login")
	public ResponseEntity<Success_Customer_Login> check_Login(@RequestParam String username, @RequestParam String password) 
	{		
		System.out.println(username);
		System.out.println(password);
		Customer_Login customer=customer_Login_ServiceI.check_Login(username,password);

		if(customer != null)
		{ 
			Success_Customer_Login s=new Success_Customer_Login();
			s.setId(customer.getId());
			s.setMessage("Login Successful");
			return new ResponseEntity<Success_Customer_Login>(s,HttpStatus.OK);
		}
		else
		{
			throw new Invalid_Login("Invalid Credentials Entered");
		}
		
		
	}

}
