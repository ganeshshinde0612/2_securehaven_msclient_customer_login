package com.securehaven.securehaven_msclient_customer_login.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer_login_Exception{
	
	private String msg;
	private int statusCode;
	private String uri;
	private HttpStatus statusMsg;
	private Date date;
}
