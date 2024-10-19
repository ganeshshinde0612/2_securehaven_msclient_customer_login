package com.securehaven.securehaven_msclient_customer_login.servicei;



import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.securehaven.securehaven_msclient_customer_login.model.Customer_Login;

public interface Customer_Login_ServiceI {
	
	public void registerCustomer(Customer_Login customer);
	
	public List<Customer_Login> getCustomer();
	
	public void updateCustomer(Customer_Login customer,int id);

	public Customer_Login check_Login(String username, String password);

	public Customer_Login getSingleCustomer(int id);
	
	

}
